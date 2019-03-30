package br.appcontrolevenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.appcontrolevenda.dao.CategoriaDao;
import br.appcontrolevenda.dao.ClienteDao;
import br.appcontrolevenda.model.Categoria;
import br.appcontrolevenda.model.Cliente;


public class MainActivity extends AppCompatActivity {
    TextView idTeste;
    Button btnTestar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding();

      btnTestar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {



              Categoria categoria = new Categoria();
              categoria.setNomeCategoria("VESTUARIO");

              Categoria categoria1 = new Categoria();
              categoria1.setNomeCategoria("LIMPEZA");

              CategoriaDao categoriaDao = new CategoriaDao(getApplicationContext());
              categoriaDao.insert(categoria);
              categoriaDao.insert(categoria1);

              idTeste.setText("categoria: "+ categoriaDao.findAll().size());




          }
      });

       //

    }

    private void binding() {

        idTeste= findViewById(R.id.idteste);
        btnTestar = findViewById(R.id.btnTestar);
    }


}
