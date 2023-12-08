package hu.cymar.tamzol.model;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table (name="users")
public class User {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="firstname", nullable=false)
	private String firstName;
	
	@Column(name="lastname", nullable=false)
	private String lastName;
	
	@Column(name="username", nullable=false, unique=true)
	private String userName;
	
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(name="user_category", nullable=false)
    private UserCategory userCategory=UserCategory.USER;

    @Column(name="status", nullable = false , columnDefinition = "TINYINT default 0")
    private boolean status;
	public User(Long id, String firstName, String lastName, String userName, String password, String email,
			UserCategory userCategory, boolean status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userCategory = userCategory;
		this.status=status;
	}
	public User() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	
	@Transient
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public void setPassword(String password) {
		this.password = passwordEncoder.encode(password);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserCategory getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}



}
