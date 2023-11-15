package com.security.lab.Repository;

import com.security.lab.Entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<User, Integer> {

    Optional<User> findUserByUsernameAndPassword(String userName, String password);

    User findByUsername(String username);
}
