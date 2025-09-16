package com.github.delduck.spring_product_api.service;

import com.github.delduck.spring_product_api.exceptions.RecursoNaoEncontratoException;
import com.github.delduck.spring_product_api.model.Produto;
import com.github.delduck.spring_product_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontratoException("Produto com ID " + id + " não encontrado."));
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        if(!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontratoException("Produto com ID " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

}
