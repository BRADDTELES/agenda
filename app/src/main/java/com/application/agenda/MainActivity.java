package com.application.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.application.agenda.controller.ContatoDao;
import com.application.agenda.http.ContatoParser;
import com.application.agenda.model.ContactAdapter;
import com.application.agenda.model.Contato;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Contato contato = new Contato();
    ContatoDao contatoDao = new ContatoDao();
    ContatoParser contatoParser = new ContatoParser();
    ArrayList<Contato> contatos = new ArrayList<Contato>();

    private ListView listView;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        String response = contatoDao.todos();
        contatos = contatoParser.getContatosFromJson(response);

        contactAdapter = new ContactAdapter(this, contatos);
        listView.setAdapter(contactAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                contato = new Contato();
                contato = contatos.get(i);
                Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
                intent.putExtra("contato", contato);
                startActivity(intent);
            }
        });

        Log.d("debug-mode", "" + contatos.size());


    }

    public void adicionarContato(View view) {
        Intent intent = new Intent(this, AddEditContactActivity.class);
        startActivity(intent);
    }
}