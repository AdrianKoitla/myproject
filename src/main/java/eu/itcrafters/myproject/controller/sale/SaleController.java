package eu.itcrafters.myproject.controller.sale;

import eu.itcrafters.myproject.controller.sale.dto.SaleCreateRequestDto;
import eu.itcrafters.myproject.controller.sale.dto.SaleResponseDto;
import eu.itcrafters.myproject.persistence.customer.Customer;
import eu.itcrafters.myproject.persistence.customer.CustomerRepository;
import eu.itcrafters.myproject.persistence.product.Product;
import eu.itcrafters.myproject.persistence.product.ProductRepository;
import eu.itcrafters.myproject.persistence.sale.Sale;
import eu.itcrafters.myproject.persistence.sale.SaleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales")
@Tag(name = "Sales", description = "Sales management endpoints")
public class SaleController {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public SaleController(
            SaleRepository saleRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Operation(
            summary = "Create a new sale",
            description = "Creates a sale for a given product and customer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sale successfully created"),
                    @ApiResponse(responseCode = "404", description = "Product or customer not found")
            }
    )
    @PostMapping
    public SaleResponseDto createSale(@RequestBody SaleCreateRequestDto request) {

        Product product = productRepository
                .findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Customer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Sale sale = new Sale();
        sale.setProduct(product);
        sale.setCustomer(customer);
        sale.setSaleDate(LocalDate.now());
        sale.setSalePrice(request.getSalePrice());

        Sale savedSale = saleRepository.save(sale);

        return mapToResponse(savedSale);
    }

    @Operation(summary = "List all sales")
    @GetMapping
    public List<SaleResponseDto> findAll() {
        return saleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SaleResponseDto mapToResponse(Sale sale) {
        SaleResponseDto response = new SaleResponseDto();
        response.setId(sale.getId());
        response.setSaleDate(sale.getSaleDate());
        response.setSalePrice(sale.getSalePrice());
        response.setProductName(sale.getProduct().getName());
        response.setCustomerName(
                sale.getCustomer().getFirstName() + " " +
                        sale.getCustomer().getLastName()
        );
        return response;
    }
}