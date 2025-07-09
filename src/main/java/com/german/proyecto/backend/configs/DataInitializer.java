package com.german.proyecto.backend.configs;

import com.german.proyecto.backend.enums.RolesEnum;
import com.german.proyecto.backend.models.entities.RoleEntity;
import com.german.proyecto.backend.models.entities.UserEntity;
import com.german.proyecto.backend.repositories.RoleRepository;
import com.german.proyecto.backend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRole(RoleRepository repository) {
        return args -> {
            if(repository.count() > 0) {
                System.out.println("La tabla de roles de usuario no está vacía. No necesita ser inicializada.");
                return;
            }

            List<RoleEntity> roleList = new ArrayList<RoleEntity>();

            roleList.add(new RoleEntity(RolesEnum.ANONYMOUS.getId(), RolesEnum.ANONYMOUS.getDescription()));
            roleList.add(new RoleEntity(RolesEnum.CUSTOMER.getId(), RolesEnum.CUSTOMER.getDescription()));
            roleList.add(new RoleEntity(RolesEnum.MANAGER.getId(), RolesEnum.MANAGER.getDescription()));
            roleList.add(new RoleEntity(RolesEnum.SYSTEM.getId(), RolesEnum.SYSTEM.getDescription()));
            roleList.add(new RoleEntity(RolesEnum.ADMINISTRATOR.getId(), RolesEnum.ADMINISTRATOR.getDescription()));

            repository.saveAll(roleList);
            System.out.println("La tabla de roles de usuario ha sido inicializada.");
        };

    }

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
