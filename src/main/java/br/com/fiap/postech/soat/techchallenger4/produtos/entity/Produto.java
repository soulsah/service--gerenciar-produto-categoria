package br.com.fiap.postech.soat.techchallenger4.produtos.entity;

import br.com.fiap.postech.soat.techchallenger4.produtos.enums.Categoria;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome não pode estar vazio")
    private String nome;

    @NotEmpty(message = "Categoria não pode estar vazia")
    private Categoria categoria;

    @NotEmpty(message = "Preço não pode estar vazio")
    private double preco;

    @NotEmpty(message = "Descrição não pode estar vazia")
    private String descricao;

    private String imagem;
}
