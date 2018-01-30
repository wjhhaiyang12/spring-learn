package com.example.dbtest.model;

import java.io.Serializable;

public class MismatchStoreKey implements Serializable {

    private String sStoreId;

    private String sourceId;

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((sStoreId == null) ? 0 : sStoreId.hashCode());
        result = PRIME * result + ((sourceId == null) ? 0 : sourceId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) {
            return true;
        }
        if(null == object) {
            return false;
        }
        if(getClass() != object.getClass()) {
            return false;
        }

        MismatchStoreKey mismatchStoreKey = (MismatchStoreKey)object;

        //距离在200m以内就视为相同
        if(sStoreId.equals(mismatchStoreKey.getsStoreId()) && sourceId.equals(mismatchStoreKey.getSourceId()))
            return true;

        return false;
    }

    public String getsStoreId() {
        return sStoreId;
    }

    public void setsStoreId(String sStoreId) {
        this.sStoreId = sStoreId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
