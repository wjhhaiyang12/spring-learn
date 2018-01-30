package com.example.dbtest.model;


import com.example.dbtest.baidu.DistanceCalculate;

public class BaiduCoordinate {

    private double baiduGeocoderLongitude;

    private double baiduGeocoderLatitude;

    public BaiduCoordinate(){}

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

        BaiduCoordinate baiduCoordinate = (BaiduCoordinate)object;
//        BigDecimal bigDecimalLongitude = BigDecimal.valueOf(baiduGeocoderLongitude);
//        BigDecimal bigDecimalLatitude = BigDecimal.valueOf(baiduGeocoderLatitude);
//        BigDecimal baiduCoordinateLongitude = BigDecimal.valueOf(baiduCoordinate.getBaiduGeocoderLongitude());
//        BigDecimal baiduCoordinateLatitude = BigDecimal.valueOf(baiduCoordinate.getBaiduGeocoderLatitude());
//
//        if(bigDecimalLongitude.compareTo(baiduCoordinateLongitude) == 0
//                && bigDecimalLatitude.compareTo(baiduCoordinateLatitude) == 0)
//            return true;

        //距离在200m以内就视为相同
        if(DistanceCalculate.getDistance(baiduGeocoderLatitude, baiduGeocoderLongitude,
            baiduCoordinate.getBaiduGeocoderLatitude(), baiduCoordinate.getBaiduGeocoderLongitude()) < 200.0)
            return true;

        return false;
    }

    @Override
    public String toString(){
        return "baiduGeocoderLongitude" + baiduGeocoderLongitude + "baiduGeocoderLatitude" + baiduGeocoderLatitude;
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }

    public BaiduCoordinate(double baiduGeocoderLongitude, double baiduGeocoderLatitude){
        this.baiduGeocoderLongitude = baiduGeocoderLongitude;
        this.baiduGeocoderLatitude = baiduGeocoderLatitude;
    }

    public double getBaiduGeocoderLongitude() {
        return baiduGeocoderLongitude;
    }

    public void setBaiduGeocoderLongitude(double baiduGeocoderLongitude) {
        this.baiduGeocoderLongitude = baiduGeocoderLongitude;
    }

    public double getBaiduGeocoderLatitude() {
        return baiduGeocoderLatitude;
    }

    public void setBaiduGeocoderLatitude(double baiduGeocoderLatitude) {
        this.baiduGeocoderLatitude = baiduGeocoderLatitude;
    }
}
