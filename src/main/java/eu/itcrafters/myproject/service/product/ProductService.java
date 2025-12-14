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
import eu.itcrafters.myproject.persistence.sale.Sale;
import eu.itcrafters.myproject.persistence.sale.SaleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductTypeRepository productTypeRepository;
    private final SaleRepository saleRepository;

    public void addProduct(ProductDto productDto) {
        ProductType productType = getValidProductType(productDto.getProductType());
        Product product = productMapper.toProduct(productDto);
        product.setProductType(productType);
        productRepository.save(product);

    }

    public ProductDto findProduct(Integer productId){
        Product product = getValidProduct(productId);
        return productMapper.toDto(product);

    }


    public List<ProductInfo> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductInfos(products);
    }

    public void updateProduct(Integer productId, @Valid ProductDto productDto) {
        Product product = getValidProduct(productId);
        ProductType productType = getValidProductType(productDto.getProductType());
        productMapper.updateProduct(productDto, product);
        product.setProductType(productType);
        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Integer productId) {
       Product product = getValidProduct(productId);

       saleRepository.findSaleBy(product).ifPresent(saleRepository::delete);
       productRepository.delete(product);
    }

    private ProductType getValidProductType(String productTypeName) {
        return productTypeRepository.findProductTypeBy(productTypeName)
                .orElseThrow(() -> new DataNotFoundException(Error.NO_PRODUCT_TYPE_FOUND.getMessage()));
    }

    private Product getValidProduct(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException(Error.NO_PRODUCT_FOUND.getMessage()));

    }


}
