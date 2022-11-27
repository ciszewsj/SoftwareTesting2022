package com.example.softwaretesting.test.bdd.stepdefs;

import cucumber.api.java8.En;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class CommonSteps extends AbstractSteps implements En {
	public CommonSteps() {

		Then("the status {string}", (String expectedResult) -> {
			Response response = testContext().getResponse();

			switch (expectedResult) {
				case "IS SUCCESSFUL":
					assertThat(response.statusCode()).isIn(200, 201);
					break;
				case "HANDLE_FAILS":
					assertThat(response.statusCode()).isBetween(400, 409);
					break;
				case "UNAUTHORIZED":
					assertThat(response.statusCode()).isEqualTo(403);
					break;
				default:
					fail("Unexpected error");
			}
		});
	}

}
