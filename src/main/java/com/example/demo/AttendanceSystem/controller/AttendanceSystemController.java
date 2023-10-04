package com.example.demo.AttendanceSystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.AttendanceSystem.data.Clock;
import com.example.demo.AttendanceSystem.data.Employee;
import com.example.demo.AttendanceSystem.service.AttendanceSystemService;

@Controller
public class AttendanceSystemController {

	private final AttendanceSystemService attendanceSystemService;

	public AttendanceSystemController(AttendanceSystemService attendanceSystemService) {
		this.attendanceSystemService = attendanceSystemService;
	}

	@GetMapping
	public String getEmployees(Model model) throws IOException {

		List<Employee> employeesList = attendanceSystemService.getEmployees();

		model.addAttribute("employeesList", employeesList);

		return "index.html";

	}

	@GetMapping("/employee_new")
	public String newEmployee() throws IOException {

		return "employee_new.html";

	}

	@PostMapping("/employee_detail")
	public String getDetail(@RequestParam(value = "selectId") int getId, Model model) throws IOException {

		List<Employee> employeeDetail = attendanceSystemService.getEmployeeDetail(getId);
		List<Clock> clockResult = attendanceSystemService.getClockResult(getId);

		model.addAttribute("employeeDetail", employeeDetail);
		model.addAttribute("clockResult", clockResult);

		return "employee_detail.html";

	}

	@PostMapping("/employee_post")
	public String postEmployee(@RequestParam("postName") String postName,
			@RequestParam("postHometown") String postHometown,
			@RequestParam("postJoiningMonth") String postJoiningMonth) throws IOException {

		attendanceSystemService.postEmployee(postName, postHometown, postJoiningMonth);

		return "redirect:/";

	}

	@PostMapping("/clock_post")
	public String postClock(@RequestParam(value = "actionName") String actionName,
			@RequestParam("employeeId") String employeeId) throws IOException {

		attendanceSystemService.postClock(actionName, employeeId);

		return "redirect:/";

	}

}