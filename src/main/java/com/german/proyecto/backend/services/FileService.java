package com.german.proyecto.backend.services;

import com.german.proyecto.backend.models.entities.FileEntity;
import com.german.proyecto.backend.models.entities.ProductEntity;
import com.german.proyecto.backend.repositories.FileRepository;
import com.german.proyecto.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private final FileRepository fileRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FileService(FileRepository fileRepository, ProductRepository productRepository) {
        this.fileRepository = fileRepository;
        this.productRepository = productRepository;
    }

    public void addFiles(String[] names, Integer productId) {
        FileEntity file;
        ProductEntity product = productRepository.findById(productId).orElse(null);
        for (String name : names) {
            file = new FileEntity(name, getFileExtension(name), product);
            fileRepository.save(file);
        }
    }

    private String getFileExtension(String name) {
        return name.substring(name.length() - 3);
    }

}
