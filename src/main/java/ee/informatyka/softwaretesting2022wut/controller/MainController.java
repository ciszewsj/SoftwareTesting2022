package ee.informatyka.softwaretesting2022wut.controller;

import ee.informatyka.softwaretesting2022wut.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@Slf4j
@RequiredArgsConstructor
public class MainController {

	private final UserService userService;

	@GetMapping
	public String getMainView() {
		return "main";
	}

	@GetMapping("/cart")
	public String getShoppingCartView() {
		return "";
	}

	@GetMapping("/login")
	public String loginView() {
		return "";
	}

	@GetMapping("/logout")
	public String logoutView() {
		return "";
	}

	@GetMapping("/register")
	public String registerView() {
		return "";
	}

}
