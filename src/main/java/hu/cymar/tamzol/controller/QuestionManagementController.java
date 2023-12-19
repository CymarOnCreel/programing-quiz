package hu.cymar.tamzol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.cymar.tamzol.model.Question;
import hu.cymar.tamzol.service.QuestionService;

@Controller
//@RequestMapping("questionManagement")
public class QuestionManagementController {

	@Autowired
	QuestionService qs;

	@GetMapping("questionManagement")
	public String questionManagementView(Model model) {
		List<Question> all = qs.getAll();
		model.addAttribute("questions", all);
		return "questionManagement";
	}
	
	@DeleteMapping("/questionManagement/{id}")
	 public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
      
        try {
            qs.deleteQuestionById(id);
            return ResponseEntity.ok("Question deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting question");
        }
    }

}
