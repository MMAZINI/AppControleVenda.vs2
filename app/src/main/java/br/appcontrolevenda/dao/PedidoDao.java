package br.appcontrolevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.appcontrolevenda.model.Cliente;
import br.appcontrolevenda.model.DataBase;
import br.appcontrolevenda.model.EStatus;
import br.appcontrolevenda.model.ItensPedido;
import br.appcontrolevenda.model.Parcelar;
import br.appcontrolevenda.model.Pedido;

public class PedidoDao extends DaoGenerics<Pedido,Integer> {
    private ItensPedidoDao  itensPedidoDao;
    private ParcelarDao parcelarDao;

    public PedidoDao(Context context) {
        super(context);
        this.itensPedidoDao = new ItensPedidoDao(context);
        this.parcelarDao = new ParcelarDao(context);
    }

    @Override
    public void insert(Pedido obj) {
        try{
            open();
            ContentValues cv = new ContentValues();
            cv.put(DataBase.FIELD_PEDIDO_VALORTOTAL, obj.getValorTotal());
            cv.put(DataBase.FIELD_PEDIDO_STATUS, obj.getStatusPedido().toString());
            cv.put(DataBase.FIELD_PEDIDO_ID_CLIENTE, obj.getCliente().getIdCliente());

            // nao preciso de uma lista;


            getConexao().insert(DataBase.TABLE_CLIENTE,null,cv);
        }
        finally {

        }

    }

    @Override
    public void edtit(Pedido obj) {
        try{
            open();
            ContentValues cv = new ContentValues();
            cv.put(DataBase.FIELD_PEDIDO_VALORTOTAL, obj.getValorTotal());
            cv.put(DataBase.FIELD_PEDIDO_STATUS, obj.getStatusPedido().toString());
            cv.put(DataBase.FIELD_PEDIDO_ID_CLIENTE, obj.getCliente().getIdCliente());

            // nao preciso de uma lista;


            getConexao().update(DataBase.TABLE_PEDIDO,cv,DataBase.FIELD_PEDIDO_ID+"="+obj.getIdPedido(),null);
        }
        finally {

            close();
        }

    }

    @Override
    public void delete(Pedido obj) {

        try{
            open();
            ContentValues cv = new ContentValues();


            getConexao().delete(DataBase.TABLE_PEDIDO,DataBase.FIELD_PEDIDO_ID+"="+obj.getIdPedido(),null);

        }
        finally {
            close();
        }
    }

    @Override
    public List<Pedido> findAll() {
        try {
            open();
            List<Pedido> pedidos = new ArrayList<>();
            String sql = "select * from " + DataBase.TABLE_PEDIDO;

            Cursor c = getConexao().rawQuery(sql, null);

            while (c.moveToNext()) {

                Cliente cli = new Cliente();
                cli.setIdCliente(c.getInt(c.getColumnIndex(DataBase.FIELD_PEDIDO_ID_CLIENTE)));

                Pedido ped = new Pedido();
                ped.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PEDIDO_ID)));
                ped.setValorTotal(c.getDouble(c.getColumnIndex(DataBase.FIELD_PEDIDO_VALORTOTAL)));
                ped.setStatusPedido(EStatus.valueOf(c.getString(c.getColumnIndex(DataBase.FIELD_PEDIDO_STATUS))));
                ped.setItensPedidos(itensPedidoDao.findAllPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PEDIDO_ID))));
                ped.setParcelas(parcelarDao.findAllPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PEDIDO_ID))));

                pedidos.add(ped);
            }

            return pedidos;
        }

        finally{
            close();

        }
    }

    @Override
    public Pedido findById(Integer id) {

        try {
            open();

            String sql = "select * from " + DataBase.TABLE_PEDIDO+" where "+DataBase.FIELD_PEDIDO_ID+" = "+id;

            Cursor c = getConexao().rawQuery(sql, null);

            if(c.moveToNext()) {


                Pedido ped = new Pedido();
                ped.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PEDIDO_ID)));
                ped.setValorTotal(c.getDouble(c.getColumnIndex(DataBase.FIELD_PEDIDO_VALORTOTAL)));
                ped.setStatusPedido(EStatus.valueOf(c.getString(c.getColumnIndex(DataBase.FIELD_PEDIDO_STATUS))));// como eu seto um enum
                ped.setItensPedidos(itensPedidoDao.findAllPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PEDIDO_ID))));
                ped.setParcelas(parcelarDao.findAllPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PEDIDO_ID))));


              return ped;
            }

           else{
                return null;
            }
        }

        finally{
            close();

        }
}

    @Override
    public long count() {
        try{
            open();
            String sql = "select count(*) from "+DataBase.TABLE_PEDIDO;
            Cursor c = getConexao().rawQuery(sql, null);

            c.moveToNext();
            return c.getLong(0);
        }
        finally {
            close();
        }
    }
}
