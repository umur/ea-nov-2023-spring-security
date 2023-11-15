package edu.miu.springsecurity1.repository;

import edu.miu.springsecurity1.entity.Role;
import edu.miu.springsecurity1.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {

    List<Role> findByRoleIn(List<String> roles);
}