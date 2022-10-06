package ee.informatyka.softwaretesting2022wut.controller;

import ee.informatyka.softwaretesting2022wut.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
public class MainController {

	@Autowired
	public UserService userService;

	@GetMapping
	public String getMainView() {
//		User user = new User();
//		user.email = ("1234@o2.pl");
//		user.username = ("1234");
//		userService.create(user);
//		log.error("Detected user : " + userService.findAll().size());

		return "main";
	}
}
