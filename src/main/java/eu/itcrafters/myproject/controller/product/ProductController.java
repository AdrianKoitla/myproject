package eu.itcrafters.myproject.controller.product;


import eu.itcrafters.myproject.controller.product.dto.ProductDto;
import eu.itcrafters.myproject.infrastructure.rest.error.ApiError;
import eu.itcrafters.myproject.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{productId}")
    @Operation(summary = "Find product by id", description = "Finds product by id or throws an error if no product found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),

    })
    public ProductDto findProduct(@PathVariable Integer productId){
        productService.findProduct(productId);
        return productService.findProduct(productId);

    }

}
