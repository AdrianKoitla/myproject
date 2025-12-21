package eu.itcrafters.myproject.controller.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request payload for creating a customer")
public class CustomerCreateRequestDto {

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    @Schema(description = "Customer's first name", example = "John", required = true)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    @Schema(description = "Customer's last name", example = "Doe", required = true)
    private String lastName;

    @Size(max = 20, message = "Phone number must be less than 20 characters")
    @Schema(description = "Customer's phone number", example = "123456789")
    private String phone;
}