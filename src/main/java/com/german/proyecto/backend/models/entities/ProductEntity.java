package com.german.proyecto.backend.models.entities;

import com.german.proyecto.backend.exceptions.PriceCanNotBeZeroOrLessException;
import com.german.proyecto.backend.exceptions.StockCanNotBeLessThanZeroException;
import com.german.proyecto.backend.models.dtos.EditProductDto;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    //#region ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price = 0.0;
    private Integer stock = 0;
    private String description;

    private Boolean enabled = true;
    private Instant creationDateTime = Instant.now();

    // Relación inversa. Me permite tener "a mano" todos los archivos asociados al producto.
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<FileEntity> files;
    //#endregion

    //#region CONSTRUCTORES
    public ProductEntity() {};

    public ProductEntity(String name, String description, double price, int stock) throws PriceCanNotBeZeroOrLessException, StockCanNotBeLessThanZeroException {
        this.name = name;
        setPrice(price);
        setStock(stock);
        this.description = description;
    }
    //#endregion

    //#region GETTERS
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getId() {
        return id;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public Instant getCreationDateTime() {
        return creationDateTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    //#endregion

    //#region SETTERS
    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
