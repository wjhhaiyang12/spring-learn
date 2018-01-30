package com.example.dbtest.dao;

import com.example.dbtest.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreDao extends JpaRepository<Store, Long> {

    List<Store> findAll();

}
