package eu.itcrafters.myproject.controller.product.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductInfo extends ProductDto {
    private Integer productId;


}
