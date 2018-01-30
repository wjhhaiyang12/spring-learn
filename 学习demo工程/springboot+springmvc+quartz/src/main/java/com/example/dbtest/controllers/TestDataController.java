package com.example.dbtest.controllers;

import com.example.dbtest.baidu.DistanceCalculate;
import com.example.dbtest.baidu.TelephoneUtil;
import com.example.dbtest.baidu.WordDistance;
import com.example.dbtest.dao.MismatchStoreDao;
import com.example.dbtest.dao.StoreDao;
import com.example.dbtest.dao.StoreRelDao;
import com.example.dbtest.dao.TestMismatchStoreDao;
import com.example.dbtest.model.Store;
import com.example.dbtest.model.TestMismatchStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.util.List;

@RestController
public class TestDataController {

    @Autowired
    StoreDao storeDao;

    @Autowired
    MismatchStoreDao mismatchStoreDao;

    @Autowired
    StoreRelDao storeRelDao;

    @Autowired
    TestMismatchStoreDao testMismatchStoreDao;

    @RequestMapping(value = "/generateTestData")
    public String generateTestData(){
        List<TestMismatchStore> testMismatchStores = testMismatchStoreDao.findAll();
        List<Store> standardStores = storeDao.findAll();
        TestMismatchStore testMismatchStore = testMismatchStores.get(0);

        try {
            String testFile = "D:\\正负样本数据\\testdata.txt";
            FileWriter testWriter = new FileWriter(testFile, true);
            for (int i = 0; i < standardStores.size(); ++i) {
                double testAddressDistance = DistanceCalculate.getDistance(Double.valueOf(standardStores.get(i).getBd_latitude()),
                        Double.valueOf(standardStores.get(i).getBd_longitude()),
                        Double.valueOf(testMismatchStore.getBd_latitude()),
                        Double.valueOf(testMismatchStore.getBd_longitude()));
                double testNameDistance = DistanceCalculate.getDistance(Double.valueOf(standardStores.get(i).getLatitude()),
                        Double.valueOf(standardStores.get(i).getLongitude()),
                        Double.valueOf(testMismatchStore.getLatitude()),
                        Double.valueOf(testMismatchStore.getLongitude()));
                float testNameSimilarity = WordDistance.levenshtein(standardStores.get(i).getName(), testMismatchStore.getName());
                int testTelContain = TelephoneUtil.telContain(standardStores.get(i), testMismatchStore.convertToMismatchStore());
                String testLine = String.valueOf(testAddressDistance) + " " +
                        String.valueOf(testNameDistance) + " " +
                        String.valueOf(testNameSimilarity) + " " +
                        String.valueOf(testTelContain) + "\r\n";
                testWriter.write(testLine);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "generate test data success";
    }
}
