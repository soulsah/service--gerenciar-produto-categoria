package br.com.fiap.postech.soat.techchallenger4.produtos.exception;

public class ProdutoNotFoundException extends Exception{

    public ProdutoNotFoundException(){
        super("Produto não encontrado");
    }
}
