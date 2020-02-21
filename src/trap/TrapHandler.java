package trap;

import java.util.ArrayList;

public class TrapHandler {
	private static ArrayList<Trap> traps;
	public static void insertTrap(String thread_id, String objectId, String operation_id) {
		if(traps == null) traps = new ArrayList<Trap>();
		Trap trap = new Trap(thread_id, objectId, operation_id);
		trap.setObjectID(objectId);
		traps.add(trap);		
	}
	
	public static void OnCall(String thread_id, String object_id, String operation_id) {
		//check_for_trap(thread_id, object_id, operation_id);
		//if(should_delay(operation_id)){
			insertTrap(thread_id, object_id, operation_id);
		//}
		
	}

}
