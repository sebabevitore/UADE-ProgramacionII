package org.uade.entity;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;

public class Ruta {
    private int codigoOrigen;
    private int codigoDestino;
    private QueueADT<Terminal> paradasIntermedias;

    public Ruta(int codigoOrigen, int codigoDestino) {
        this.codigoOrigen = codigoOrigen;
        this.codigoDestino = codigoDestino;
        this.paradasIntermedias = new DynamicQueueADT<>();
    }

    public Ruta(int codigoOrigen, int codigoDestino, QueueADT<Terminal> paradasIntermedias) {
        this.codigoOrigen = codigoOrigen;
        this.codigoDestino = codigoDestino;
        this.paradasIntermedias = paradasIntermedias;
    }

    public int getCodigoOrigen() {
        return codigoOrigen;
    }

    public void setCodigoOrigen(int codigoOrigen) {
        this.codigoOrigen = codigoOrigen;
    }

    public int getCodigoDestino() {
        return codigoDestino;
    }

    public void setCodigoDestino(int codigoDestino) {
        this.codigoDestino = codigoDestino;
    }

    public QueueADT<Terminal> getParadasIntermedias() {
        return paradasIntermedias;
    }

    public void setParadasIntermedias(QueueADT<Terminal> paradasIntermedias) {
        this.paradasIntermedias = paradasIntermedias;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "codigoOrigen=" + codigoOrigen +
                ", codigoDestino=" + codigoDestino +
                ", paradasIntermedias=" + paradasIntermedias +
                '}';
    }
}
