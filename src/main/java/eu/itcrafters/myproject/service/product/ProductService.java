package eu.itcrafters.myproject.service.product;


import eu.itcrafters.myproject.controller.product.dto.ProductDto;
import eu.itcrafters.myproject.controller.product.dto.ProductInfo;
import eu.itcrafters.myproject.infrastructure.rest.error.Error;
import eu.itcrafters.myproject.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.myproject.persistence.product.Product;
import eu.itcrafters.myproject.persistence.product.ProductMapper;
import eu.itcrafters.myproject.persistence.product.ProductRepository;
import eu.itcrafters.myproject.persistence.producttype.ProductType;
import eu.itcrafters.myproject.persistence.producttype.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductTypeRepository productTypeRepository;

    public void addProduct(ProductDto productDto) {
        ProductType productType = productTypeRepository.findProductTypeBy(productDto.getProductType())
                .orElseThrow(() -> new DataNotFoundException(Error.NO_PRODUCT_TYPE_FOUND.getMessage()));
        Product product = productMapper.toProduct(productDto);
        product.setProductType(productType);
        productRepository.save(product);

    }

    public ProductDto findProduct(Integer productId){
       Product product = productRepository.findById(productId)
               .orElseThrow(() -> new DataNotFoundException(Error.NO_PRODUCT_FOUND.getMessage()));
        return productMapper.toDto(product);

    }

    public List<ProductInfo> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductInfos(products);
    }

}
