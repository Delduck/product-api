package com.github.delduck.spring_product_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequestDTO(
        @NotBlank(message = "O nome do produto é obrigatório")
        String nome,

        @NotNull(message = "O preço do produto não pode ser nulo")
        @PositiveOrZero(message = "O preço deve ser maior ou igual a zero")
        Double preco) {
}
