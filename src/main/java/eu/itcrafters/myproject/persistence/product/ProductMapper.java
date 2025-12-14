package eu.itcrafters.myproject.persistence.product;

import eu.itcrafters.myproject.controller.product.dto.ProductDto;
import eu.itcrafters.myproject.controller.product.dto.ProductInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(source = "productType.typeName", target = "productType")
    @Mapping(source = "name", target = "productName")
    @Mapping(source = "size", target = "size")
    @Mapping(source = "color", target = "color")
    ProductDto toDto(Product product);

    @InheritConfiguration(name = "toDto")
    @Mapping(source = "id", target = "productId")
    ProductInfo toProductInfo(Product product);

    List<ProductInfo> toProductInfos(List<Product> products);

    @Mapping(source = "productName", target = "name")
    @Mapping(source = "size", target = "size")
    @Mapping(source = "color", target = "color")
    @Mapping(target = "productType", ignore = true)
    Product toProduct(ProductDto productDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @InheritConfiguration(name = "toProduct")
    Product updateProduct(ProductDto productDto, @MappingTarget Product product);
}
