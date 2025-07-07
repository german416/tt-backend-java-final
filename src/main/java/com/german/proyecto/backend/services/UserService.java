package com.german.proyecto.backend.services;

import com.german.proyecto.backend.models.entities.UserEntity;
import com.german.proyecto.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity add(UserEntity user) {
        return repository.save(user);
    }


}
