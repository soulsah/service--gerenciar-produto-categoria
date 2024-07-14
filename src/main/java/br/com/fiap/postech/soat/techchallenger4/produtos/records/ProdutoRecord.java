package br.com.fiap.postech.soat.techchallenger4.produtos.records;

import br.com.fiap.postech.soat.techchallenger4.produtos.enums.Categoria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Valid
public record ProdutoRecord(Long id, @NotEmpty(message = "Nome não pode estar vazio")String nome,
                            Categoria categoria,
                            @NotEmpty(message = "Preço não pode estar vazio") double preco,
                            @NotEmpty(message = "Descrição não pode estar vazia") String descricao,
                            String imagem) {
}
