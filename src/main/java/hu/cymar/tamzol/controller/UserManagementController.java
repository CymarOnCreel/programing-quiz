package hu.cymar.tamzol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserManagementController {

	@GetMapping("userManagement")
	public String userManagementView(Model model) {
		return "userManagement";
	}
}
