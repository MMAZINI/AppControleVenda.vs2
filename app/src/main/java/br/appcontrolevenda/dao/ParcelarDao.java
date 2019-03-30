package br.appcontrolevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;


import br.appcontrolevenda.model.DataBase;
import br.appcontrolevenda.model.Parcelar;
import br.appcontrolevenda.model.Pedido;


public class ParcelarDao extends DaoGenerics<Parcelar,Integer> {

    public ParcelarDao(Context context) {
        super(context);

    }

    @Override
    public void insert(Parcelar obj) {
        try{
            open();

            ContentValues cv = new ContentValues();

            cv.put(DataBase.FIELD_PARCELAR_QTDPARCELAS, obj.getQtdParcelas());
            cv.put(DataBase.FIELD_PARCELAR_IDPEDIDO,obj.getIdPedido());

            getConexao().insert(DataBase.TABLE_CATEGORIA, null, cv);
        }
        finally {
            close();
        }
    }

    @Override
    public void edtit(Parcelar obj) {

        try{
            open();

            ContentValues cv = new ContentValues();

            cv.put(DataBase.FIELD_PARCELAR_QTDPARCELAS, obj.getQtdParcelas());
            cv.put(DataBase.FIELD_PARCELAR_IDPEDIDO,obj.getIdPedido());

            getConexao().update(DataBase.TABLE_PARCELAR, cv,DataBase.FIELD_PARCELAR_ID_PARCELAR+" = "+obj.getIdParcelar(),null);
        }
        finally {
            close();
        }
    }

    @Override
    public void delete(Parcelar obj) {
        try{
            open();

            getConexao().delete(DataBase.TABLE_PARCELAR,DataBase.FIELD_PARCELAR_ID_PARCELAR+"="+obj.getIdParcelar(),null);
        }finally {
            close();
        }
    }

    @Override
    public List<Parcelar> findAll() {


        try {
            open();

            List<Parcelar> parcelas = new ArrayList<>();
            String sql = "select * from "+ DataBase.TABLE_PARCELAR;
            Cursor c = getConexao().rawQuery(sql, null);

            while (c.moveToNext()) {

                Parcelar parc = new Parcelar();

                parc.setIdParcelar(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_ID_PARCELAR)));
                parc.setQtdParcelas(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_QTDPARCELAS)));
                parc.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_IDPEDIDO)));


                parcelas.add(parc);
            }
            return parcelas;

        }
        finally {
            close();
        }
    }

    @Override
    public Parcelar findById(Integer id) {
        try {
            open();

            String sql = "select * from "+ DataBase.TABLE_PARCELAR+" where "+ DataBase.FIELD_PEDIDO_ID_PARCELAR+ " = "+ id;

            Cursor c = getConexao().rawQuery(sql, null);

            if (c.moveToNext()) {

                Parcelar parc = new Parcelar();

                parc.setIdParcelar(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_ID_PARCELAR)));
                parc.setQtdParcelas(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_QTDPARCELAS)));
                parc.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_IDPEDIDO)));


              return parc;
            }
            else{
                return null;
            }

        }
        finally {
            close();
        }
    }

    public List<Parcelar> findAllPedido(int idPedido) {


        try {
            open();

            List<Parcelar> parcelas = new ArrayList<>();
            String sql = "select * from "+ DataBase.TABLE_PARCELAR +" where "+ DataBase.FIELD_PARCELAR_ID_PARCELAR+ "="+idPedido;
            Cursor c = getConexao().rawQuery(sql, null);

            while (c.moveToNext()) {

                Parcelar parc = new Parcelar();

                parc.setIdParcelar(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_ID_PARCELAR)));
                parc.setQtdParcelas(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_QTDPARCELAS)));
                parc.setIdPedido(c.getInt(c.getColumnIndex(DataBase.FIELD_PARCELAR_IDPEDIDO)));


                parcelas.add(parc);
            }
            return parcelas;

        }
        finally {
            close();
        }
    }

    @Override
    public long count() {
        try{
            open();

            String sql = "select count(*) from "+DataBase.TABLE_PARCELAR;
            Cursor c= getConexao().rawQuery(sql,null);

            c.moveToNext();
            return c.getLong(0);

        }
        finally {
            close();
        }
    }
}

