package com.ea.dataone.repository;

import com.ea.dataone.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ListCrudRepository<User, Long> {

    User findByEmail(String email);
}
