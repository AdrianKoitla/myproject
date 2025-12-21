package eu.itcrafters.myproject.controller.sale.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Sale response object")
public class SaleResponseDto {

    @Schema(example = "10")
    private Integer id;

    @Schema(example = "T-Shirt")
    private String productName;

    @Schema(example = "John Doe")
    private String customerName;

    @Schema(example = "2025-01-01")
    private LocalDate saleDate;

    @Schema(example = "79.99")
    private BigDecimal salePrice;
}
