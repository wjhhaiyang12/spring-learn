package com.example.dbtest.dao;

import com.example.dbtest.model.StoreRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRelDao extends JpaRepository<StoreRel, Long> {

    List<StoreRel> findAll();

}
