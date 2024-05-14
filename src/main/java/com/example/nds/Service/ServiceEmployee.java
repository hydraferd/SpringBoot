package com.example.nds.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.nds.Dao.EmployeeDao;
import com.example.nds.Dto.EmployeeDto;
import com.example.nds.Entity.Employee;
import com.example.nds.Exceptions.ResourceNotFoundException;
import com.example.nds.Interface.EmployeeInterface;
import com.example.nds.Payload.ApiResponse;
import com.example.nds.Payload.PaginationResponse;
import com.example.nds.Constants.HttpStatus;

@Service
public class ServiceEmployee implements EmployeeInterface{
@Autowired
ModelMapper modelMapper;
@Autowired 
EmployeeDao employeRepo;
public static final Logger log = LogManager.getLogger(ServiceEmployee.class);
	@Override
	public ApiResponse saveEmployeeData(EmployeeDto employee) {
		HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
		try {
			HttpStatus status = HttpStatus.OK;
			Employee employeeDetails = this.EmployeeDtoToEmployee(employee);
			Employee details = employeRepo.save(employeeDetails);
			employeeDetails.setId(details.getId());
			log.info("Details saved in DB");
		    return this.setApiResponse(status.getCode(), "null", "Data Saved", employeeDetails);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.error("Error while saving employee Data");
		}
		return new ApiResponse(error.getCode(),null,error.getMessage(),null);
	}

	@Override
	public ApiResponse updateEmployeeData(EmployeeDto employee) {
		  HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
		try {
			Employee employeeDetails=this.EmployeeDtoToEmployee(employee);
			Optional<Employee> details=this.employeRepo.findById(employeeDetails.getId());
			if(details.isPresent()) {
				HttpStatus status = HttpStatus.OK;
				Employee info = details.get();
				info.setId(employeeDetails.getId());
				info.setCity(employeeDetails.getCity());
				info.setName(employeeDetails.getName());
				info.setCity(employeeDetails.getCity());
				this.employeRepo.save(info);
				log.info("Entity Data Updated");
				return this.setApiResponse(status.getCode(), "null", "Details Updated", info);
			}
		}
		catch(Exception e) {
		  e.printStackTrace();
		  log.error("Error while updating Data");
		  return this.setApiResponse(error.getCode(), e.getMessage(), error.getMessage(), e);
		}
		  return this.setApiResponse(error.getCode(),"null" ,"No Details with this id is present!", null);
	}

	@Override
	public ApiResponse deleteEmployeeData(int id) {
		
			HttpStatus status = HttpStatus.OK;
			Employee details = this.employeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "Id", id));
			employeRepo.delete(details);
			return this.setApiResponse(status.getCode(), "null", "Details Deleted", details);
//			if(details.isPresent()) {
//			Employee emp = details.get();
//			this.employeRepo.delete(emp);
//			log.info("Employee Details Deleted");
//			return this.setApiResponse(status.getCode(), "null", "Details Deleted", emp);
//			}
//			else {
//				log.error("User not found");
//				throw new ResourceNotFoundException("User","id",id);
//			}
//			HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
//			log.error("Error while deleteing Employee Data");
//			return this.setApiResponse(error.getCode(), "null", error.getMessage(), e);
		
	}

	@Override
	public PaginationResponse getAllEmployeeData(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		try
		{
			Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

	        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

	        Page<Employee> pagePost = employeRepo.getPageableData(p);

	        List<Employee> allPosts = pagePost.getContent();

	        List<EmployeeDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, EmployeeDto.class))
	                .collect(Collectors.toList());

	        PaginationResponse paginationResponse = new PaginationResponse();
            log.info(postDtos);
	        paginationResponse.setContent(postDtos);
	        paginationResponse.setPageNumber(pagePost.getNumber());
	        paginationResponse.setPageSize(pagePost.getSize());
	        paginationResponse.setTotalElements(pagePost.getTotalElements());

	        paginationResponse.setTotalPages(pagePost.getTotalPages());
	        paginationResponse.setLastPage(pagePost.isLast());
	        return paginationResponse;
	}
		catch(Exception e) {
			log.error("Exception occured while getting data from DB "+e.getMessage());
			PaginationResponse paginationResponse = new PaginationResponse();
			return paginationResponse;
		}
	}
	public Employee EmployeeDtoToEmployee(EmployeeDto employeeDto) {
		Employee employee = this.modelMapper.map(employeeDto, Employee.class);
		return employee;
	}
   	
	public EmployeeDto EmployeeToEmployeeDto(Employee employee) {
		EmployeeDto employeeDto = this.modelMapper.map(employee,EmployeeDto.class);
		return employeeDto;
	}
	public ApiResponse setApiResponse(int status,String errorMessage,String message,Object data) {
		 ApiResponse response = new ApiResponse(status,errorMessage,message,data);
		 return response;
	}
}
