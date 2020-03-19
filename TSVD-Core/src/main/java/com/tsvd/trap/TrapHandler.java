package com.tsvd.trap;

import com.conf.Configuration;
import com.tsvd.ThreadSafetyContract;
import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TrapHandler.java
 *
 * This consists of the main TSVD algorithm (the trap and near-miss functionality)
 */
public class TrapHandler {

    private static CopyOnWriteArrayList<Trap> traps;

    // function to insert trap into the global trap set

    private static void insertTrap(Trap trap) {
        traps.add(trap);
        System.out.println("Inserted trap: (" + trap.getThreadId() + ", " + trap.getObjectID() + ", " + trap.getOperationId()
                + ") into trap set");
    }

//    private static void clearTrap(String thread_id, String objectId, String operation_id){
//        for(Trap trap: traps){
//            if(trap.getThreadId().equals(thread_id) && trap.getObjectID().equals(objectId) && trap.getOperationId().equals(operation_id)){
//                traps.remove(trap);
//                System.out.println("Removed trap: (" + thread_id + ", " + objectId + ", " + operation_id + ") from trap set");
//            }
//        }
//    }

    // function to clear an inserted trap

    private static void clearTrap(Trap trap){
        traps.remove(trap);
        System.out.println("Removed trap: (" + trap.getThreadId() + ", " + trap.getObjectID() + ", " + trap.getOperationId()
                + ") from trap set");
    }

    // function to report TSVs if near-miss conflicts are found
    private static void checkNearMiss(Trap trap){

        Timestamp trapTime = trap.getCreateTime();

        int threshold = Configuration.NEAR_MISS_THRESHOLD;

        for (Trap existingTrap: traps){

            String existingTrapThreadID = existingTrap.getThreadId();
            String existingTrapOperationID = existingTrap.getOperationId();
            String existingTrapObjectID = existingTrap.getObjectID();

            String currentThreadID = trap.getThreadId();
            String currentOperationID = trap.getOperationId();
            String currentObjectID = trap.getObjectID();

            if(!(currentThreadID.equals(existingTrapThreadID)) && currentObjectID.equals(existingTrapObjectID)){

                if(ThreadSafetyContract.object.getString(currentOperationID).equals("write") ||
                                ThreadSafetyContract.object.getString(existingTrapOperationID).equals("write"))
                {
                    long diff = trapTime.getTime() - existingTrap.getCreateTime().getTime();

                    if(Math.abs(diff) <= threshold){

                        System.out.println("\nThread Safety Violation Detected:" +
                                "\n\tThreadID: " + existingTrapThreadID + " and " + currentThreadID +
                                "\n\tObjectID: " + existingTrapObjectID +
                                "\n\tOperations: " + existingTrapOperationID + " and " + currentOperationID
                        );
                    }
                }
            }
        }
    }

    // driver function
    public static void OnCall(String thread_id, String object_id, String operation_id) {

        if(traps == null) traps = new CopyOnWriteArrayList<Trap>();

        Trap trap = new Trap(thread_id, object_id, operation_id);

        System.out.println("Trap created with (thread_id, objectId, operation_id): " +
                thread_id + " " +
                object_id + " " +
                operation_id +
                " at time: " +
                trap.getCreateTime());

        // checks if there is a TSV, if it's there then report it
        checkNearMiss(trap);

        // insert trap, sleep the thread for a random time, and clear trap

        insertTrap(trap);

        int delay = Configuration.MAX_RANDOM_DELAY;
        if(Configuration.RANDOM_DELAYS){
            delay = (int) (Math.random()*(Configuration.MAX_RANDOM_DELAY));
        }
        System.out.println("Thread " + thread_id + " sleeping for " + delay + " milliseconds");
        try {
            Thread.sleep(delay);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // clearTrap(thread_id, object_id, operation_id);
        clearTrap(trap);
    }
}
