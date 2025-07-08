package com.german.proyecto.backend.initializers;

import com.german.proyecto.backend.enums.RolesEnum;
import com.german.proyecto.backend.models.entities.RoleEntity;
import com.german.proyecto.backend.models.entities.UserEntity;
import com.german.proyecto.backend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserInitializer {
    @Bean
    CommandLineRunner initUser(UserRepository repository) {
        return args -> {
            if(repository.count() > 0) {
                System.out.println("No es necesario inicializar la tabla de usuarios.");
                return;
            }

            RoleEntity role = new RoleEntity(RolesEnum.ADMINISTRATOR.getId(), RolesEnum.ADMINISTRATOR.getDescription());
            UserEntity user = new UserEntity("Owner","","owner@system.com", "1234", role);
            repository.save(user);
            System.out.println("Tabla de usuarios inicializada.");
        };
    }
}
