package com.github.delduck.spring_product_api.mapper;

import com.github.delduck.spring_product_api.dto.ProductRequestDTO;
import com.github.delduck.spring_product_api.dto.ProductResponseDTO;
import com.github.delduck.spring_product_api.model.Produto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Produto toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toResponse(Produto product);

    List<ProductResponseDTO> toResponseList(List<Produto> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ProductRequestDTO dto, @MappingTarget Produto produto);


}
