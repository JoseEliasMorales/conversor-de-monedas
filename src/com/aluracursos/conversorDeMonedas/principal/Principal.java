package com.aluracursos.conversorDeMonedas.principal;

import com.aluracursos.conversorDeMonedas.modulos.Moneda;
import com.aluracursos.conversorDeMonedas.request.RequestApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);
        boolean activo = true;
        boolean error = false;

        DecimalFormat df = new DecimalFormat("#,###.00");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        System.out.println("\nBienvenido a nuestro convertidor de monedas!\n");

        while (activo){
            try {
                if(error){
                    System.out.println("""
                            
                            Quieres intentarlo nuevamente?
                            
                            1 - Si
                            2 - No, salir.
                            """);

                    String opcion = teclado.nextLine();

                    if(opcion.equals("1")){
                        error = false;
                        continue;
                    } else if (opcion.equals("2")){
                        activo = false;
                        System.out.println("Gracias por utilizar nuestros servicios. Adios!");
                        break;
                    } else {
                        System.out.println("Opcion no valida. Adios!");
                        activo = false;
                        break;
                    }
                }

                System.out.println("""
                   
                   *************************************
                   Escoge que tipo de cambio quieres:
                   
                   1) Peso Argentino =>> Dolar
                   2) Dolar =>> Peso Argentino
                   3) Peso Argentino =>> Real Brasileño
                   4) Real Brasileño =>> Peso Argentino
                   5) Peso Argentino =>> Peso Chileno
                   6) Peso Chileno =>> Peso Argentino
                   7) Salir
                    
                   *************************************
                   """);

                RequestApi requestApi = new RequestApi(gson);

                System.out.println("Ingrese la opcion deseada: ");
                String tipoDeCambio = teclado.nextLine();

                switch (tipoDeCambio){
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                        requestApi.request(Integer.parseInt(tipoDeCambio));
                        System.out.println("Escriba el monto a convertir: ");

                        String montoAConvertir = teclado.nextLine();

                        Moneda monedaConvertida = new Moneda(requestApi.conversion(montoAConvertir));

                        System.out.println("\nCambio: " +
                                df.format(Integer.valueOf(montoAConvertir)) +
                                " " +
                                requestApi.getMonedaBase() +
                                " son " +
                                df.format(monedaConvertida.getCambio()) +
                                " " +
                                requestApi.getMonedaConvertida());

                        System.out.println(""" 
                        \nDesea hacer otro cambio?
                        
                        1 - Si
                        2 - No
                        
                        """);

                        String intento = teclado.nextLine();

                        if(intento.equals("1")){
                            continue;
                        } else if (intento.equals("2")) {
                            System.out.println("Gracias por utilizar nuestros servicios! Adios!");
                            activo = false;
                            break;
                        }
                        continue;
                    case "7":
                        System.out.println("Gracias por utilizar nuestros servicios! Adios!");
                        activo = false;
                        break;
                    default:
                        System.out.println("Por favor, ingresa un valor valido. Intentalo nuevamente");
                        break;
                }





            }catch (InputMismatchException e){
                System.out.println("Por favor, introduce un valor valido.");
                error = true;
            } catch (NumberFormatException e){
                System.out.println("Por favor, introduce un valor valido.");
                error = true;
            }
        }
    }
}