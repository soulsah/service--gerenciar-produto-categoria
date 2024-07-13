package br.com.fiap.postech.soat.techchallenger4.produtos.controller;

import br.com.fiap.postech.soat.techchallenger4.produtos.exception.ProdutoNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.produtos.records.ProdutoRecord;
import br.com.fiap.postech.soat.techchallenger4.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoRecord>> getAllProdutos() {
        try {
            List<ProdutoRecord> produtos = produtoService.findAllProdutos();
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        } catch (ProdutoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRecord> getProdutoById(@PathVariable Long id) {
        try {
            ProdutoRecord produto = produtoService.findProdutoById(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (ProdutoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoRecord> createProduto(@RequestBody ProdutoRecord produtoRecord) {
        ProdutoRecord createdProduto = produtoService.createProduto(produtoRecord);
        return new ResponseEntity<>(createdProduto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRecord> updateProduto(@PathVariable Long id, @RequestBody ProdutoRecord produtoRecord) {
        try {
            ProdutoRecord updateProduto = produtoService.updateProduto(id, produtoRecord);
            return new ResponseEntity<>(updateProduto, HttpStatus.OK);
        } catch (ProdutoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
