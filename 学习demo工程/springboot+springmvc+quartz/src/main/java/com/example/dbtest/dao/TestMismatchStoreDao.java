package com.example.dbtest.dao;

import com.example.dbtest.model.MismatchStore;
import com.example.dbtest.model.MismatchStoreKey;
import com.example.dbtest.model.TestMismatchStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestMismatchStoreDao extends JpaRepository<TestMismatchStore, MismatchStoreKey> {

    List<TestMismatchStore> findAll();

}
