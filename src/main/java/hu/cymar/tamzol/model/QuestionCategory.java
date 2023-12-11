package hu.cymar.tamzol.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Other imports...

import java.util.List;
import javax.persistence.OneToMany;

@Entity
@Table(name = "question_categories")
public class QuestionCategory {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(name = "category_name")
 private String categoryName;

 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
 private List<QuestionSubcategory> subcategories;

 public QuestionCategory(Long id, String categoryName, List<QuestionSubcategory> subcategories) {
	super();
	this.id = id;
	this.categoryName = categoryName;
	this.subcategories = subcategories;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getCategoryName() {
	return categoryName;
}

public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}

public List<QuestionSubcategory> getSubcategories() {
	return subcategories;
}

public void setSubcategories(List<QuestionSubcategory> subcategories) {
	this.subcategories = subcategories;
}

public QuestionCategory() {
	super();
}

}
