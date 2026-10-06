package com.speedfast.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclase que representa un Pedido de encomienda
 * Hereda atributos y métodos de la clase padre Pedido
 * Implementa los métodos correspondientes a las interfaces Despachable, Cancelable y Rastreable
 */
public class PedidoEncomienda extends Pedido {
    private List<String> historial;

    /**
     * Constructor para crear los objetos e inicializar atributos heredados
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     */
    public PedidoEncomienda(int idPedido, String tipoPedido, String direccionEntrega, double distanciaKm) {
        /**
         * Llamado al constructor de la clase padre para inicializar sus atributos en el objeto PedidoEncomienda
         */
        super(idPedido, tipoPedido, direccionEntrega, distanciaKm);
        this.historial = new ArrayList<>();
        this.historial.add("Número de pedido: "+idPedido);
        this.historial.add("Distancia: "+distanciaKm+" km");
    }

    /**
     * Constructor para crear un "PedidoEncomienda" desde la interfaz
     * @param tipoPedido
     * @param direccionEntrega
     * @param distanciaKm
     */
    public PedidoEncomienda(String tipoPedido, String direccionEntrega, double distanciaKm) {
        /**
         * Llamado al constructor de la clase padre para inicializar sus atributos en el objeto PedidoEncomienda
         */
        super(tipoPedido, direccionEntrega, distanciaKm);
        this.historial = new ArrayList<>();
        this.historial.add("Número de pedido: "+idPedido);
        this.historial.add("Distancia: "+distanciaKm+" km");
    }

    /**
     * Redefine el metodo de la clase padre, cambiando su comportamiento
     */
    @Override
    public void asignarRepartidor() {
        System.out.println();
        System.out.println("Asignando repartidor de encomienda...");
        System.out.println("... ... ...");
    }

    /**
     * Se implementa el metodo abstracto
     */
    @Override
    public void calcularTiempoEntrega() {
        double tiempo = 20+(1.5*distanciaKm);

        /**
         * Trata la variable "tiempo" como int y guarda su valor en la variable "tiempoEstimado"
         */
        int tiempoEstimado = (int) tiempo;

        System.out.println("------------------------------------------------------");
        System.out.println("Tiempo estimado de entrega: "+tiempoEstimado+" minutos");
        System.out.println("------------------------------------------------------");
        System.out.println();
    }

    /**
     * Sobrecarga del metodo
     * Se le pasan tres parámetros
     * @param nombreRepartidor
     */
    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Verificando el peso de la encomienda...");
        System.out.println("El peso de la encomienda ha sido verificado satisfactoriamente!");
        System.out.println("Embalando encomienda...");
        System.out.println("... ... ...");
        System.out.println("La encomienda ha sido embalada correctamente.");
        System.out.println("El pedido ha sido asignado a: "+nombreRepartidor);
        System.out.println();
        historial.add("Repartidor asignado: "+nombreRepartidor);
    }

    @Override
    public void despachar() {
        System.out.println("Despachando Encomienda "+"'"+idPedido+"'"+"...");
        System.out.println("... ... ...");
        System.out.println("La encomienda "+"'"+idPedido+"'"+" ha sido despachada!");
        System.out.println();
        historial.add("Encomienda despachada a: "+direccionEntrega);
    }

    @Override
    public void cancelar() {
        System.out.println("Cancelando Pedido de encomienda...");
        System.out.println("... ... ...");
        System.out.println("El pedido "+"'"+idPedido+"'"+" ha sido cancelado correctamente");
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
