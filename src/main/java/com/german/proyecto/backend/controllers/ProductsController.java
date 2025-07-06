package com.german.proyecto.backend.controllers;

import com.german.proyecto.backend.exceptions.PriceCanNotBeZeroOrLessException;
import com.german.proyecto.backend.exceptions.ProductNotFoundException;
import com.german.proyecto.backend.exceptions.StockCanNotBeLessThanZeroException;
import com.german.proyecto.backend.helpers.FilesHelper;
import com.german.proyecto.backend.models.dtos.EditProductDto;
import com.german.proyecto.backend.models.entities.ProductEntity;
import com.german.proyecto.backend.models.requests.PostProductRequest;
import com.german.proyecto.backend.models.requests.PutProductRequest;
import com.german.proyecto.backend.models.responses.PostProductResponse;
import com.german.proyecto.backend.services.FileService;
import com.german.proyecto.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    @Value("${storage.product.images}")
    private String storage;

    private final ProductService productService;
    private final FileService fileService;

    @Autowired
    public ProductsController(ProductService productService, FileService fileService) {
        this.productService = productService;
        this.fileService = fileService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editById(@PathVariable(required = false) Integer id, @RequestBody(required = false) PutProductRequest request) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro id es obligatorio.");
        }

        if(request == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El content es obligatorio.");
        }

        try {
            ProductEntity updatedProduct;
            EditProductDto data = new EditProductDto(request);
            updatedProduct = productService.edit(id, data);
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
    public ResponseEntity<?> deleteById(@PathVariable(required = false) Integer id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro id es obligatorio.");
        }

        try {
            ProductEntity product = productService.getById(id);
            productService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Recurso eliminado.\n" + product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no fue encontrado.");
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@ModelAttribute PostProductRequest request) throws StockCanNotBeLessThanZeroException, PriceCanNotBeZeroOrLessException {
        PostProductResponse response;
        ProductEntity product;
        String[] files;
        try {
            // doy de alta el producto
            product = addProduct(request);
            if(product.getId() == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo dar de alta el producto.");
            }
            response = new PostProductResponse(product);

            // subo archivos
            files = uploadFiles(request.files);
            response.setFiles(files);

            // doy de alta los archivos en la db
            fileService.addFiles(files, response.getId());

            // respondo
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(StockCanNotBeLessThanZeroException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock no puede ser menor que cero (0).");
        } catch (PriceCanNotBeZeroOrLessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price no puede ser menor o igual a cero (0).");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String[] uploadFiles(MultipartFile[] files) throws Exception {
        String[] names = null;

        if(fileExists(files)) {
            names = FilesHelper.upload(files, storage);
        }

        return names;
    }

    private ProductEntity addProduct(PostProductRequest request) throws StockCanNotBeLessThanZeroException, PriceCanNotBeZeroOrLessException {
        ProductEntity product = new ProductEntity(request.name, request.description, request.price, request.stock);
        product = productService.add(product);
        return product;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ProductEntity> products = productService.getAll();

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

        ProductEntity product = productService.getById(id);

        if(product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no fue encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    private Boolean fileExists(MultipartFile[] files) {
        return (files != null && files.length > 0);
    }
}
