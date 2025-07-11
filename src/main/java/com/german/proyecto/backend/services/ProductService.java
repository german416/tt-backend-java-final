package com.german.proyecto.backend.services;

import com.german.proyecto.backend.exceptions.PriceCanNotBeZeroOrLessException;
import com.german.proyecto.backend.exceptions.ProductNotFoundException;
import com.german.proyecto.backend.exceptions.StockCanNotBeLessThanZeroException;
import com.german.proyecto.backend.models.dtos.EditProductDto;
import com.german.proyecto.backend.models.entities.ProductEntity;
import com.german.proyecto.backend.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    public ProductEntity getById(int id) {
        return repository.findByIdWithFiles(id).orElse(null);
    }

    // todo: public ProductEntity getByString(String query) {}

    public ProductEntity edit(Integer id, EditProductDto data) throws ProductNotFoundException, PriceCanNotBeZeroOrLessException, StockCanNotBeLessThanZeroException {
        boolean exist = repository.existsById(id);

        if(!exist) {
            throw new ProductNotFoundException();
        }

        ProductEntity product = repository.findById(id).orElse(null);

        if(product != null) {
            product.update(data);
        }

        repository.save(product);

        return product;
    }

    public void deleteById(int id) throws ProductNotFoundException {
        boolean exist = repository.existsById(id);

        if(!exist) {
            throw new ProductNotFoundException();
        }

        repository.deleteById(id);
    }

    public ProductEntity add(ProductEntity product) {
        return repository.save(product);
    }
}
