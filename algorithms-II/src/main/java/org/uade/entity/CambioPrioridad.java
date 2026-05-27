package org.uade.entity;

public class CambioPrioridad {
    private Motivo motivo;
    private int prioridad;


    public CambioPrioridad(Motivo motivo, int prioridad) {
        this.motivo = motivo;
        this.prioridad = prioridad;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
