package org.uade.entity;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;

import static org.uade.util.QueueADTUtil.copy;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ruta ruta = (Ruta) obj;
        return origen.equals(ruta.origen) && destino.equals(ruta.destino);
    }

    @Override
    public String toString() {
        String paradas = "";

        QueueADT<Terminal> copia = copy(this.paradasIntermedias);

        boolean esPrimera = true;
        while (copia != null && !copia.isEmpty()) {
            if (!esPrimera) {
                paradas += ", ";
            }
            paradas += copia.getElement().getCodigo(); // Agrega el código de la terminal (ej: ROS)
            esPrimera = false;
            copia.remove();
        }

        if (paradas.isEmpty()) {
            return origen.getCodigo() + " -> " + destino.getCodigo() + " (Directo)";
        } else {
            return origen.getCodigo() + " -> " + destino.getCodigo() + " [Paradas: " + paradas + "]";
        }
    }
}
