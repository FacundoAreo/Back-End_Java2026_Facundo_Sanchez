package com.techlab.article.model;

import com.techlab.article.util.ValidateUtil;

public class Category {
    private String code;
    private String name;
    private String description;
    private ProductType productType;

    public Category(String code, String name, String description, ProductType productType) {
        this.code = ValidateUtil.string(code, "Código");
        this.name = ValidateUtil.string(name, "Nombre");
        this.description = ValidateUtil.string(description, "Descripción");
        this.productType = ValidateUtil.productType(productType);
    }

    public void setCode(String code) {
        this.code = ValidateUtil.string(code, "Código");
    }

    public void setName(String name) {
        this.name = ValidateUtil.string(name, "Nombre");
    }

    public void setDescription(String description) {
        this.description = ValidateUtil.string(description, "Descripción");
    }

    public void setProductType(ProductType productType) {
        this.productType = ValidateUtil.productType(productType);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ProductType getProductType() {
        return this.productType;
    }

    @Override
    public String toString() {
        return "Código: " + this.code +
                " | Nombre: " + this.name +
                " | Descripción: " + this.description +
                " | Tipo de producto: " + this.productType.getDescription();
    }
}
