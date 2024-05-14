package com.example.nds.Interface;


import com.example.nds.Dto.EmployeeDto;
import com.example.nds.Payload.ApiResponse;
import com.example.nds.Payload.PaginationResponse;

public interface EmployeeInterface {
public ApiResponse saveEmployeeData(EmployeeDto employee);
public ApiResponse updateEmployeeData(EmployeeDto employee);
public ApiResponse deleteEmployeeData(int id);
public PaginationResponse getAllEmployeeData(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
}
