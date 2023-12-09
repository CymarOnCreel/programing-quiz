package hu.cymar.tamzol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question_subcategories")
public class QuestionSubcategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "subcategory_name", nullable = false)
	private String subcategoryName;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private QuestionCategory category;

	public QuestionSubcategory(Long id, String subcategoryName, QuestionCategory category) {
		super();
		this.id = id;
		this.subcategoryName = subcategoryName;
		this.category = category;
	}

	public QuestionSubcategory() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public QuestionCategory getMainCategory() {
		return category;
	}

	public void setMainCategory(QuestionCategory category) {
		this.category = category;
	}

}
