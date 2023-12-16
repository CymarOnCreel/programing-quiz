package hu.cymar.tamzol.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.cymar.tamzol.model.QuestionCategory;
import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.service.CategoriesService;

@Controller
public class QuizFilterController {

	@Autowired
	CategoriesService catService;

	@GetMapping("/selectCategory")
	public String getCategories(Model model) {
		List<QuestionCategory> categories = catService.getAllCategories();
		model.addAttribute("categories", categories);
		return "selectCategory";
	}

	@GetMapping("selectSubcategories")
	public String getSubcategories(Model model, HttpSession session) {
		populateSubcategories(model, session);
		model.addAttribute("error", "Select at least one subcategory!!!");
		return "selectSubcategories";

	}

	@PostMapping("/selectSubcategories")
	public String getSubcategories(Model model, @RequestParam(name = "id", required = false) Long id,
			HttpSession session) {
		boolean categoryChosen = id != null;
		model.addAttribute("categoryChosen", categoryChosen);
		session.setAttribute("selectedCategory", catService.getCategoryById(id));
		populateSubcategories(model, session);
		return "selectSubcategories";
	}

	private void populateSubcategories(Model model, HttpSession session) {
		QuestionCategory selectedCategory = (QuestionCategory) session.getAttribute("selectedCategory");
		model.addAttribute("selectedCategory", selectedCategory);
		List<QuestionSubcategory> subcategories = catService.getSubcategoriesByCategoryId(selectedCategory.getId());
		model.addAttribute("subcategories", subcategories);
	}
}
