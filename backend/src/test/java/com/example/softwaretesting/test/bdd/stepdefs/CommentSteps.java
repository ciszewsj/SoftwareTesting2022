package com.example.softwaretesting.test.bdd.stepdefs;

import com.example.softwaretesting.data.entity.Item;
import com.example.softwaretesting.data.request.AddCommentRequest;
import cucumber.api.java8.En;
import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertTrue;


@Slf4j
public class CommentSteps extends AbstractSteps implements En {
	public CommentSteps() {
		Given("user want to add comment with data {string}", (String expectedString) -> {
			int id = super.testContext().get("productId", Integer.class);
			AddCommentRequest addCommentRequests = new AddCommentRequest();
			addCommentRequests.setComment(expectedString);
			addCommentRequests.setProductId((long) id);
			super.testContext().set("comment", addCommentRequests);
			super.testContext()
					.setPayload(addCommentRequests);
		});
		When("user add comment with data", () -> {
			executePost("/user/comment");
		});
		Then("user check if comment is added", () -> {
			AddCommentRequest comment = super.testContext().get("comment", AddCommentRequest.class);
			Item item = super.testContext().getResponse().body().as(Item.class);
			assertTrue(item.getComments().stream().anyMatch(c -> c.getComment().equals(comment.getComment())));
		});
	}
}
