package com.speedfast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclase que representa un Pedido de comida
 * Hereda atributos y métodos de la clase padre Pedido
 * Implementa los métodos correspondientes a las interfaces Despachable, Cancelable y Rastreable
 */
public class PedidoComida extends Pedido {
    /**
     * Se crea un atributo de tipo List<String> correspondiente a la interfaz Rastreable
     */
    private List<String> historial;

    /**
     * Constructor para crear los objetos e inicializar atributos heredados
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     */
    public PedidoComida(int idPedido, String tipoPedido, String direccionEntrega, double distanciaKm) {
        /**
         * Llamado al constructor de la clase padre para inicializar sus atributos en el objeto actual
         */
        super(idPedido, tipoPedido, direccionEntrega, distanciaKm);
        this.historial = new ArrayList<>();
        //Se agrega el texto "Pedido + id" a la lista historial
        this.historial.add("Número de pedido: "+idPedido);
        this.historial.add("Distancia: "+distanciaKm+" km");
    }

    /**
     * Redefine el metodo de la clase padre, cambiando su comportamiento
     */
    @Override
    public void asignarRepartidor() {
        System.out.println();
        System.out.println("Asignando repartidor de comida...");
        System.out.println("... ... ...");
    }

    /**
     * Se implementa el metodo abstracto
     */
    @Override
    public void calcularTiempoEntrega() {
        /**
         * Se calcula el tiempo estimado de entrega y el valor se guarda en la variable "tiempo"
         */
        double tiempo = 15+(2*distanciaKm);

        /**
         * Trata la variable "tiempo" como int y guarda su valor en la variable "tiempoEstimado"
         */
        int tiempoEstimado = (int) tiempo;

        System.out.println("---------------------------------------");
        System.out.println("Tiempo estimado de entrega: "+tiempoEstimado+" minutos");
        System.out.println("---------------------------------------");
        System.out.println();
    }

    /**
     * Sobrecarga del metodo asignarRepartidor
     * @param nombreRepartidor
     */
    @Override
    public void asignarRepartidor(String nombreRepartidor){
        System.out.println("Repartidor disponible: "+nombreRepartidor);
        System.out.println("Verificando si el repartidor tiene mochila térmica...");
        System.out.println("El repartidor sí tiene mochila térmica!");
        System.out.println("El pedido ha sido asignado a: "+nombreRepartidor);
        System.out.println();
        historial.add("Repartidor asignado: "+nombreRepartidor);
    }

    @Override
    public void despachar() {
        System.out.println("--------------------------------------------");
        System.out.println("Despachando pedido de comida "+"'"+idPedido+"'"+"...");
        System.out.println("... ... ...");
        System.out.println("El pedido de comida "+"'"+idPedido+"'"+" ha sido despachado!");
        System.out.println("--------------------------------------------");
        System.out.println();
        //Se agrega el texto "Pedido despachado + direccionEntrega" a la lista historial
        historial.add("Pedido despachado a: "+direccionEntrega);
    }

    @Override
    public void cancelar() {
        System.out.println("----------------------------------------------------------");
        System.out.println("Cancelando pedido "+"'"+idPedido+"'");
        System.out.println("... ... ...");
        System.out.println("El pedido de comida "+"'"+idPedido+"'"+" ha sido cancelado correctamente!");
        System.out.println("----------------------------------------------------------");
        System.out.println();
        //Se agrega el texto "Pedido cancelado" a la lista historial
        historial.add("Pedido cancelado");
    }

    @Override
    public List<String> verHistorial() {
        return historial;
    }

    public void mostrarHistorial(){
        /**
         * Se recorre la lista y se imprime
         */
        for (String elemento : verHistorial()) {
            System.out.println(elemento);
        }
        System.out.println();
    }
}
