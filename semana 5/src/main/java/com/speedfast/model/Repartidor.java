package com.speedfast.model;

import java.util.List;

/**
 * Clase que representa a un Repartidor. Reparte pedidos de forma concurrente
 */
public class Repartidor implements Runnable {
    private String nombre;
    private List<Pedido> pedidos;//Repartidor tiene una lista de pedidos
    private ZonaDeCarga zonaDeCarga;//Tiene zonaDeCarga que contiene una cola con los pedidos pendientes

    /**
     * Constructor donde creamos los objetos e inicializamos los atributos nombre, la lista "pedidos" y la zona de carga con los pedidos pendientes
     * @param nombre
     * @param pedidos
     */
    public Repartidor(String nombre, List<Pedido> pedidos, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.pedidos = pedidos;
        this.zonaDeCarga = zonaDeCarga;
    }

    /**
     * Tarea que ejecuta el Repartidor
     * Entrega un pedido en un lapso de entre 1 a 10 segundos
     */
    @Override
    public void run() {
        //For each para recorrer la lista de tipo Pedido
            try {
                //Se llama al metodo retirarPedido de la zona de carga
                Pedido pedido = zonaDeCarga.retirarPedido();
                //Mensaje informativo
                System.out.println("Comenzando la entrega de "+pedido.tipoPedido+" '"+pedido.idPedido+"'");

                //tiempo aleatorio entre 1 y 10 segundos
                int tiempo = (int) (Math.random() * 10+1);
                //El hilo espera según el tiempo
                Thread.sleep(tiempo*1000);

                //Se cambia el estadoPedido a "EN_REPARTO"
                pedido.setEstado(EstadoPedido.EN_REPARTO);

                //Se obtiene el estadoPedido y se imprime
                System.out.println("Estado del pedido "+"'"+pedido.idPedido+"' -> "+pedido.getEstado());

                //Se imprime un mensaje informativo acerca de la entrega
                System.out.println(pedido.tipoPedido+" '"+pedido.idPedido+"'"+" entregado por: "+nombre);
                System.out.println("El repartidor "+nombre+" ha finalizado la entrega");

                //Se cambia el estadoPedido a "ENTREGADO"
                pedido.setEstado(EstadoPedido.ENTREGADO);

                //Se obtiene el estadoPedido y se imprime
                System.out.println("Estado del pedido: "+"'"+pedido.idPedido+"' -> "+pedido.getEstado());
            } catch (InterruptedException e) {
                /**
                 * Se interrumpe el hilo de forma segura
                 */
                Thread.currentThread().interrupt();
            }
    }
}
