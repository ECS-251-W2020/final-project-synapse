package com.tsvd.trap;

import com.conf.Configuration;

import java.sql.Timestamp;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class TrapHandler {

	private static ArrayList<Trap> traps;

	public static void insertTrap(String thread_id, String objectId, String operation_id) {

		if(traps == null) traps = new ArrayList<Trap>();

		Trap trap = new Trap(thread_id, objectId, operation_id);
		System.out.println("Entry in Trap Set created with thread id: " + thread_id);
		trap.setObjectID(objectId);
		/*if(checkNearMiss(trap)){
			System.out.println("Near Miss Detected!");
		}*/
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
		Integer threshold = Integer.valueOf((String) Configuration.properties.get("nearMissThreshold"));
		for (Trap existingTrap: traps){
			long diff = trapTime.getTime() - existingTrap.getCreateTime().getTime();
			if(abs(diff) < threshold){
				return true;
			}
		}
		return false;
	}
	
	public static void OnCall(String thread_id, String object_id, String operation_id) {
		//check_for_trap(thread_id, object_id, operation_id);
		//if(should_delay(operation_id)){
			insertTrap(thread_id, object_id, operation_id);
			System.out.println("Thread " + thread_id + " sleeping for 5secs");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//}
		
	}
}
