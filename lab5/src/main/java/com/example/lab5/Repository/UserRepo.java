package com.example.lab5.Repository;

import com.example.lab5.Model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ListCrudRepository<User,Integer> {
    User findById(int id);


    void deleteById(int id);
}
