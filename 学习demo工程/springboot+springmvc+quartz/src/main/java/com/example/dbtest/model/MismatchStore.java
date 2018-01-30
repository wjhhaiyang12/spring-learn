package com.example.dbtest.model;

import javax.persistence.*;

@Entity
@Table(name = "nb_mismatch_store")
@IdClass(MismatchStoreKey.class)
public class MismatchStore {

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "bd_longitude")
    private String bd_longitude;

    @Column(name = "bd_latitude")
    private String bd_latitude;

    @Id
    @Column(name = "s_store_id")
    private String sStoreId;

    @Id
    @Column(name = "source_id")
    private String sourceId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getBd_longitude() {
        return bd_longitude;
    }

    public void setBd_longitude(String bd_longitude) {
        this.bd_longitude = bd_longitude;
    }

    public String getBd_latitude() {
        return bd_latitude;
    }

    public void setBd_latitude(String bd_latitude) {
        this.bd_latitude = bd_latitude;
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
