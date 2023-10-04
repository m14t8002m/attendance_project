package com.example.demo.AttendanceSystem.repository;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.AttendanceSystem.data.Clock;
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

	public void postEmployee(String postName, String postHometown, String postJoiningMonth) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";

		String postJson = '{'
				+ "\"body\": \"{"
				+ "\\\"name\\\":\\\"" + postName + "\\\","
				+ "\\\"hometown\\\":\\\"" + postHometown + "\\\","
				+ "\\\"joining_month\\\":\\\"" + postJoiningMonth + "\\\""
				+ "}\""
				+ '}';

		RequestEntity<String> request = RequestEntity.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.body(postJson);

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(request, String.class);

	}

	public void postClock(String employeeId, String clockIn, String breakStart, String breakEnd, String clockOut)
			throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";

		String postJson = '{'
				+ "\"body\": \"{"
				+ "\\\"employee_id\\\":\\\"" + employeeId + "\\\","
				+ "\\\"clock_in\\\":\\\"" + clockIn + "\\\","
				+ "\\\"break_start\\\":\\\"" + breakStart + "\\\","
				+ "\\\"break_end\\\":\\\"" + breakEnd + "\\\","
				+ "\\\"clock_out\\\":\\\"" + clockOut + "\\\""
				+ "}\""
				+ '}';

		RequestEntity<String> request = RequestEntity.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.body(postJson);

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(request, String.class);

	}

}