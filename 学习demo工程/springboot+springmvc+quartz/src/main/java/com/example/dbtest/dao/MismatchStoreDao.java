package com.example.dbtest.dao;

import com.example.dbtest.model.MismatchStore;
import com.example.dbtest.model.MismatchStoreKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MismatchStoreDao extends JpaRepository<MismatchStore, MismatchStoreKey> {

    List<MismatchStore> findAll();

}
