package br.com.fiap.postech.soat.techchallenger4.produtos.service;

import br.com.fiap.postech.soat.techchallenger4.produtos.controller.ProdutoController;
import br.com.fiap.postech.soat.techchallenger4.produtos.exception.ProdutoNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.produtos.records.ProdutoRecord;
import br.com.fiap.postech.soat.techchallenger4.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProdutoService {

    List<ProdutoRecord> findAllProdutos() throws ProdutoNotFoundException;

    ProdutoRecord findProdutoById(Long id) throws ProdutoNotFoundException;

    void deleteProduto(Long id);

    ProdutoRecord updateProduto(Long id, ProdutoRecord produtoRecord) throws ProdutoNotFoundException;

    ProdutoRecord createProduto(ProdutoRecord produtoRecord);
}
