package com.german.proyecto.backend.controllers;

import com.german.proyecto.backend.constants.ApiRoutes;
import com.german.proyecto.backend.models.entities.UserEntity;
import com.german.proyecto.backend.models.requests.PostUserRequest;
import com.german.proyecto.backend.models.responses.GenericErrorResponse;
import com.german.proyecto.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiRoutes.USER)
public class UsersController {
    private final UserService service;

    public UsersController(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody PostUserRequest request) {
        try {
            UserEntity user = new UserEntity(request);
            service.add(user);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericErrorResponse(e.getMessage(), e.getStackTrace().toString()));
        }
    }
}
