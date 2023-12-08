package hu.cymar.tamzol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cymar.tamzol.model.User;
import hu.cymar.tamzol.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public User getUserByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}
	
	public void saveUser(User userObj) {
		userRepo.save(userObj);
	}
}
