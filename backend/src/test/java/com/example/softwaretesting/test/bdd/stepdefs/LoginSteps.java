package com.example.softwaretesting.test.bdd.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.example.softwaretesting.data.request.LoginRequest;
import com.example.softwaretesting.data.response.LoginResponse;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
public class LoginSteps extends AbstractSteps implements En {

	public LoginSteps() {

		Given("user wants to login using credentials", (DataTable employeeDt) -> {
			testContext().reset();
			List<LoginRequest> loginRequests = employeeDt.asList(LoginRequest.class);

			super.testContext()
					.setPayload(loginRequests.get(0));
		});


		When("user login to site with credential", () -> {
			String createEmployeeUrl = "/auth/login";

			executePost(createEmployeeUrl);
		});

		And("returned token is not null", () -> {
			Response response = testContext().getResponse();
			log.error("THIS IS STRANGE {} ", response.getBody().as(LoginResponse.class));
			log.error("THIS IS STRANGE {} ", response.getBody().toString());
			LoginResponse loginResponse = response.getBody().as(LoginResponse.class);
			assertThat(loginResponse.getToken() != null && !Objects.equals(loginResponse.getToken(), ""));
		});

	}
}
