package com.company.trap;

import java.sql.Timestamp;

public class NearMiss {
    String objectID;

    String operationId;
    String threadId;
    public NearMiss(String objectID, String operationId, String threadId){
        this.objectID = objectID;
        this.operationId = operationId;
        this.threadId = threadId;
    }

    public String getObjectID() {
        return objectID;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getThreadId() {
        return threadId;
    }


}
