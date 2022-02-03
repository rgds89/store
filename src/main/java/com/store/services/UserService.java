package com.store.services;

import com.store.entities.User;
import com.store.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    public List<User> findAll() { return userRespository.findAll(); }

    public User findById(Long id) {
        Optional<User> user = userRespository.findById(id);
        return user.get();
    }

    public User insert (User obj){
        return userRespository.saveAndFlush(obj);
    }
}
