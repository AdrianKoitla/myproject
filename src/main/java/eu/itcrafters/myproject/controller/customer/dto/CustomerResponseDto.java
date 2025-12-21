package eu.itcrafters.myproject.controller.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Response payload for customer data")
public class CustomerResponseDto {

    @Schema(description = "Customer ID", example = "1")
    private Integer id;

    @Schema(description = "Customer's first name", example = "John")
    private String firstName;

    @Schema(description = "Customer's last name", example = "Doe")
    private String lastName;

    @Schema(description = "Customer's phone number", example = "123456789")
    private String phone;
}