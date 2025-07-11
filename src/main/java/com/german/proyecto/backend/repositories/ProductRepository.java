package com.german.proyecto.backend.repositories;

import com.german.proyecto.backend.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findById(Integer productId);

    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.files WHERE p.id = :id")
    Optional<ProductEntity> findByIdWithFiles(@Param("id") Integer productId);
}
