package com.example.demo.AttendanceSystem.service;

import java.io.IOException;
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

	public String postTry() throws IOException {

		String postTry = attendanceSystemRepository.postTry();

		return postTry;

	}
}