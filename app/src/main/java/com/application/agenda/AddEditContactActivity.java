package com.application.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.application.agenda.controller.ContatoDao;
import com.application.agenda.model.Contato;

public class AddEditContactActivity extends AppCompatActivity {

    Contato contato = new Contato();
    ContatoDao contatoDao = new ContatoDao();
    EditText editTextNome;
    EditText editTextFone;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        editTextNome = findViewById(R.id.editTextNome);
        editTextFone = findViewById(R.id.editTextFone);
        editTextEmail = findViewById(R.id.editTextEmail);

        Intent intent = getIntent();
        if (intent.hasExtra("contato")){
            contato = (Contato) intent.getSerializableExtra("contato");
            editTextNome.setText(contato.getNome());
            editTextFone.setText(contato.getFone());
            editTextEmail.setText(contato.getEmail());
        }

    }

    public void salvarButton(View view){
        contato.setNome(editTextNome.getText().toString());
        contato.setFone(editTextFone.getText().toString());
        contato.setEmail(editTextEmail.getText().toString());
        if (contato.getId_contato() == 0){
            String response = contatoDao.inserir(contato);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Contato Salvo", Toast.LENGTH_LONG).show();
        }else {
            String response = contatoDao.atualizar(contato);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Contato Atualizado", Toast.LENGTH_LONG).show();
        }
    }

    public void voltarButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}