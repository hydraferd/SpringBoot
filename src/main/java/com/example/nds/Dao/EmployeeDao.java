package com.example.nds.Dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.nds.Entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee,Integer>{
@Query(value="select * from employee",nativeQuery = true)
Page<Employee> getPageableData(Pageable page);
}
