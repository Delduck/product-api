package com.github.delduck.spring_product_api.controller;

import com.github.delduck.spring_product_api.dto.ProductRequestDTO;
import com.github.delduck.spring_product_api.dto.ProductResponseDTO;
import com.github.delduck.spring_product_api.model.Produto;
import com.github.delduck.spring_product_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProductResponseDTO> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> criarProduto(@Valid @RequestBody ProductRequestDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.salvarProduto(produtoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO produtoDTO) {
        ProductResponseDTO produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
