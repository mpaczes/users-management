package info.mpaczes.usersmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.mpaczes.usersmanagement.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findById(long id);
}
