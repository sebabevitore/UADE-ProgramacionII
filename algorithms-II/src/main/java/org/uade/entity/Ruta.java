package org.uade.entity;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;

public class Ruta {
    private Terminal origen;
    private Terminal destino;
    private QueueADT<Terminal> paradasIntermedias;


    public Ruta(Terminal origen, Terminal destino, QueueADT<Terminal> paradasIntermedias) {
        this.origen = origen;
        this.destino = destino;
        this.paradasIntermedias = paradasIntermedias;
    }

    public Terminal getOrigen() {
        return origen;
    }

    public void setOrigen(Terminal origen) {
        this.origen = origen;
    }

    public Terminal getDestino() {
        return destino;
    }

    public void setDestino(Terminal destino) {
        this.destino = destino;
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
                "codigoOrigen=" + origen.getCodigo() +
                ", codigoDestino=" + destino.getCodigo() +
                ", paradasIntermedias=" + paradasIntermedias +
                '}';
    }
}
