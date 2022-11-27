package com.example.softwaretesting.test.bdd.stepdefs;

import com.example.softwaretesting.data.request.LoginRequest;
import com.example.softwaretesting.data.request.RegisterRequest;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;

public class RegisterSteps extends AbstractSteps implements En {

	public RegisterSteps() {

		Given("user wants to register using data", (DataTable loginData) -> {
			List<RegisterRequest> employeeList = loginData.asList(LoginRequest.class);

			super.testContext()
					.setPayload(employeeList.get(0));
		});

		When("user register with credential", () -> {
			String createEmployeeUrl = "/auth/register";

			executePost(createEmployeeUrl);
		});


	}
}