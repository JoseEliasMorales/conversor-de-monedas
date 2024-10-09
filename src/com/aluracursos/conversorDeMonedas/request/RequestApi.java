package com.aluracursos.conversorDeMonedas.request;

import com.aluracursos.conversorDeMonedas.records.MonedaConvertida;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestApi{
    private HttpClient client;
    private String direccion = "https://v6.exchangerate-api.com/v6/";
    private String API_KEY = "913e3fe9f560dd78f5a90b13";
    private Gson gson;
    private String pair;

    public String getMonedaConvertida() {
        return monedaConvertida;
    }

    private String monedaConvertida;

    public String getMonedaBase() {
        return monedaBase;
    }

    private String monedaBase;


    public RequestApi(Gson gson){
        this.gson = gson;
    }

    public void request(int opcionesDeCambio){
        switch (opcionesDeCambio){
            case 1:
                this.pair = "/ARS/USD/";
                this.monedaBase = "Pesos Argentinos";
                this.monedaConvertida = "Dolares";
                break;
            case 2:
                this.pair = "/USD/ARS/";
                this.monedaBase = "Dolares";
                this.monedaConvertida = "Pesos Argentinos";
                break;
            case 3:
                this.pair = "/ARS/BRL/";
                this.monedaBase = "Pesos Argentinos";
                this.monedaConvertida = "Reales Brasileños";
                break;
            case 4:
                this.pair = "/BRL/ARS/";
                this.monedaBase = "Reales Brasileños";
                this.monedaConvertida = "Pesos Argentinos";
                break;
            case 5:
                this.pair = "/ARS/CLP/";
                this.monedaBase = "Pesos Argentinos";
                this.monedaConvertida = "Pesos Chilenos";
                break;
            case 6:
                this.pair = "/CLP/ARS/";
                this.monedaBase = "Pesos Chilenos";
                this.monedaConvertida = "Pesos Argentinos";
                break;
            default:
                break;
        }
    }




    public MonedaConvertida conversion(String monto) throws IOException, InterruptedException {

        String url = direccion + API_KEY + "/pair" + this.pair + monto;

        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        return gson.fromJson(json, MonedaConvertida.class);
    }
}
