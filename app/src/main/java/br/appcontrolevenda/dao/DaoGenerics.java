package br.appcontrolevenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.appcontrolevenda.model.DataBase;

public abstract class DaoGenerics<C,K> {

    private DataBase dataBase;
    private SQLiteDatabase conexao;
    private Context context;

    public DaoGenerics(Context context){this.context= context;}

    protected  void open(){

        dataBase = new DataBase(context);
        conexao = dataBase.getWritableDatabase();
    }

    protected  void close(){
        conexao.close();
    }

    protected SQLiteDatabase getConexao(){
        return conexao;
    }

    public abstract  void insert (C obj);
    public abstract  void edtit (C obj);
    public abstract  void delete (C obj);
    public abstract List<C> findAll();
    public abstract C findById(K id);
    public abstract long count();
}
