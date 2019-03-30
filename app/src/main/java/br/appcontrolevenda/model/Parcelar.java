package br.appcontrolevenda.model;

import java.io.Serializable;

public class Parcelar implements Serializable {
    private int idParcelar;
    private int qtdParcelas;
    private  int idPedido;

    public Parcelar(int idParcelar, int qtdParcelas, int idPedido) {
        this.idParcelar = idParcelar;
        this.qtdParcelas = qtdParcelas;
        this.idPedido = idPedido;
    }

    public Parcelar() {
    }

    public int getIdParcelar() {
        return idParcelar;
    }

    public void setIdParcelar(int idParcelar) {
        this.idParcelar = idParcelar;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
