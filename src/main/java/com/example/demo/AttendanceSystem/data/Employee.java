package com.example.demo.AttendanceSystem.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Employee {

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("hometown")
	private String hometown;

	@JsonProperty("joining_month")
	private String joiningMonth;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

}