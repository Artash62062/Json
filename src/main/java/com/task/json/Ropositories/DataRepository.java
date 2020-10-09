package com.task.json.Ropositories;

import com.task.json.Model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<Data,Integer> {
    List<Data> findAll();
    Data findById (int id);
}
