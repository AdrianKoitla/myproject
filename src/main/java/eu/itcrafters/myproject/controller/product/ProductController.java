package eu.itcrafters.myproject.controller.product;


import eu.itcrafters.myproject.controller.product.dto.ProductDto;
import eu.itcrafters.myproject.controller.product.dto.ProductInfo;
import eu.itcrafters.myproject.infrastructure.rest.error.ApiError;
import eu.itcrafters.myproject.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    @Operation(summary = "Adds a product", description = "Adds a product. Throws error ´ProductType not found´ if productType is not found from system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "ProductType not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
    })
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);

    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Find product by id", description = "Finds product by id or throws an error if no product found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),

    })
    public ProductDto findProduct(@PathVariable Integer productId) {
        return productService.findProduct(productId);
    }

    @GetMapping("/products")
    @Operation(summary = "Finds all products")
    public List<ProductInfo> findAllProducts() {
        return productService.findAllProducts();
    }


    @PutMapping("/product/{productId}")
    @Operation(summary = "Updates a product", description = "If there are any null value fields, those won´t get updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request body: payload validation failed",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Product does not exist / ProductType not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void updateProduct(@PathVariable Integer productId, @RequestBody @Valid ProductDto productDto) {
        productService.updateProduct(productId, productDto);

    }


    @DeleteMapping("/product/{productId}")
    @Operation(summary = "Deletes a product by its ID",
            description = "Checks if any sale record exists with this product. If yes, sale record is deleted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
    }
}

