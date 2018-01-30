package com.example.dbtest.controllers;

import com.example.dbtest.baidu.DistanceCalculate;
import com.example.dbtest.baidu.WordDistance;
import com.example.dbtest.dao.MismatchStoreDao;
import com.example.dbtest.dao.StoreDao;
import com.example.dbtest.dao.StoreRelDao;
import com.example.dbtest.model.MismatchStore;
import com.example.dbtest.model.Store;
import com.example.dbtest.model.StoreRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class FindFalseController {

    @Autowired
    StoreDao storeDao;

    @Autowired
    MismatchStoreDao mismatchStoreDao;

    @Autowired
    StoreRelDao storeRelDao;

    @RequestMapping(value = "/findfalse")
    public String findFalse(){
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

        String posFile = "C:\\Users\\lenovo\\Desktop\\实习\\影院关联数据\\positive.txt";
        String negFile = "C:\\Users\\lenovo\\Desktop\\实习\\影院关联数据\\negative.txt";
        for(int i = 0; i < standardStores.size(); ++i){
            for(int j = 0; j < mismatchStores.size(); ++j){
                StoreRel storeRel = new StoreRel();
                storeRel.setSourceId(mismatchStores.get(j).getSourceId());
                storeRel.setsStoreId(mismatchStores.get(j).getsStoreId());
                storeRel.setStoreId(standardStores.get(i).getId() + "");
                if(storeRelSet.contains(storeRel)){
                    double posDistance = DistanceCalculate.getDistance(Double.valueOf(standardStores.get(i).getBd_latitude()),
                            Double.valueOf(standardStores.get(i).getBd_longitude()),
                            Double.valueOf(mismatchStores.get(j).getBd_latitude()),
                            Double.valueOf(mismatchStores.get(j).getBd_longitude()));
                    float posNameSimilarity = WordDistance.levenshtein(standardStores.get(i).getName(), mismatchStores.get(j).getName());
                    if(posDistance > 1000.0 && posNameSimilarity != 1.0f){
                        String falseA = standardStores.get(i).getName() + "  " + mismatchStores.get(j).getName();
                        String falseB = standardStores.get(i).getAddress() + "  " + mismatchStores.get(j).getAddress();
                        System.out.println(falseA);
                        System.out.println(falseB);
                        System.out.println("距离：" + posDistance);
                        System.out.println("名称相似度：" + posNameSimilarity);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
            }
        }
        return "find false end";
    }

}
