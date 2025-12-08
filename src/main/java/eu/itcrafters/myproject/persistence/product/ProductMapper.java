package eu.itcrafters.myproject.persistence.product;

import eu.itcrafters.myproject.controller.product.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(source = "productType.typeName", target = "productType")
    @Mapping(source = "name", target = "productName")
    @Mapping(source = "size", target = "size")
    @Mapping(source = "color", target = "color")
    ProductDto toDto(Product product);
}
