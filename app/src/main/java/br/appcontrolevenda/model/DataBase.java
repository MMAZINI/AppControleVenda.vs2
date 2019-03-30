package br.appcontrolevenda.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    final static String nomeDataBase = "bancoVendas.db";
    final static int versaoDataBase = 3;

    public DataBase(Context context) {
        super(context, nomeDataBase, null, versaoDataBase);
    }

    //cliente
    public static final String TABLE_CLIENTE ="cliente";
    public static final String FIELD_CLIENTE_ID ="id_Cliente";
    public static final String FIELD_CLIENTE_NOME ="nome_cliente";
    public static final String FIELD_CLIENTE_SOBRENOME ="sobrenome_cliente";
    public static final String FIELD_CLIENTE_CPF ="cpf_cliente";
    public static final String FIELD_CLIENTE_RUA ="rua_cliente";
    public static final String FIELD_CLIENTE_NUMERO ="numero_cliente";
    public static final String FIELD_CLIENTE_BAIRRO ="bairro_cliente";
    public static final String FIELD_CLIENTE_CIDADE ="cidade_cliente";
    public static final String FIELD_CLIENTE_TELEFONE ="telefone_cliente";

    final static String CREATE_TABLE_CLIENTE = "Create table "+TABLE_CLIENTE+"(" + FIELD_CLIENTE_ID + ", integer primary key autoincrement, " +
            " " + FIELD_CLIENTE_NOME + " text not null, " + FIELD_CLIENTE_SOBRENOME + " text not null, " + FIELD_CLIENTE_CPF + " text not null, " + FIELD_CLIENTE_RUA + " text not null, " +
            " " + FIELD_CLIENTE_NUMERO + " integer not null, "+FIELD_CLIENTE_BAIRRO+ " integer not null," + FIELD_CLIENTE_CIDADE + " text not null, " + FIELD_CLIENTE_TELEFONE + " text not null );";

    //Pedido
    public static final String TABLE_PEDIDO ="pedido";
    public static final String FIELD_PEDIDO_ID ="id_pedido";
    public static final String FIELD_PEDIDO_VALORTOTAL ="valorTotal_pedido";
    public static final String FIELD_PEDIDO_STATUS ="status_pedido";
    public static final String FIELD_PEDIDO_ID_CLIENTE ="id_cliente_pedido";
    public static final String FIELD_PEDIDO_ID_PARCELAR ="id_parcelar_pedido";
    public static final String FIELD_PEDIDO_ID_ITENSPEDIDO ="id_itensPedido_pedido";

    final static String CREATE_TABLE_PEDIDO = "Create table "+TABLE_PEDIDO+" (" + FIELD_PEDIDO_ID + ", integer primary key autoincrement, " +
            " " + FIELD_PEDIDO_VALORTOTAL + " real not null, " + FIELD_PEDIDO_STATUS + " text not null, " + FIELD_PEDIDO_ID_CLIENTE + " integer not null, " + FIELD_PEDIDO_ID_PARCELAR + " integer not null, " +
            " " + FIELD_PEDIDO_ID_ITENSPEDIDO + " integer not null);";

    //ItensPedido
    public static final String TABLE_ITENSPEDIDO ="itensPedido";
    public static final String FIELD_ITENSPEDIDO_ID ="id_itensPedido";
    public static final String FIELD_ITENSPEDIDO_QUANTIDADE ="quantidade_itensPedido";
    public static final String FIELD_ITENSPEDIDO_ID_PRODUTO ="id_produto_itensPedido";
    public static final String FIELD_ITENSPEDIDO_ID_PEDIDO ="id_pedido_itensPedido";

    final static String CREATE_TABLE_ITENSPEDIDO = "Create table "+TABLE_ITENSPEDIDO+" (" + FIELD_ITENSPEDIDO_ID + ", integer primary key autoincrement, " +
            " " + FIELD_ITENSPEDIDO_QUANTIDADE + " integer not null, " + FIELD_ITENSPEDIDO_ID_PRODUTO + " integer not null, "+FIELD_ITENSPEDIDO_ID_PEDIDO+ " integer not null );";

    //Produto
    public static final String TABLE_PRODUTO="produto";
    public static final String FIELD_PRODUTO_ID ="id_produto";
    public static final String FIELD_PRODUTO_NOMEPRODUTO ="nomeProduto_produto";
    public static final String FIELD_PRODUTO_DESCRICAO ="descricao_produto";
    public static final String FIELD_PRODUTO_MARCA ="marca_produto";
    public static final String FIELD_PRODUTO_PRECO ="preco_produto";
    public static final String FIELD_PRODUTO_ID_CATEGORIA ="id_categoria_produto";

    final static String CREATE_TABLE_PRODUTO = "Create table "+TABLE_PRODUTO+" (" + FIELD_PRODUTO_ID + ", integer primary key autoincrement, " +
            " " + FIELD_PRODUTO_NOMEPRODUTO + " text not null, " + FIELD_PRODUTO_DESCRICAO + " text not null, " + FIELD_PRODUTO_MARCA + " text not null, " + FIELD_PRODUTO_PRECO + " real not null, " +
            " " + FIELD_PRODUTO_ID_CATEGORIA + " integer not null );";

    //Categoria
    public static final String TABLE_CATEGORIA="categoria";
    public static final String FIELD_CATEGORIA_ID_CATEGORIA ="id_categoria";
    public static final String FIELD_CATEGORIA_NOMECATEGORIA ="nomeCategoria_categoria";

    final static String CREATE_TABLE_CATEGORIA = "Create table "+TABLE_CATEGORIA+" (" + FIELD_CATEGORIA_ID_CATEGORIA + ", integer primary key autoincrement, " +
            " " + FIELD_CATEGORIA_NOMECATEGORIA + " text not null);";


    //Parcelar

    public static final String TABLE_PARCELAR="parcelar";
    public static final String FIELD_PARCELAR_ID_PARCELAR ="id_parcelar";
    public static final String FIELD_PARCELAR_QTDPARCELAS ="qtdparcelas_parcelar";
    public static final String FIELD_PARCELAR_IDPEDIDO ="id_pedido_parcela";


    final static String CREATE_TABLE_PARCELAR = "Create table "+TABLE_PARCELAR+" (" + FIELD_PARCELAR_ID_PARCELAR+ ", integer primary key autoincrement, " +
            " " + FIELD_PARCELAR_QTDPARCELAS + " integer not null, "+ FIELD_PARCELAR_IDPEDIDO + "integer not null);";



    //CRIANDO AS TABELAS NO BANCO DE DADOS
    @Override
    public void onCreate(SQLiteDatabase db) {
        //CRIANDO A TABLE  CLIENTE
        db.execSQL(CREATE_TABLE_CLIENTE);

        //CRIANDO A TABLE PEDIDO
        db.execSQL(CREATE_TABLE_PEDIDO);

        //CRIANDO A TABLE A  ITENSPEDIDO
        db.execSQL(CREATE_TABLE_ITENSPEDIDO);

        //CRIANDO A TABLE PRODUTO
        db.execSQL(CREATE_TABLE_PRODUTO);

        //CRIANDO A TABLE A CATEGORIA
        db.execSQL(CREATE_TABLE_CATEGORIA);

        //CRIANDO A TABLE PARCELAR
        db.execSQL(CREATE_TABLE_PARCELAR);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE "+TABLE_CLIENTE);
        db.execSQL("DROP TABLE "+TABLE_PEDIDO);
        db.execSQL("DROP TABLE "+TABLE_ITENSPEDIDO);
        db.execSQL("DROP TABLE "+TABLE_PRODUTO);
        db.execSQL("DROP TABLE "+TABLE_CATEGORIA);
        db.execSQL("DROP TABLE "+TABLE_PARCELAR);
        onCreate(db);
    }
}
