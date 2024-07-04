package com.libreria.Project.service;

public interface IConversionDatos {
    <T> T obtenerDatos(String json,Class<T> clase);
}
