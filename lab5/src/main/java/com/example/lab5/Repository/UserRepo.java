package com.example.lab5.Repository;

import com.example.lab5.Model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends ListCrudRepository<User,Integer> {
    User findById(int id);


    Optional<User> findByEmail(String email);

    void deleteById(int id);
}
