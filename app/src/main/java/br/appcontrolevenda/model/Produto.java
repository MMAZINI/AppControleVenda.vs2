package br.appcontrolevenda.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private int idProduto;
    private String nomeProduto;
    private String descricao;
    private String marca;
    private double preco;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int idProduto, String nomeProduto, String descricao, String marca, double preco, Categoria categoria) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.marca = marca;
        this.preco = preco;
        this.categoria = categoria;


    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
