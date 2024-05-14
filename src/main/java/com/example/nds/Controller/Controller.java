package com.example.nds.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nds.Constants.PaginationConstants;
import com.example.nds.Dto.EmployeeDto;
import com.example.nds.Interface.EmployeeInterface;
import com.example.nds.Payload.ApiResponse;
import com.example.nds.Payload.PaginationResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class Controller {
	@Autowired
	EmployeeInterface service;
	
	public static final Logger log = LogManager.getLogger(Controller.class);
	
	ApiResponse errorResponse = new ApiResponse(200, "null", "OK", null);

	@GetMapping("/getData")
	public ResponseEntity<PaginationResponse> getEmployeeData(
		@RequestParam(name="pageNumber",defaultValue = PaginationConstants.PAGE_NUMBER,required = false) Integer pageNumber,
		@RequestParam(name="pageSize",defaultValue = PaginationConstants.PAGE_SIZE,required = false) Integer pageSize,
		@RequestParam(value = "sortBy", defaultValue = PaginationConstants.SORT_BY, required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue = PaginationConstants.SORT_DIR, required = false) String sortDir
			) {
		PaginationResponse response = null;
		try{
			response =this.service.getAllEmployeeData(pageNumber, pageSize, sortBy, sortDir);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e) {
			log.error("Exception Occurred while fetching data"+e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@PostMapping("/saveData")
	public ResponseEntity<ApiResponse> saveEmployeeData(@Valid @RequestBody EmployeeDto employee) {
		try {
			ApiResponse response = this.service.saveEmployeeData(employee);
			if(response.getStatus()==200)
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e) {
			log.error("Error while saving data");
			ApiResponse exceptionResponse = new ApiResponse(200, "null", "OK", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	@PostMapping("/updateData")
	public ResponseEntity<ApiResponse> updateEmployeeData(@RequestBody EmployeeDto employee) {
		ApiResponse updateErrorResponse = new ApiResponse(500,"INTERNAL_SERVER_ERROR","Record not Found!",null);
		try {
			ApiResponse response = this.service.updateEmployeeData(employee);
			if(response.getStatus()==200)
				return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e) {
			log.error("Error while updating data"+e.getMessage());
			ApiResponse exceptionResponse = new ApiResponse(200, "null", "OK", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updateErrorResponse);
	}

	@DeleteMapping("/deleteData/{id}")
	public ResponseEntity<ApiResponse> deleteEmployeeData(@PathVariable int id) {
//		try {
			ApiResponse response = this.service.deleteEmployeeData(id);
//			if(response.getStatus()==200)
				return ResponseEntity.status(HttpStatus.OK).body(response);
//		}
//		catch(Exception e) {
//			log.error("Error while deleting data"+e.getMessage());
//			ApiResponse exceptionResponse = new ApiResponse(200, "null", "OK", e);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
//		}
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
