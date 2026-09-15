package com.speedfast.model;

import java.util.concurrent.PriorityBlockingQueue;

public class ZonaDeCarga {
    private final PriorityBlockingQueue<Pedido> pedidosPendientes;

    public ZonaDeCarga() {
        this.pedidosPendientes = new PriorityBlockingQueue<>();
    }

    /**
     * Metodo que agrega un pedido a la cola
     * @param p
     */
    public synchronized void agregarPedido(Pedido p){
        pedidosPendientes.add(p);
    }

    /**
     * Metodo que retira un pedido de la cola
     * @return
     * @throws InterruptedException
     */
    public synchronized Pedido retirarPedido() throws InterruptedException {
        try {
            //Se retira un pedido de la cola y se guarda en una variable
            Pedido pedido = pedidosPendientes.take();

            //Si la cola está vacía
            if (pedidosPendientes == null){
                System.out.println("No existen pedidos pendientes");
            }
            System.out.println("El "+pedido.tipoPedido+" '"+pedido.idPedido+"'"+" ha sido retirado");

            return pedido;

        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
