package br.com.fiap.postech.soat.techchallenger4.produtos.repository;

import br.com.fiap.postech.soat.techchallenger4.produtos.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
}
