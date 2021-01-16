package info.mpaczes.usersmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.mpaczes.usersmanagement.domain.EnumYesNo;
import info.mpaczes.usersmanagement.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserNameAndPassword(String userName, String password);
	
	User findByAuthenticationId(int authenticationId);
	
	User findByUserName(String userName);
}
