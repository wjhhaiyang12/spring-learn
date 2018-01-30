package com.example.dbtest.controllers;

import com.example.dbtest.baidu.BaiduMapApi;
import com.example.dbtest.baidu.DistanceCalculate;
import com.example.dbtest.baidu.TelephoneUtil;
import com.example.dbtest.baidu.WordDistance;
import com.example.dbtest.dao.MismatchStoreDao;
import com.example.dbtest.dao.StoreDao;
import com.example.dbtest.dao.StoreRelDao;
import com.example.dbtest.model.BaiduCoordinate;
import com.example.dbtest.model.MismatchStore;
import com.example.dbtest.model.Store;
import com.example.dbtest.model.StoreRel;
import com.example.dbtest.service.TestService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class TestController {

    @Autowired
    StoreDao storeDao;

    @Autowired
    MismatchStoreDao mismatchStoreDao;

    @Autowired
    StoreRelDao storeRelDao;

    @Autowired
    TestService testService;

    @RequestMapping(value = "/scopeTest")
    public String scopeTest(){
        return testService.testData.toString();
    }


    @RequestMapping(value = "/doNameGeocoding")
    public String doNameGeocoding(){
        List<Store> standardStores = storeDao.findAll();
        List<MismatchStore> mismatchStores = mismatchStoreDao.findAll();

//        for(int i = 0; i < standardStores.size(); ++i){
//            BaiduCoordinate baiduCoordinate = BaiduMapApi.searchCoordinate(standardStores.get(i).getName());
//            if(baiduCoordinate == null)
//                continue;
//            standardStores.get(i).setLatitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLatitude()));
//            standardStores.get(i).setLongitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLongitude()));
//            storeDao.save(standardStores.get(i));
//            if(i % 200 == 0)
//                System.out.println("标准影院处理到: " + i);
//        }

        for(int i = 0; i < mismatchStores.size(); ++i){
            BaiduCoordinate baiduCoordinate = BaiduMapApi.searchCoordinate(mismatchStores.get(i).getName());
            if(baiduCoordinate == null)
                continue;
            mismatchStores.get(i).setLatitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLatitude()));
            mismatchStores.get(i).setLongitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLongitude()));
            mismatchStoreDao.save(mismatchStores.get(i));
            if(i % 200 == 0)
                System.out.println("非标准影院处理到: " + i);
        }
        return "get request.";
    }

    @RequestMapping(value = "/doAddressGeocoding")
    public String doAddressGeocoding(){
        List<Store> standardStores = storeDao.findAll();
        List<MismatchStore> mismatchStores = mismatchStoreDao.findAll();

        for(int i = 0; i < standardStores.size(); ++i){
            BaiduCoordinate baiduCoordinate = BaiduMapApi.searchCoordinate(standardStores.get(i).getAddress());
            if(baiduCoordinate == null)
                continue;
            standardStores.get(i).setBd_latitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLatitude()));
            standardStores.get(i).setBd_longitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLongitude()));
            storeDao.save(standardStores.get(i));
            if(i % 200 == 0)
                System.out.println("标准影院处理到: " + i);
        }

        for(int i = 0; i < mismatchStores.size(); ++i){
            BaiduCoordinate baiduCoordinate = BaiduMapApi.searchCoordinate(mismatchStores.get(i).getAddress());
            if(baiduCoordinate == null)
                continue;
            mismatchStores.get(i).setBd_latitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLatitude()));
            mismatchStores.get(i).setBd_longitude(String.valueOf(baiduCoordinate.getBaiduGeocoderLongitude()));
            mismatchStoreDao.save(mismatchStores.get(i));
            if(i % 200 == 0)
                System.out.println("非标准影院处理到: " + i);
        }
        return "get request.";
    }

    @RequestMapping(value = "/similarityTest")
    public String similarityTest(){
        String nameA = "上海万达影城";
        String nameB = "万达上海影城";
        float similarity = WordDistance.levenshtein(nameA, nameB);
        return "相似度：" + similarity;
    }

    @RequestMapping(value = "/generateData")
    public String generateData(){
        List<Store> standardStores = storeDao.findAll();
        List<MismatchStore> mismatchStores = mismatchStoreDao.findAll();
        List<StoreRel> storeRels = storeRelDao.findAll();

        System.out.println(standardStores.size());
        System.out.println(mismatchStores.size());
        System.out.println(storeRels.size());

        //生成关联集合
        Set<StoreRel> storeRelSet = new HashSet<>();
        for(int i = 0; i < storeRels.size(); ++i)
            storeRelSet.add(storeRels.get(i));

        try {
            String posFile = "D:\\正负样本数据\\positive.txt";
            String negFile = "D:\\正负样本数据\\negative.txt";
            FileWriter posWriter = new FileWriter(posFile, true);
            FileWriter negWriter = new FileWriter(negFile, true);
            for (int i = 0; i < standardStores.size(); ++i) {
                System.out.println("处理到标准影院：" + i);
                for (int j = 0; j < mismatchStores.size(); ++j) {
                    StoreRel storeRel = new StoreRel();
                    storeRel.setSourceId(mismatchStores.get(j).getSourceId());
                    storeRel.setsStoreId(mismatchStores.get(j).getsStoreId());
                    storeRel.setStoreId(standardStores.get(i).getId() + "");
                    //如果包含关联关系，则生成正负样本
                    if (storeRelSet.contains(storeRel)) {
                        //写入正样本文件(地址距离、名称距离、名称相似度、电话是否相同)
                        double posAddressDistance = DistanceCalculate.getDistance(Double.valueOf(standardStores.get(i).getBd_latitude()),
                                Double.valueOf(standardStores.get(i).getBd_longitude()),
                                Double.valueOf(mismatchStores.get(j).getBd_latitude()),
                                Double.valueOf(mismatchStores.get(j).getBd_longitude()));
                        double posNameDistance = DistanceCalculate.getDistance(Double.valueOf(standardStores.get(i).getLatitude()),
                                Double.valueOf(standardStores.get(i).getLongitude()),
                                Double.valueOf(mismatchStores.get(j).getLatitude()),
                                Double.valueOf(mismatchStores.get(j).getLongitude()));
                        float posNameSimilarity = WordDistance.levenshtein(standardStores.get(i).getName(), mismatchStores.get(j).getName());
                        int posTelContain = TelephoneUtil.telContain(standardStores.get(i), mismatchStores.get(j));
                        String posLine = String.valueOf(posAddressDistance) + " " +
                                String.valueOf(posNameDistance) + " " +
                                String.valueOf(posNameSimilarity) + " " +
                                String.valueOf(posTelContain) + "\r\n";
                        posWriter.write(posLine);
                        //写入负样本文件
                        for (int m = 0; m < standardStores.size(); ++m) {
                            if (m == i)
                                continue;
                            double negAddressDistance = DistanceCalculate.getDistance(Double.valueOf(standardStores.get(m).getBd_latitude()),
                                    Double.valueOf(standardStores.get(m).getBd_longitude()),
                                    Double.valueOf(mismatchStores.get(j).getBd_latitude()),
                                    Double.valueOf(mismatchStores.get(j).getBd_longitude()));
                            double negNameDistance = DistanceCalculate.getDistance(Double.valueOf(standardStores.get(m).getLatitude()),
                                    Double.valueOf(standardStores.get(m).getLongitude()),
                                    Double.valueOf(mismatchStores.get(j).getLatitude()),
                                    Double.valueOf(mismatchStores.get(j).getLongitude()));
                            float negNameSimilarity = WordDistance.levenshtein(standardStores.get(m).getName(), mismatchStores.get(j).getName());
                            int negTelContain = TelephoneUtil.telContain(standardStores.get(m), mismatchStores.get(j));
                            String negLine = String.valueOf(negAddressDistance) + " " +
                                    String.valueOf(negNameDistance) + " " +
                                    String.valueOf(negNameSimilarity) + " " +
                                    String.valueOf(negTelContain) + "\r\n";
                            negWriter.write(negLine);
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "generateData success";
    }

}
