package com.speedfast.model;

import com.speedfast.interfaces.Rastreable;
import com.speedfast.interfaces.Cancelable;
import com.speedfast.interfaces.Despachable;

/**
 * Clase padre abstracta que representa un Pedido
 * Implementa las interfaces Despachable, Cancelable, Rastreable y Comparable
 * Cada subclase debe implementar los métodos correspondientes a cada interfaz
 */
public abstract class Pedido implements Despachable, Cancelable, Rastreable, Comparable<Pedido> {
    /**
     * Atributos de tipo protected para acceder a ellos desde cada subclase
     */
    protected int idPedido;
    protected String tipoPedido;
    protected String direccionEntrega;
    protected double distanciaKm;
    protected EstadoPedido estadoPedido = EstadoPedido.PENDIENTE;//el estadoPedido se inicializa como PENDIENTE

    /**
     * Constructor
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     */
    public Pedido(int idPedido, String tipoPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.tipoPedido = tipoPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    /**
     * Constructor sin "id"
     * @param tipoPedido
     * @param direccionEntrega
     * @param distanciaKm
     */
    public Pedido(String tipoPedido, String direccionEntrega, double distanciaKm) {
        this.tipoPedido = tipoPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }
    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }
    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }
    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public EstadoPedido getEstado() {
        return estadoPedido;
    }
    public void setEstado(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    public void setNuevoEstado(String nuevoEstado){
        this.estadoPedido = EstadoPedido.valueOf(nuevoEstado);
    }

    public void asignarRepartidor(){
        System.out.println("Asignando repartidor...");
    }

    public abstract void asignarRepartidor(String nombreRepartidor);

    /**
     * Metodo implementado
     */
    public void mostrarResumen(){
        System.out.println("---"+tipoPedido+"---");
        System.out.println("Código Pedido: "+idPedido);
        System.out.println("Dirección de entrega: "+direccionEntrega);
        System.out.println("Distancia en Km: "+distanciaKm);
    }

    /**
     * Metodos abstractos que deben ser implementados en cada subclase
     */
    public abstract void calcularTiempoEntrega();

    public abstract void mostrarHistorial();

    /**
     * Le dice a la cola quién va primero
     * @param otro the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Pedido otro) {
        return this.estadoPedido.compareTo(otro.estadoPedido);
    }

    /**
     * Representación textual
     * @return
     */
    @Override
    public String toString() {
        return idPedido + ", Tipo Pedido: "+tipoPedido+", Dirección Entrega: "+direccionEntrega+", Distancia en Km: "+distanciaKm;
    }
}
