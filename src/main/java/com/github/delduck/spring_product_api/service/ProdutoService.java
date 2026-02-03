package com.github.delduck.spring_product_api.service;

import com.github.delduck.spring_product_api.dto.ProductRequestDTO;
import com.github.delduck.spring_product_api.dto.ProductResponseDTO;
import com.github.delduck.spring_product_api.exceptions.RecursoNaoEncontratoException;
import com.github.delduck.spring_product_api.mapper.ProductMapper;
import com.github.delduck.spring_product_api.model.Produto;
import com.github.delduck.spring_product_api.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProductMapper productMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProductMapper productMapper) {
        this.produtoRepository = produtoRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return productMapper.toResponseList(produtos);
    }

    public ProductResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException("Produto com ID " + id + " não encontrado."));
        return productMapper.toResponse(produto);
    }

    @Transactional
    public ProductResponseDTO salvarProduto(ProductRequestDTO produtoDTO) {
        Produto produto = productMapper.toEntity(produtoDTO);
        Produto produtoSalvo = produtoRepository.save(produto);
        return productMapper.toResponse(produtoSalvo);
    }

    @Transactional
    public ProductResponseDTO atualizarProduto(Long id, ProductRequestDTO dto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontratoException(
                                "Produto com ID " + id + " não encontrado."
                        )
                );

        productMapper.updateEntityFromDTO(dto, produtoExistente);
        Produto atualizado = produtoRepository.save(produtoExistente);
        return productMapper.toResponse(atualizado);
    }

    @Transactional
    public void deletarProduto(Long id) {
        if(!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontratoException("Produto com ID " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }
}
