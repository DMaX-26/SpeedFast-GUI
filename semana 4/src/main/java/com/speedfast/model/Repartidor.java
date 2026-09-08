package com.speedfast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un Repartidor. Reparte pedidos de forma concurrente
 */
public class Repartidor implements Runnable {
    private String nombre;
    private List<Pedido> pedidos;

    /**
     * Constructor donde creamos los objetos e inicializamos los atributos nombre y la lista "pedidos"
     * @param nombre
     * @param pedidos
     */
    public Repartidor(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }

    /**
     * Tarea que ejecuta el Repartidor
     * Entrega un pedido en un lapso de entre 1 y 10 segundos
     */
    @Override
    public void run() {
        //For each para recorrer la lista de tipo Pedido
        for (Pedido p : pedidos){
            System.out.println("Comenzando la entrega de "+p.tipoPedido+" '"+p.idPedido+"'");
            try {
                //tiempo aleatorio entre 1 y 10 segundos
                int tiempo = (int) (Math.random() * 10+1);
                //El hilo espera según el tiempo
                Thread.sleep(tiempo*1000);
                //Se imprime un mensaje informativo acerca de la entrega
                System.out.println(p.tipoPedido+" '"+p.idPedido+"'"+" entregado por "+nombre);
                System.out.println("El repartidor "+nombre+" ha finalizado la entrega");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
