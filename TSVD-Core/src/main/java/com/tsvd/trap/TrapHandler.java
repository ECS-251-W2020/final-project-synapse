package com.tsvd.trap;

import com.conf.Configuration;
import com.tsvd.ThreadSafetyContract;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;


import static java.lang.Math.abs;
import static java.lang.Math.random;

public class TrapHandler {

	private static CopyOnWriteArrayList<Trap> traps;

	public static void insertTrap(Trap trap) {

//		if(traps == null) traps = new CopyOnWriteArrayList<Trap>();
//
//		Trap trap = new Trap(thread_id, objectId, operation_id);
//		System.out.println("Trap Set entry with (thread_id, objectId, operation_id): " +
//				thread_id + " " +
//				objectId + " " +
//				operation_id +
//				" at time: " +
//				trap.getCreateTime());

//		trap.setObjectID(objectId);   // is this required?

//		checkNearMiss(trap);

		traps.add(trap);
	}

	public static void clearTrap(String thread_id, String objectId, String operation_id){
		for(Trap trap: traps){
			if(trap.getThreadId().equals(thread_id) && trap.getObjectID().equals(objectId) && trap.operationId.equals(operation_id))
				traps.remove(trap);
		}
	}
	private static boolean checkNearMiss(Trap trap){

		Timestamp trapTime = trap.getCreateTime();

		Integer threshold = Configuration.NEAR_MISS_THRESHOLD;

		for (Trap existingTrap: traps){
//			System.out.println(trapTime.getTime() + " " + existingTrap.getCreateTime().getTime());

			String existingTrapThreadID = existingTrap.getThreadId();
			String existingTrapOperationID = existingTrap.getOperationId();
			String existingTrapObjectID = existingTrap.getObjectID();

			String currentThreadID = trap.getThreadId();
			String currentOperationID = trap.getOperationId();
			String currentObjectID = trap.getObjectID();

//			System.out.println(currentOperationID.substring(0,currentOperationID.lastIndexOf('.')));

			if(
					!(currentThreadID.equals(existingTrapThreadID)) &&
					currentObjectID.equals(existingTrapObjectID)
			){
				if(
						ThreadSafetyContract.object.getString(currentOperationID).equals("write") ||
						ThreadSafetyContract.object.getString(existingTrapOperationID).equals("write")
				){
					long diff = trapTime.getTime() - existingTrap.getCreateTime().getTime();
//					System.out.println("diff: " + diff);
					if(abs(diff) < threshold){

						System.out.println("\nThread Safety Violation Detected:" +
								"\n\tThreadID: " + existingTrapThreadID + " and " + currentThreadID +
								"\n\tObjectID:" + existingTrapObjectID +
								"\n\tOperationID:" + existingTrapOperationID
						);
					}
				}
			}
		}
		return false;
	}
	
	public static void OnCall(String thread_id, String object_id, String operation_id) {

		if(traps == null) traps = new CopyOnWriteArrayList<Trap>();

		Trap trap = new Trap(thread_id, object_id, operation_id);
		System.out.println("Trap entry created with (thread_id, objectId, operation_id): " +
				thread_id + " " +
				object_id + " " +
				operation_id +
				" at time: " +
				trap.getCreateTime());

//		near miss tracking
		checkNearMiss(trap);

		//if(should_delay(operation_id)){

			insertTrap(trap);

			int delay = Configuration.MAX_RANDOM_DELAY;
			if(Configuration.RANDOM_DELAYS){
				delay = (int) (random()*(Configuration.MAX_RANDOM_DELAY));
			}
			System.out.println("Thread " + thread_id + " sleeping for "+ delay +" milliseconds");
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
		clearTrap(thread_id, object_id, operation_id);
	}
}
