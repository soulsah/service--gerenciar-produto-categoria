package br.com.fiap.postech.soat.techchallenger4.produtos.service.impl;

import br.com.fiap.postech.soat.techchallenger4.produtos.entity.Produto;
import br.com.fiap.postech.soat.techchallenger4.produtos.exception.ProdutoNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.produtos.mapper.ProdutoMapper;
import br.com.fiap.postech.soat.techchallenger4.produtos.records.ProdutoRecord;
import br.com.fiap.postech.soat.techchallenger4.produtos.repository.ProdutoRepository;
import br.com.fiap.postech.soat.techchallenger4.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoRecord> findAllProdutos() throws ProdutoNotFoundException {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            throw new ProdutoNotFoundException();
        }
        return produtos.stream()
                .map(ProdutoMapper::mapToRecord)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoRecord findProdutoById(Long id) throws ProdutoNotFoundException {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(ProdutoNotFoundException::new);
        return ProdutoMapper.mapToRecord(produto);
    }

    @Override
    public ProdutoRecord createProduto(ProdutoRecord produtoRecord) {
        Produto produto = ProdutoMapper.mapFromRecord(produtoRecord);
        Produto saveProduto = produtoRepository.save(produto);
        return ProdutoMapper.mapToRecord(saveProduto);
    }

    @Override
    public void deleteProduto(Long id) {
        try {
            produtoRepository.findById(id)
                    .orElseThrow(ProdutoNotFoundException::new);
        } catch (ProdutoNotFoundException e) {
            throw new RuntimeException(e);
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public ProdutoRecord updateProduto(Long id, ProdutoRecord produtoRecord) throws ProdutoNotFoundException {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(ProdutoNotFoundException::new);
        produto.setNome(produtoRecord.nome());
        produto.setCategoria(produtoRecord.categoria());
        produto.setPreco(produtoRecord.preco());
        produto.setDescricao(produtoRecord.descricao());
        produto.setImagem(produtoRecord.imagem());
        Produto updateProduto = produtoRepository.save(produto);
        return ProdutoMapper.mapToRecord(updateProduto);
    }
}
