package com.aluracursos.conversormonedas.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Moneda {
    @SerializedName("base_code")
    private String codigoISO;
    @SerializedName("conversion_rates")
    private Map<String, Double> valorDeConversion;

    public String getCodigoISO() {
        return codigoISO;
    }

    public Map<String, Double> getValorDeConversion() {
        return valorDeConversion;
    }

    @Override
    public String toString() {
        return "Moneda: " + codigoISO + "\n" +
                "Datos de Conversion con respecto a otras monedas:\n" + valorDeConversion;
    }
}
