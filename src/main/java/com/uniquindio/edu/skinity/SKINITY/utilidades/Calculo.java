package com.uniquindio.edu.skinity.SKINITY.utilidades;

public class Calculo {
    
    public static Double calcularGananciaPorcentaje(Double valorVenta, String nivel){
        Double ganancia = 0.0;
        switch (nivel) {
            case Constantes.NIVEL1:
                ganancia = valorVenta * 0.1;
                break;
                
            case Constantes.NIVEL2:
                ganancia = valorVenta * 0.07;
            break;
            case Constantes.NIVEL3:
                ganancia = valorVenta * 0.05;
            break;
            case Constantes.NIVEL4:
                ganancia = valorVenta * 0.03;
            break;
            default:
                throw new AssertionError();
        }
        
        return ganancia;
    }
}
