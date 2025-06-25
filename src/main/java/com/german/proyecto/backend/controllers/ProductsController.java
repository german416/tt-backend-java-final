package com.german.proyecto.backend.controllers;

import com.german.proyecto.backend.exceptions.PriceCanNotBeZeroOrLessException;
import com.german.proyecto.backend.exceptions.ProductNotFoundException;
import com.german.proyecto.backend.exceptions.StockCanNotBeLessThanZeroException;
import com.german.proyecto.backend.models.dtos.EditProductDto;
import com.german.proyecto.backend.models.entities.ProductEntity;
import com.german.proyecto.backend.models.requests.PostProductRequest;
import com.german.proyecto.backend.models.requests.PutProductRequest;
import com.german.proyecto.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    private final ProductService service;

    @Autowired
    public ProductsController(ProductService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> create(@PathVariable(required = false) Integer id, @RequestBody(required = false) PutProductRequest request) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro id es obligatorio.");
        }

        if(request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El content es obligatorio.");
        }

        try {
            ProductEntity updatedProduct;
            EditProductDto data = new EditProductDto(request);
            updatedProduct = service.edit(id, data);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no fue encontrado.");
        } catch (PriceCanNotBeZeroOrLessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El precio no puede ser cero (0) o menos.");
        } catch (StockCanNotBeLessThanZeroException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El stock no puede ser menor a 0.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(required = false) Integer id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro id es obligatorio.");
        }

        try {
            ProductEntity product = service.getById(id);
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Recurso eliminado.\n" + product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no fue encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostProductRequest request) throws StockCanNotBeLessThanZeroException, PriceCanNotBeZeroOrLessException {
        ProductEntity product;

        try {
            product = new ProductEntity(request.name, request.description, request.price, request.stock);
        } catch(StockCanNotBeLessThanZeroException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock no puede ser menor que cero (0).");
        } catch (PriceCanNotBeZeroOrLessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price no puede ser menor o igual a cero (0).");
        }

        product = service.add(product);
        return ResponseEntity.status(HttpStatus.OK).body(product);

    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ProductEntity> products = service.getAll();

        if(products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(required = false) Integer id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro id es obligatorio.");
        }

        ProductEntity product = service.getById(id);

        if(product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no fue encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
