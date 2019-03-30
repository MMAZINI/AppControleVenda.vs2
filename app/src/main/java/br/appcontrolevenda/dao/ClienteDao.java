package br.appcontrolevenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.appcontrolevenda.model.Cliente;
import br.appcontrolevenda.model.DataBase;

public class
ClienteDao extends DaoGenerics<Cliente, Integer > {

    public ClienteDao(Context context) {
        super(context);
    }

    @Override
    public void insert(Cliente obj) {
       try{
           open();
           ContentValues cv = new ContentValues();

           cv.put(DataBase.FIELD_CLIENTE_NOME, obj.getNome());
           cv.put(DataBase.FIELD_CLIENTE_SOBRENOME, obj.getSobrenome());
           cv.put(DataBase.FIELD_CLIENTE_CPF, obj.getCpf());
           cv.put(DataBase.FIELD_CLIENTE_RUA,obj.getRua());
           cv.put(DataBase.FIELD_CLIENTE_NUMERO,obj.getNumero());
           cv.put(DataBase.FIELD_CLIENTE_BAIRRO,obj.getBairro());
           cv.put(DataBase.FIELD_CLIENTE_CIDADE,obj.getCidade());
           cv.put(DataBase.FIELD_CLIENTE_TELEFONE,obj.getTelefone());

           getConexao().insert(DataBase.TABLE_CLIENTE,null,cv);

       }
       finally {
           close();
       }
    }

    @Override
    public void edtit(Cliente obj) {
        try{
            open();
            ContentValues cv = new ContentValues();

            cv.put(DataBase.FIELD_CLIENTE_NOME, obj.getNome());
            cv.put(DataBase.FIELD_CLIENTE_SOBRENOME, obj.getSobrenome());
            cv.put(DataBase.FIELD_CLIENTE_CPF, obj.getCpf());
            cv.put(DataBase.FIELD_CLIENTE_RUA,obj.getRua());
            cv.put(DataBase.FIELD_CLIENTE_NUMERO,obj.getNumero());
            cv.put(DataBase.FIELD_CLIENTE_BAIRRO,obj.getBairro());
            cv.put(DataBase.FIELD_CLIENTE_CIDADE,obj.getCidade());
            cv.put(DataBase.FIELD_CLIENTE_TELEFONE,obj.getTelefone());

            getConexao().update(DataBase.TABLE_CLIENTE,cv, DataBase.FIELD_CLIENTE_ID+"="+obj.getIdCliente(),null);

        }
        finally {
            close();
        }
    }

    @Override
    public void delete(Cliente obj) {

        try{
            open();
            ContentValues cv = new ContentValues();


            getConexao().delete(DataBase.TABLE_CLIENTE,DataBase.FIELD_CLIENTE_ID+"="+obj.getIdCliente(),null);

        }
        finally {
            close();
        }
    }

    @Override
    public List<Cliente> findAll() {

        try {
            open();
            List<Cliente> clientes = new ArrayList<>();
            String sql = "select * from " + DataBase.TABLE_CLIENTE;
            Cursor c = getConexao().rawQuery(sql, null);

            while (c.moveToNext()) {
                Cliente cl = new Cliente();
                cl.setIdCliente(c.getInt(c.getColumnIndex(DataBase.FIELD_CLIENTE_ID)));
                cl.setNome(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_NOME)));
                cl.setSobrenome(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_SOBRENOME)));
                cl.setCpf(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_CPF)));
                cl.setRua(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_RUA)));
                cl.setNumero(c.getInt(c.getColumnIndex(DataBase.FIELD_CLIENTE_NUMERO)));
                cl.setBairro(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_BAIRRO)));
                cl.setCidade(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_CIDADE)));
                cl.setTelefone(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_TELEFONE)));

                clientes.add(cl);
            }

            return clientes;
        }

        finally{
                close();

        }
    }


    @Override
    public Cliente findById(Integer id) {
        try{
            open();
            String sql = "select *from"+DataBase.TABLE_CLIENTE+"where"+DataBase.FIELD_CLIENTE_ID+" = "+id;
            Cursor  c = getConexao().rawQuery(sql,null);

            if(c.moveToNext()){
                Cliente cl = new Cliente();
                cl.setIdCliente(c.getInt(c.getColumnIndex(DataBase.FIELD_CLIENTE_ID)));
                cl.setNome(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_NOME)));
                cl.setSobrenome(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_SOBRENOME)));
                cl.setCpf(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_CPF)));
                cl.setRua(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_RUA)));
                cl.setNumero(c.getInt(c.getColumnIndex(DataBase.FIELD_CLIENTE_NUMERO)));
                cl.setBairro(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_BAIRRO)));
                cl.setCidade(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_CIDADE)));
                cl.setTelefone(c.getString(c.getColumnIndex(DataBase.FIELD_CLIENTE_TELEFONE)));

                return cl;
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
            String sql ="select count(*) from"+DataBase.TABLE_CLIENTE;
            Cursor c = getConexao().rawQuery(sql,null);

            c.moveToNext();
            return c.getLong(0);

        }
        finally {
            close();
        }
    }
}
