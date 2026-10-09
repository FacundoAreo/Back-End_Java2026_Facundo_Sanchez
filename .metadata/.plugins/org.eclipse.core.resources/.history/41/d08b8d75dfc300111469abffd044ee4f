package com.techlab.article.model;

import com.techlab.article.interfaces.Calculable;
import com.techlab.article.util.ValidateUtil;

public abstract class Product implements Calculable {
    private String code;
    private String name;
    private double price;
    private Category category;

    public Product(String code, String name, double price, Category category) {
        this.code = ValidateUtil.string(code, "Código");
        this.name = ValidateUtil.string(name, "Nombre");
        this.price = ValidateUtil.price(price);
        this.category = ValidateUtil.category(category);
    }

    public void setCode(String code) {
        this.code = ValidateUtil.string(code, "Código");
    }

    public void setName(String name) {
        this.name = ValidateUtil.string(name, "Nombre");
    }

    public void setPrice(double price) {
        this.price = ValidateUtil.price(price);
    }

    public void setCategory(Category category) {
        this.category = ValidateUtil.category(category);
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

    public abstract ProductType getProductType();

    public abstract String getSpecificDetail();

    @Override
    public String toString() {
        return "Código: " + this.code +
                " | Nombre: " + this.name +
                " | Precio Base: $ " + this.price +
                " | Precio Final: $" + this.calculateFinalPrice() +
                " | Detalle: " + this.getSpecificDetail() +
                " | Tipo: " + this.getProductType().getDescription() +
                (this.category != null ? " | Categoría: " + this.category.getName() : "");
    }

}
