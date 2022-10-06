package ee.informatyka.softwaretesting2022wut.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@Slf4j
@RequiredArgsConstructor
public class ProfileController {

	@GetMapping
	public String getCreateProfileView() {
		return "";
	}

	@GetMapping("/edit")
	public String getProfileEditView() {
		return "";
	}
}
