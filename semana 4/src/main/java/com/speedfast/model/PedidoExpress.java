package com.speedfast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclase que representa un Pedido express
 * Hereda atributos y métodos de la clase padre Pedido
 * Implementa los métodos correspondientes a las interfaces Despachable, Cancelable y Rastreable
 */
public class PedidoExpress extends Pedido {
    private List<String> historial;

    /**
     * Constructor para crear los objetos e inicializar atributos heredados
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     */
    public PedidoExpress(int idPedido, String tipoPedido, String direccionEntrega, double distanciaKm) {
        /**
         * Llamado al constructor de la clase padre para inicializar sus atributos en el objeto PedidoExpress
         */
        super(idPedido, tipoPedido, direccionEntrega, distanciaKm);
        /**
         * Se inicializa la lista historial vacía
         */
        this.historial = new ArrayList<>();
        historial.add("Número de pedido: "+idPedido);
        historial.add("Distancia: "+distanciaKm+" km");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println();
        System.out.println("Asignando repartidor de pedidos express...");
        System.out.println("... ... ...");
    }

    @Override
    public void calcularTiempoEntrega() {
        /**
         * Tiempo estimado base de 10 minutos
         */
        int tiempoEstimado = 10;

        /**
         * Si la distancia es mayor a 5, el tiempo estimado aumenta 5 minutos
         */
        if (distanciaKm>5){
            tiempoEstimado = 10+5;
        }
        System.out.println("---------------------------------------");
        System.out.println("Tiempo estimado de entrega: "+tiempoEstimado+" minutos");
        System.out.println("---------------------------------------");
        System.out.println();
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Buscando repartidor cercano a la zona...");
        System.out.println("Repartidor encontrado: "+nombreRepartidor);
        System.out.println("Verificando disponibilidad inmediata...");
        System.out.println("EL repartidor está disponible!");
        System.out.println("El pedido ha sido asignado a : "+nombreRepartidor);
        System.out.println();
        /**
         * Se agrega a la lista el nombre del repartidor
         */
        historial.add("Repartidor asignado: "+nombreRepartidor);
    }

    @Override
    public void despachar() {
        System.out.println("Despachando Pedido Express "+"'"+idPedido+"'"+"...");
        System.out.println("... ... ...");
        System.out.println("El pedido ha sido despachado!");
        System.out.println();
        /**
         * Se agrega la direccion de entrega a la lista
         */
        historial.add("Pedido express despachado a: "+direccionEntrega);
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido express "+"'"+idPedido+"'"+"...");
        System.out.println("... ... ...");
        System.out.println("El pedido "+"'"+idPedido+"'"+" ha sido cancelado correctamente!");
        System.out.println();
        historial.add("Pedido cancelado");
    }

    @Override
    public List<String> verHistorial() {
        return historial;
    }

    @Override
    public void mostrarHistorial() {
        /**
         * Se recorre la lista y se imprime
         */
        for (String elemento : verHistorial()) {
            System.out.println(elemento);
        }
        System.out.println();
    }
}
