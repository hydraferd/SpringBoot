package com.example.nds.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

public class EmployeeDto {
//	@NotNull
	String id;
	@NotBlank
	@Size(min=5 , message="Name cannot be less than 5 characters !!")
	String name;
	@NotNull
	String team;
	@NotNull
	@Size(min = 3, max =20 , message="City name cannot be less than 4 characters !!")
	String city;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
