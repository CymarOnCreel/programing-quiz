package hu.cymar.tamzol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.cymar.tamzol.model.QuestionSubcategory;
import hu.cymar.tamzol.service.CategoriesService;

@Controller
public class GetSubcategories {

	@Autowired
	CategoriesService categoriesService;
	
	
}
