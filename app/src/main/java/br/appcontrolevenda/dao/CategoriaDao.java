package br.appcontrolevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.appcontrolevenda.model.Categoria;
import br.appcontrolevenda.model.DataBase;

public class CategoriaDao extends DaoGenerics<Categoria, Integer> {
    public CategoriaDao(Context context) {
        super(context);
    }

    @Override
    public void insert(Categoria obj) {
        try{
            open();

            ContentValues cv = new ContentValues();

            cv.put(DataBase.FIELD_CATEGORIA_NOMECATEGORIA, obj.getNomeCategoria());

            getConexao().insert(DataBase.TABLE_CATEGORIA, null, cv);
        }
        finally {
            close();
        }
    }

    @Override
    public void edtit(Categoria obj) {
        try{
            open();

            ContentValues cv = new ContentValues();


            cv.put(DataBase.FIELD_CATEGORIA_NOMECATEGORIA, obj.getNomeCategoria());

            getConexao().update(DataBase.TABLE_CATEGORIA, cv,DataBase.FIELD_CATEGORIA_ID_CATEGORIA+"="+obj.getIdCategoria(),null);
        }
        finally {
            close();
        }
    }

    @Override
    public void delete(Categoria obj) {

        try{
            open();

            getConexao().delete(DataBase.TABLE_CATEGORIA,DataBase.FIELD_CATEGORIA_ID_CATEGORIA+"="+obj.getIdCategoria(),null);
        }finally {
            close();
        }
    }

    @Override
    public List<Categoria> findAll() {
        try {
            open();
            List<Categoria> categorias = new ArrayList<>();
            String sql = "select * from " + DataBase.TABLE_CATEGORIA;
            Cursor c = getConexao().rawQuery(sql, null);

            while (c.moveToNext()) {

            Categoria cat = new Categoria();
            cat.setIdCategoria(c.getInt(c.getColumnIndex(DataBase.FIELD_CATEGORIA_ID_CATEGORIA)));
            cat.setNomeCategoria(c.getString(c.getColumnIndex(DataBase.FIELD_CATEGORIA_NOMECATEGORIA)));

            categorias.add(cat);
            }
            return  categorias;
        }

        finally {
            close();
        }
    }

    @Override
    public Categoria findById(Integer id) {

        try{
           open();
           String sql =" select*from "+DataBase.TABLE_CATEGORIA+" where "+DataBase.FIELD_CATEGORIA_ID_CATEGORIA+" = "+id;
           Cursor c = getConexao().rawQuery(sql,null);

           if(c.moveToNext()){

               Categoria cat = new Categoria();
               cat.setIdCategoria(c.getInt(c.getColumnIndex(DataBase.FIELD_CATEGORIA_ID_CATEGORIA)));
               cat.setNomeCategoria(c.getString(c.getColumnIndex(DataBase.FIELD_CATEGORIA_NOMECATEGORIA)));

               return cat;
           }
           else {
               return null;
           }
        }

        finally {
           close();
        }
    }


    @Override
    public long count() {

        try{
        open();

        String sql = "select count(*) from "+DataBase.TABLE_CATEGORIA;
        Cursor c= getConexao().rawQuery(sql,null);

        c.moveToNext();
        return c.getLong(0);

        }
        finally {
            close();
        }
    }
}
