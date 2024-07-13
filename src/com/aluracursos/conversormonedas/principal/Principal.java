package com.aluracursos.conversormonedas.principal;

import com.aluracursos.conversormonedas.models.Moneda;
import com.aluracursos.conversormonedas.service.ConsumoAPI;
import com.aluracursos.conversormonedas.service.ConvierteDatos;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Scanner;

public class Principal {
    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    private final String API_KEY_MAS_URL = "9d6008cffe5e6011b5cefd2a/latest/";

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraMenu(){
        int opcion;
        double valor;
        double resultadoConversion;
        System.out.println("Bienvenido a su Sistema de Conversion de Monedas.");
        do {
            System.out.println("******* Conversor de Monedas *********");
            System.out.println("1- Dolar => Peso Argentino");
            System.out.println("2- Peso Argentino => Dolar");
            System.out.println("3- Dolar => Real Brasileño");
            System.out.println("4- Real Brasileño => Dolar");
            System.out.println("5- Dolar => Peso Colombiano");
            System.out.println("6- Peso Colombiano => Dolar");
            System.out.println("7- Salir");
            System.out.println("Seleccione una opción válida: ");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has elegido convertir Dolar a Peso Argentino");
                    System.out.print("\nPor favor ingrese el monto que desea convertir: $ ");
                    valor = teclado.nextDouble();
                    resultadoConversion = calculaConversion("USD", "ARS", valor);
                    System.out.println("El resultado de convertir $" + valor +" USD a ARS es: $" +resultadoConversion);
                    break;
                case 2:
                    System.out.println("Has elegido convertir Peso Argentino a Dolar");
                    System.out.print("\nPor favor ingrese el monto que desea convertir: $ ");
                    valor = teclado.nextDouble();
                    resultadoConversion = calculaConversion("ARS", "USD", valor);
                    System.out.println("El resultado de convertir $" + valor +" ARS a USD es: $" +resultadoConversion);
                    break;
                case 3:
                    System.out.println("Has elegido convertir Dolar a Real Brasileño");
                    System.out.print("\nPor favor ingrese el monto que desea convertir: $ ");
                    valor = teclado.nextDouble();
                    resultadoConversion = calculaConversion("USD", "BRL", valor);
                    System.out.println("El resultado de convertir $" + valor +" USD a BRL es: $" +resultadoConversion);
                    break;
                case 4:
                    System.out.println("Has elegido convertir Real Brasileño a Dolar");
                    System.out.print("\nPor favor ingrese el monto que desea convertir: $ ");
                    valor = teclado.nextDouble();
                    resultadoConversion = calculaConversion("BRL", "USD", valor);
                    System.out.println("El resultado de convertir $" + valor +" BRL a USD es: $" +resultadoConversion);
                    break;
                case 5:
                    System.out.println("Has elegido convertir Dolar a Peso Colombiano");
                    System.out.print("\nPor favor ingrese el monto que desea convertir: $ ");
                    valor = teclado.nextDouble();
                    resultadoConversion = calculaConversion("USD", "COP", valor);
                    System.out.println("El resultado de convertir $" + valor +" USD a COP es: $" +resultadoConversion);
                    break;
                case 6:
                    System.out.println("Has elegido convertir Peso Colombiano a Dolar");
                    System.out.print("\nPor favor ingrese el monto que desea convertir: $ ");
                    valor = teclado.nextDouble();
                    resultadoConversion = calculaConversion("COP", "USD", valor);
                    System.out.println("El resultado de convertir $" + valor +" COP a USD es: $" +resultadoConversion);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
        } while (opcion != 7);
    }

    public double calculaConversion(String monedaUsuario, String monedaAConvertir, double valorAConvetir){
        var json = consumoApi.obtenerDatos(URL_BASE + API_KEY_MAS_URL + monedaUsuario);
        var datos = conversor.obtenerDatos(json, Moneda.class);

        Gson gson = new Gson();
        Moneda moneda = gson.fromJson(json, Moneda.class);

        Map<String, Double> conversion = moneda.getValorDeConversion();
        var tasaConversion = conversion.get(monedaAConvertir);
        var resultado = valorAConvetir * tasaConversion;

        return resultado;
    }
}
