package com.techlab.article.model;

import com.techlab.article.util.ValidateUtil;

public class ElectronicProduct extends Product {
    private int monthsOfWarranty;

    public ElectronicProduct(String code, String name, double price, Category category,
            int monthsOfWarranty) {
        super(code, name, price, category);
        if (category != null && category.getProductType() != ProductType.ELECTRONIC) {
            throw new IllegalArgumentException(
                    "La categoría debe ser tipo de producto '" + ProductType.ELECTRONIC.getDescription() + "'");
        }
        this.monthsOfWarranty = ValidateUtil.monthsOfWarranty(monthsOfWarranty);
    }

    public void setWarranty(int monthsOfWarranty) {
        this.monthsOfWarranty = ValidateUtil.monthsOfWarranty(monthsOfWarranty);
    }

    public int getMonthsOfWarranty() {
        return this.monthsOfWarranty;
    }

    public String helpDeskPhoneNumber() {
        return "+54 11 1234-5678";
    }

    @Override
    public ProductType getProductType() {
        return ProductType.ELECTRONIC;
    }

    @Override
    public double calculateFinalPrice () {
        if (this.monthsOfWarranty >= 24) {
            return super.getPrice() * 1.2;
        }
        if (this.monthsOfWarranty >= 12){
            return super.getPrice() * 1.1;
        }
        return super.getPrice();
    }

    @Override
    public String getSpecificDetail() {
        return "Garantía: " + getMonthsOfWarranty() + " meses" +
                " | Soporte telefónico: " + helpDeskPhoneNumber();
    }
}
