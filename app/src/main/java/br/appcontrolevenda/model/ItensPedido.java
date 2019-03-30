package br.appcontrolevenda.model;

import java.io.Serializable;

public class ItensPedido implements Serializable {
    private int idItensPedido;
    private int quantidade;
    private Produto produto;
    private int idPedido; // duvidas se tenho que ter esse pedido para pegar seu id

    public ItensPedido(int idItensPedido, int quantidade, Produto produto, int idPedido) {
        this.idItensPedido = idItensPedido;
        this.quantidade = quantidade;
        this.produto = produto;
        this.idPedido = idPedido;
    }

    public ItensPedido() {
    }

    public int getIdItensPedido() {
        return idItensPedido;
    }

    public void setIdItensPedido(int idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
