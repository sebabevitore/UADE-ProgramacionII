package org.uade.entity;

public class Ruta {
    private String origen;
    private String destino;

    public Ruta(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }




    @Override
    public String toString() {
        return "Ruta{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}
