package edu.miu.jwtsecurity.repository;

import edu.miu.jwtsecurity.model.Role;
import edu.miu.jwtsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
