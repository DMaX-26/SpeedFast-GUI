package com.speedfast.model;

/**
 * Clase que representa una entrega
 */
public class Entrega {
    private int id;
    private int idPedido;
    private int idRepartidor;
    private String fecha;
    private String hora;

    /**
     * Consructor para leer entregas existentes
     * @param id
     * @param idPedido
     * @param idRepartidor
     * @param fecha
     * @param hora
     */
    public Entrega(int id, int idPedido, int idRepartidor, String fecha, String hora) {
        this.id = id;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    /**
     * Constructor para crear una entrega
     * @param idPedido
     * @param idRepartidor
     * @param fecha
     * @param hora
     */
    public Entrega(int idPedido, int idRepartidor, String fecha, String hora) {
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    /**
     * Métodos getter and setter
     * @return
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }
    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * Representación textual de una Entrega
     * @return
     */
    @Override
    public String toString() {
        return "Entrega " + id + " | Pedido: " + idPedido + " | Repartidor: " + idRepartidor + " | Fecha: " + fecha + " | Hora: " + hora;
    }
}
