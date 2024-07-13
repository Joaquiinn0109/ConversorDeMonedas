package com.aluracursos.conversormonedas.service;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ConvierteDatos implements IConvierteDatos{
    private Gson gson = new Gson();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return gson.fromJson(json, clase);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
