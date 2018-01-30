package com.example.dbtest.model;

import javax.persistence.*;

@Entity
@Table(name = "nb_store_rel")
public class StoreRel {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "source_id")
    private String sourceId;

    @Column(name = "s_store_id")
    private String sStoreId;

    @Column(name = "status")
    private String status;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "createTime")
    private String createTime;

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

        StoreRel storeRel = (StoreRel) object;
        if(storeId.equals(storeRel.getStoreId())
                && sourceId.equals(storeRel.getSourceId())
                && sStoreId.equals(storeRel.getsStoreId()))
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        String str = "storeId" + storeId + "sourceId" + sourceId + "sStoreId" + sStoreId;
        return str.hashCode();
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getsStoreId() {
        return sStoreId;
    }

    public void setsStoreId(String sStoreId) {
        this.sStoreId = sStoreId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}