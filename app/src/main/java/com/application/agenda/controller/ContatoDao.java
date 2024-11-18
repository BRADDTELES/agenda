package com.application.agenda.controller;

import com.application.agenda.http.HttpHelper;
import com.application.agenda.model.Contato;
import com.google.gson.Gson;

public class ContatoDao {

    // Método para serializar o objeto contato para Json
    public String toJson(Contato contato){
        Gson gson = new Gson();
        String contatoJson = gson.toJson(contato);
        return contatoJson;
    }

    public String inserir(Contato contato){
        String contatoJson = this.toJson(contato);
        HttpHelper httpHelper = new HttpHelper();
        String response = httpHelper.post("/api.agenda/contato-dao/create", contatoJson);
        return response;
    }

    public String buscar(Contato contato){
        HttpHelper httpHelper = new HttpHelper();
        String response = httpHelper.get("/api.agenda/contato-dao/getId?id=" + contato.getId_contato());
        return response;
    }

    public String todos(){
        HttpHelper httpHelper = new HttpHelper();
        String response = httpHelper.get("/api.agenda/contato-dao/getAll");
        return response;
    }

    public String atualizar(Contato contato){
        String contatoJson = this.toJson(contato);
        HttpHelper httpHelper = new HttpHelper();
        String response = httpHelper.post("/api.agenda/contato-dao/update", contatoJson);
        return response;
    }

    public String excluir(Contato contato){
        String contatoJson = this.toJson(contato);
        HttpHelper httpHelper = new HttpHelper();
        String response = httpHelper.post("/api.agenda/contato-dao/delete", contatoJson);
        return response;
    }
}
