package eu.itcrafters.myproject.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    NO_PRODUCT_FOUND("Product not found");

    private final String message;
}
