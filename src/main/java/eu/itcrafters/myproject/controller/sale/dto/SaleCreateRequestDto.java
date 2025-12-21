package eu.itcrafters.myproject.controller.sale.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "Request payload for creating a sale")
public class SaleCreateRequestDto {

    @Schema(description = "ID of the product being sold", example = "1", required = true)
    private Integer productId;

    @Schema(description = "ID of the customer making the purchase", example = "2", required = true)
    private Integer customerId;

    @Schema(description = "Final sale price (after discounts)", example = "79.99", required = true)
    private BigDecimal salePrice;

}
