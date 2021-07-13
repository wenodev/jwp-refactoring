package kitchenpos.product.dto;

import java.math.BigDecimal;

public class ProductRequestModel {
    private String name;
    private BigDecimal price;

    public ProductRequestModel() {
    }

    public ProductRequestModel(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
