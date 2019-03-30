package br.appcontrolevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.appcontrolevenda.model.Categoria;
import br.appcontrolevenda.model.DataBase;
import br.appcontrolevenda.model.Produto;

public class ProdutoDao extends DaoGenerics<Produto ,Integer> {
    private CategoriaDao categoriaDao;

    public ProdutoDao(Context context) {

        super(context);
        this.categoriaDao = new CategoriaDao(context);
    }

    @Override
    public void insert(Produto obj) {

        try{
            open();
            ContentValues cv = new ContentValues();
            cv.put(DataBase.FIELD_PRODUTO_NOMEPRODUTO,obj.getNomeProduto());
            cv.put(DataBase.FIELD_PRODUTO_DESCRICAO,obj.getDescricao());
            cv.put(DataBase.FIELD_PRODUTO_MARCA,obj.getMarca());
            cv.put(DataBase.FIELD_PRODUTO_PRECO,obj.getPreco());
            cv.put(DataBase.FIELD_PRODUTO_ID_CATEGORIA, obj.getCategoria().getIdCategoria());

            getConexao().insert(DataBase.TABLE_PRODUTO,null,cv);

        }
        finally {
            close();
        }

    }

    @Override
    public void edtit(Produto obj) {

        try{
            open();
            ContentValues cv = new ContentValues();
            cv.put(DataBase.FIELD_PRODUTO_NOMEPRODUTO,obj.getNomeProduto());
            cv.put(DataBase.FIELD_PRODUTO_DESCRICAO,obj.getDescricao());
            cv.put(DataBase.FIELD_PRODUTO_MARCA,obj.getMarca());
            cv.put(DataBase.FIELD_PRODUTO_PRECO,obj.getPreco());
            cv.put(DataBase.FIELD_PRODUTO_ID_CATEGORIA,obj.getCategoria().getIdCategoria());

            getConexao().update(DataBase.TABLE_PRODUTO,cv,DataBase.FIELD_PRODUTO_ID+" = "+obj.getIdProduto(),null);

        }
        finally {
            close();
        }

    }

    @Override
    public void delete(Produto obj) {

        try{
            open();
            getConexao().delete(DataBase.TABLE_PRODUTO,DataBase.FIELD_PRODUTO_ID+" = "+obj.getIdProduto(),null);
        }
        finally {

        }

    }

    @Override
    public List<Produto> findAll() {

        try {
            open();

            List<Produto> produtos = new ArrayList<>();

            String sql = "select * from "+ DataBase.TABLE_PRODUTO;
            Cursor c= getConexao().rawQuery(sql,null);

            while(c.moveToNext()){

                Produto prod = new Produto();

                prod.setIdProduto(c.getInt(c.getColumnIndex(DataBase.FIELD_PRODUTO_ID)));
                prod.setNomeProduto(c.getString(c.getColumnIndex(DataBase.FIELD_PRODUTO_NOMEPRODUTO)));
                prod.setDescricao(c.getString(c.getColumnIndex(DataBase.FIELD_PRODUTO_DESCRICAO)));
                prod.setMarca(c.getString(c.getColumnIndex(DataBase.FIELD_PRODUTO_MARCA)));
                prod.setPreco(c.getDouble(c.getColumnIndex(DataBase.FIELD_PRODUTO_PRECO)));
                prod.setCategoria(categoriaDao.findById(c.getInt(c.getColumnIndex(DataBase.FIELD_PRODUTO_ID_CATEGORIA))));

                 produtos.add(prod);
            }
            return  produtos;


        }
        finally {
            close();
        }
    }

    @Override
    public Produto findById(Integer id) {
        try {
            open();



            String sql = "select * from "+ DataBase.TABLE_PRODUTO+" where "+DataBase.FIELD_PRODUTO_ID+" = "+id ;
            Cursor c= getConexao().rawQuery(sql,null);

            if(c.moveToNext()){

                Produto prod = new Produto();

                prod.setIdProduto(c.getInt(c.getColumnIndex(DataBase.FIELD_PRODUTO_ID)));
                prod.setNomeProduto(c.getString(c.getColumnIndex(DataBase.FIELD_PRODUTO_NOMEPRODUTO)));
                prod.setDescricao(c.getString(c.getColumnIndex(DataBase.FIELD_PRODUTO_DESCRICAO)));
                prod.setMarca(c.getString(c.getColumnIndex(DataBase.FIELD_PRODUTO_MARCA)));
                prod.setPreco(c.getDouble(c.getColumnIndex(DataBase.FIELD_PRODUTO_PRECO)));
                prod.setCategoria(categoriaDao.findById(c.getInt(c.getColumnIndex(DataBase.FIELD_PRODUTO_ID_CATEGORIA))));

               return prod;
            }
            else{
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
            String sql = "select count(*) from "+DataBase.TABLE_PRODUTO;
            Cursor c = getConexao().rawQuery(sql, null);

            c.moveToNext();
            return c.getLong(0);
        }
        finally {
            close();
        }
    }
}
