package com.example.softwaretesting.test.bdd.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.example.softwaretesting.data.request.LoginRequest;
import com.example.softwaretesting.data.request.RegisterRequest;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

import java.util.List;

public class RegisterSteps extends AbstractSteps implements En {

	public RegisterSteps() {

		Given("user wants to register using data", (DataTable employeeDt) -> {
			testContext().reset();
			List<RegisterRequest> employeeList = employeeDt.asList(LoginRequest.class);

			super.testContext()
					.setPayload(employeeList.get(0));
		});

		When("user register with credential", () -> {
			String createEmployeeUrl = "/auth/register";

			executePost(createEmployeeUrl);
		});


	}
}