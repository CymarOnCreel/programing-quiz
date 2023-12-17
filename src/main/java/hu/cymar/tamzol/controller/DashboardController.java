package hu.cymar.tamzol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hu.cymar.tamzol.model.QuizScore;
import hu.cymar.tamzol.model.User;
import hu.cymar.tamzol.service.QuizResultService;

@Controller
public class DashboardController {

	 private static final double PASSING_PERCENTAGE = 75.0;
	@Autowired 
	QuizResultService quizResultservice;
	@GetMapping("dashboard")
	public String newDashBoardView(Model model, HttpSession session) {
		User user=(User) session.getAttribute("user");
		System.out.println(user.getUserName());
		List<QuizScore> quizScores=quizResultservice.findByUser(user);	
		model.addAttribute("quizScores", quizScores);
		model.addAttribute("passingPercentage", PASSING_PERCENTAGE);
	return "dashboard";
	}
}
