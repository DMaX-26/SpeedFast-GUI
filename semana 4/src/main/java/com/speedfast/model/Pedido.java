package com.speedfast.model;

import com.speedfast.interfaces.Rastreable;
import com.speedfast.interfaces.Cancelable;
import com.speedfast.interfaces.Despachable;

/**
 * Clase padre abstracta que representa un Pedido
 * Implementa las interfaces Despachable, Cancelable, Rastreable
 * Cada subclase debe implementar los métodos correspondientes a cada interfaz
 */
public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    /**
     * Atributos de tipo protected para acceder a ellos desde cada subclase
     */
    protected int idPedido;
    protected String tipoPedido;
    protected String direccionEntrega;
    protected double distanciaKm;

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
}
