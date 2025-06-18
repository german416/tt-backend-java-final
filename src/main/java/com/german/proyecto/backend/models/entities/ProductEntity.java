package com.german.proyecto.backend.models.entities;

import com.german.proyecto.backend.exceptions.PriceCanNotBeZeroOrLessException;
import com.german.proyecto.backend.exceptions.StockCanNotBeLessThanZeroException;
import com.german.proyecto.backend.models.dtos.EditProductDto;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {
    //#region ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price = 0.0;
    private int stock = 0;
    //#endregion

    //#region CONSTRUCTORES
    public ProductEntity() {};

    public ProductEntity(String name, double price, int stock) throws PriceCanNotBeZeroOrLessException, StockCanNotBeLessThanZeroException {
        this.name = name;
        setPrice(price);
        setStock(stock);
    }
    //#endregion

    //#region GETTERS
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getId() {
        return id;
    }
    //#endregion

    //#region SETTERS
    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

    public void setPrice(double price) throws PriceCanNotBeZeroOrLessException {
        if(price <= 0) {
            throw new PriceCanNotBeZeroOrLessException();
        }

        this.price = price;
    }

    public void setStock(int stock) throws StockCanNotBeLessThanZeroException {
        if(stock < 0) {
            throw new StockCanNotBeLessThanZeroException();
        }

        this.stock = stock;
    }
    //#endregion

    //#region MÉTODOS PÚBLICOS
    public ProductEntity update(EditProductDto newData) throws StockCanNotBeLessThanZeroException, PriceCanNotBeZeroOrLessException {
        name = newData.name;
        setPrice(newData.price);
        setStock(newData.stock);

        return this;
    }
    //#endregion
}
