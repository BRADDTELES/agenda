package com.application.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.agenda.controller.ContatoDao;
import com.application.agenda.model.Contato;

public class ContactDetailsActivity extends AppCompatActivity {

    Contato contato = new Contato();
    ContatoDao contatoDao = new ContatoDao();
    TextView textViewNome;
    TextView textViewFone;
    TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        textViewNome = findViewById(R.id.textViewNome);
        textViewFone = findViewById(R.id.textViewFone);
        textViewEmail = findViewById(R.id.textViewEmail);

        Intent intent = getIntent();
        if (intent.hasExtra("contato")){
            contato = (Contato) intent.getSerializableExtra("contato");
            textViewNome.setText(contato.getNome());
            textViewFone.setText(contato.getFone());
            textViewEmail.setText(contato.getEmail());
        }
    }

    public void editarButton(View view){
        Intent intent = new Intent(this, AddEditContactActivity.class);
        intent.putExtra("contato", contato);
        startActivity(intent);
    }

    public void excluirButton(View view){
        String response = contatoDao.excluir(contato);
        Toast.makeText(this, "Contato Excluido", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void voltarButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}