package br.appcontrolevenda.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private double valorTotal;
    private EStatus statusPedido;
    private Cliente cliente;
    private List <Parcelar> parcelas;
    private List<ItensPedido> itensPedidos ;



    public Pedido(int idPedido, double valorTotal, EStatus statusPedido, Cliente cliente, List<Parcelar> parcelas, List<ItensPedido> itensPedidos) {
        this.idPedido = idPedido;
        this.valorTotal = valorTotal;
        this.statusPedido = statusPedido;
        this.cliente = cliente;
        this.parcelas = parcelas;
        this.itensPedidos = itensPedidos;


    }

    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public EStatus getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(EStatus statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Parcelar> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcelar> parcelas) {
        this.parcelas = parcelas;
    }

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) { this.itensPedidos = itensPedidos; }

    public void setItensPedidos(ArrayList<ItensPedido> itensPedidos) { this.itensPedidos = itensPedidos; }
}
