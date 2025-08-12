package com.german.proyecto.backend.models.dtos.responses;

import com.german.proyecto.backend.models.entities.ProductEntity;

import java.util.ArrayList;

public class GetProductsResponse {
    private ArrayList<GetProductResponse> products;

    public GetProductsResponse(ArrayList<ProductEntity> products) {
        if(!products.isEmpty()) {
            this.products = new ArrayList<GetProductResponse>();

            for (ProductEntity product : products) {
                this.products.add(new GetProductResponse(product));
            }
        }
    }

    public ArrayList<GetProductResponse> getProducts() {
        return products;
    }
}
