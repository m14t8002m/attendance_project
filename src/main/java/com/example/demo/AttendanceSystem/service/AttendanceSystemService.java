package com.example.demo.AttendanceSystem.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.AttendanceSystem.data.Clock;
import com.example.demo.AttendanceSystem.data.Employee;
import com.example.demo.AttendanceSystem.repository.AttendanceSystemRepository;

@Service
public class AttendanceSystemService {

	private final AttendanceSystemRepository attendanceSystemRepository;

	public AttendanceSystemService(AttendanceSystemRepository attendanceSystemRepository) {
		this.attendanceSystemRepository = attendanceSystemRepository;
	}

	public List<Employee> getEmployees() throws IOException {

		Employee[] employeesList = attendanceSystemRepository.getEmployees();

		return Arrays.asList(employeesList);

	}

	public List<Employee> getEmployeeDetail(int getId) throws IOException {

		Employee[] employeeDetail = attendanceSystemRepository.getEmployeeDetail(getId);

		return Arrays.asList(employeeDetail);

	}

	public List<Clock> getClockResult(int getId) throws IOException {

		Clock[] clockResult = attendanceSystemRepository.getClockResult(getId);

		return Arrays.asList(clockResult);

	}

	public void postEmployee(String postName, String postHometown, String postJoiningMonth) throws IOException {

		if (postJoiningMonth != "") {
			postJoiningMonth += "-01";
		}

		if (postName != "") {
			attendanceSystemRepository.postEmployee(postName, postHometown, postJoiningMonth);
		}

	}

	public void postClock(String actionName, String employeeId) throws IOException {

		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String currentTime = dateFormat.format(currentDate);

		String clockIn = "";
		String breakStart = "";
		String breakEnd = "";
		String clockOut = "";

		switch (actionName) {
		case "出勤":
			clockIn = currentTime;
			break;
		case "休憩開始":
			breakStart = currentTime;
			break;
		case "休憩終了":
			breakEnd = currentTime;
			break;
		case "退勤":
			clockOut = currentTime;
			break;
		}

		attendanceSystemRepository.postClock(employeeId, clockIn, breakStart, breakEnd, clockOut);

	}

}