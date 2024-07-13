package br.com.fiap.postech.soat.techchallenger4.produtos.mapper;

import br.com.fiap.postech.soat.techchallenger4.produtos.entity.Produto;
import br.com.fiap.postech.soat.techchallenger4.produtos.records.ProdutoRecord;

public class ProdutoMapper {

    public static ProdutoRecord mapToRecord(Produto produto) {
        return new ProdutoRecord(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getDescricao(),
                produto.getImagem()
        );
    }

    public static Produto mapFromRecord(ProdutoRecord produtoRecord) {
        return new Produto(
                produtoRecord.id(),
                produtoRecord.nome(),
                produtoRecord.categoria(),
                produtoRecord.preco(),
                produtoRecord.descricao(),
                produtoRecord.imagem()
        );
    }
}
