package com.aluracursos.conversormonedas.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json,Class<T> clase);
}
