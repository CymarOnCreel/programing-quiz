package hu.cymar.tamzol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cymar.tamzol.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 User findByUserName(String userName);
}
