package br.appcontrolevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.appcontrolevenda.model.DataBase;
import br.appcontrolevenda.model.ItensPedido;
import br.appcontrolevenda.model.Produto;

//ATENÇÃO NAO SEI SE TERIA QUE TER NO MEU BANCO DE DADOS UMA COLUNA PARA GUARDA O idPedido

public class ItensPedidoDao extends DaoGenerics<ItensPedido, Integer> {
    private ProdutoDao produtoDao;

    public ItensPedidoDao(Context context ) {

        super(context);
        this.produtoDao = new ProdutoDao(context);
    }

    @Override
    public void insert(ItensPedido obj) {
        try{
            open();
            ContentValues cv = new ContentValues();

            cv.put(DataBase.FIELD_ITENSPEDIDO_QUANTIDADE,obj.getQuantidade());
            cv.put(DataBase.FIELD_ITENSPEDIDO_ID_PRODUTO,obj.getProduto().getIdProduto());
            cv.put(DataBase.FIELD_ITENSPEDIDO_ID_PEDIDO,obj.getIdPedido());

            getConexao().insert(DataBase.TABLE_ITENSPEDIDO,null, cv);
        }
        finally {
            close();
        }
    }

    @Override
    public void edtit(ItensPedido obj) {
        try{
            open();
            ContentValues cv = new ContentValues();

            cv.put(DataBase.FIELD_ITENSPEDIDO_QUANTIDADE,obj.getQuantidade());
            cv.put(DataBase.FIELD_ITENSPEDIDO_ID_PRODUTO,obj.getProduto().getIdProduto());
            cv.put(DataBase.FIELD_ITENSPEDIDO_ID_PEDIDO,obj.getIdPedido());

            getConexao().update(DataBase.TABLE_ITENSPEDIDO, cv,DataBase.FIELD_ITENSPEDIDO_ID+" = "+obj.getIdItensPedido(),null );
        }
        finally {
            close();
        }
    }

    @Override
    public void delete(ItensPedido obj) {

        try{
            open();

            getConexao().delete(DataBase.TABLE_ITENSPEDIDO, DataBase.FIELD_ITENSPEDIDO_ID+"="+obj.getIdItensPedido(), null);
        }
        finally {
            close();
        }

    }

    @Override
    public List<ItensPedido> findAll() {
        try{
            open();

            List<ItensPedido> itensPedidos = new ArrayList<>();

            String sql = "select * from "+ DataBase.TABLE_ITENSPEDIDO;
            Cursor c = getConexao().rawQuery(sql, null);

            while (c.moveToNext()){

                ItensPedido i = new ItensPedido();


                i.setIdItensPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID)));
                i.setQuantidade(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_QUANTIDADE)));
                i.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID_PEDIDO)));
                i.setProduto(produtoDao.findById(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID_PRODUTO))));

                itensPedidos.add(i);
            }

            return itensPedidos;
        }
        finally {
            close();
        }
    }

    @Override
    public ItensPedido findById(Integer id) {
        try{
            open();

            String sql = "select * from "+ DataBase.TABLE_ITENSPEDIDO+" where "+DataBase.FIELD_ITENSPEDIDO_ID+ "="+id;
            Cursor c = getConexao().rawQuery(sql, null);

            if (c.moveToNext()){
                ItensPedido i = new ItensPedido();
                i.setIdItensPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID)));
                i.setQuantidade(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_QUANTIDADE)));
                i.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID_PEDIDO)));
                i.setProduto(produtoDao.findById(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID_PRODUTO))));

              return i;
            }

            else
                return null;
        }
        finally {
            close();
        }
    }


    public List<ItensPedido> findAllPedido( int idPedido) {
        try{
            open();

            List<ItensPedido> itensPedidos = new ArrayList<>();

            String sql = "select * from "+ DataBase.TABLE_ITENSPEDIDO +" where "+DataBase.FIELD_ITENSPEDIDO_ID+ "="+idPedido;;
            Cursor c = getConexao().rawQuery(sql, null);

            while (c.moveToNext()){

                ItensPedido i = new ItensPedido();


                i.setIdItensPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID)));
                i.setQuantidade(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_QUANTIDADE)));
                i.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID_PEDIDO)));
                i.setProduto(produtoDao.findById(c.getInt(c.getColumnIndex(DataBase.FIELD_ITENSPEDIDO_ID_PRODUTO))));

                itensPedidos.add(i);
            }

            return itensPedidos;
        }
        finally {
            close();
        }
    }


    @Override
    public long count() {
        try{
            open();
            String sql = "select count(*) from "+DataBase.TABLE_ITENSPEDIDO;
            Cursor c = getConexao().rawQuery(sql, null);

            c.moveToNext();
            return c.getLong(0);
        }
        finally {
            close();
        }
    }
}
