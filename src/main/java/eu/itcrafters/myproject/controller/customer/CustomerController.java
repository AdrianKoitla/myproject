package eu.itcrafters.myproject.controller.customer;

import eu.itcrafters.myproject.controller.customer.dto.CustomerCreateRequestDto;
import eu.itcrafters.myproject.controller.customer.dto.CustomerResponseDto;
import eu.itcrafters.myproject.infrastructure.rest.error.ApiError;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.customer.Customer;
import eu.itcrafters.myproject.persistence.customer.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "Customer management endpoints")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repository;

    @GetMapping
    @Operation(summary = "Get all customers", description = "Retrieves a list of all customers")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved customers")
    public List<CustomerResponseDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieves a customer by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved customer"),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public CustomerResponseDto findById(@PathVariable Integer id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found with id: " + id));
        return mapToResponseDto(customer);
    }

    @PostMapping
    @Operation(summary = "Create a customer", description = "Creates a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public CustomerResponseDto create(@Valid @RequestBody CustomerCreateRequestDto request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        Customer savedCustomer = repository.save(customer);
        return mapToResponseDto(savedCustomer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer", description = "Updates an existing customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public CustomerResponseDto update(@PathVariable Integer id, @Valid @RequestBody CustomerCreateRequestDto request) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found with id: " + id));

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());

        Customer updatedCustomer = repository.save(customer);
        return mapToResponseDto(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer", description = "Deletes a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Customer not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private CustomerResponseDto mapToResponseDto(Customer customer) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhone(customer.getPhone());
        return dto;
    }
}
