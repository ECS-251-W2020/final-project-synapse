package trap;

import java.sql.Timestamp;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class TrapHandler {
	private static ArrayList<Trap> traps;
	public static void insertTrap(String thread_id, String objectId, String operation_id) {
		if(traps == null) traps = new ArrayList<Trap>();
		Trap trap = new Trap(thread_id, objectId, operation_id);
		System.out.println("Trap created with thread id:" +thread_id);
		trap.setObjectID(objectId);
		if(checkNearMiss(trap)){
			System.out.println("Near Miss Detected!");
		}
		traps.add(trap);
	}
	private static boolean checkNearMiss(trap.Trap trap){
		Timestamp trapTime = trap.getCreateTime();
		for (Trap existingTrap: traps){
			long diff = trapTime.getTime() - existingTrap.getCreateTime().getTime();
			if(abs(diff) < 1000){
				return true;
			}

		}
		return false;
	}
	
	public static void OnCall(String thread_id, String object_id, String operation_id) {
		//check_for_trap(thread_id, object_id, operation_id);
		//if(should_delay(operation_id)){
			insertTrap(thread_id, object_id, operation_id);
		//}
		
	}

}
