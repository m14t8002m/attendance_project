package com.example.demo.AttendanceSystem.repository;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.AttendanceSystem.data.Clock;
import com.example.demo.AttendanceSystem.data.ClockTry;
import com.example.demo.AttendanceSystem.data.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AttendanceSystemRepository {

	public Employee[] getEmployees() throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employee[] employeesList = mapper.readValue(json, Employee[].class);

		return employeesList;
	}

	public Employee[] getEmployeeDetail(int getId) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee?id="
				+ String.valueOf(getId);

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employee[] employeeDetail = mapper.readValue(json, Employee[].class);

		return employeeDetail;
	}

	public Clock[] getClockResult(int getId) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock?employeeId="
				+ String.valueOf(getId);

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Clock[] clockResult = mapper.readValue(json, Clock[].class);

		return clockResult;
	}

	public String postTry() throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";

		//		String json = "{"
		//				+ "\"employee_id\":\"1\","
		//				+ "\"clock_in\":\"2023-09-30 09:00:00\","
		//				+ "\"break_start\":\"\","
		//				+ "\"break_end\":\"\","
		//				+ "\"clock_out\":\"\""
		//				+ "}";

		//		RequestEntity<String> request = RequestEntity.post(url)
		//				.contentType(MediaType.APPLICATION_JSON)
		//				.body(json);

		ClockTry clockTry = new ClockTry(1, "2023-09-30 09:00:00", "", "", "");

		RequestEntity<ClockTry> request = RequestEntity.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.body(clockTry);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);

		System.out.println(request);
		System.out.println(response); //結果

		return "try";
	}

}