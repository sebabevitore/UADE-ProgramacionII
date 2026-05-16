package org.uade.entity;

public class CambioPrioridad {
    private Motivo motivo;
    private String descripcion;

    public CambioPrioridad(Motivo motivo, String descripcion) {
        this.motivo = motivo;
        this.descripcion = descripcion;
    }

    public Motivo getMotivo() { return motivo; }
    public String getDescripcion() { return descripcion; }
}
