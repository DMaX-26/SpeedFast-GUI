package com.speedfast.app;

import com.speedfast.dao.PedidoDAO;
import com.speedfast.dao.RepartidorDAO;
import com.speedfast.gui.VentanaPrincipal;
import com.speedfast.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws SQLException {
        /**
        //Listas vacías
        List<PedidoComida> historialPedidosComida = new ArrayList<>();
        List<PedidoEncomienda> historialPedidosEncomienda = new ArrayList<>();
        List<PedidoExpress> historialPedidosExpress = new ArrayList<>();

        //Se crea una instancia de PedidoComida y se ingresan los datos
        PedidoComida pedidoComida1 = new PedidoComida(1, "Pedido Comida", "Los álamos 123, Viña del Mar", 2.4);
        //Se llama al metodo mostrarResumen
        pedidoComida1.mostrarResumen();
        //Se llama al metodo asignarRepartidor (version sobreescrita)
        pedidoComida1.asignarRepartidor();
        //Se llama al metodo asignarRepartidor (version sobrecargada)
        pedidoComida1.asignarRepartidor("Pablo Gonzalez");
        //Se llama al metodo calcularTiempoEntrega
        pedidoComida1.calcularTiempoEntrega();
        pedidoComida1.despachar();

        PedidoComida pedidoComida2 = new PedidoComida(2, "Pedido Comida", "Calle Santiago 789, Con-con", 1.5);
        pedidoComida2.mostrarResumen();
        pedidoComida2.asignarRepartidor();
        pedidoComida2.asignarRepartidor("Hector Rodriguez");
        pedidoComida2.calcularTiempoEntrega();
        pedidoComida2.cancelar();

        //Se agregan las instancias creadas a la lista
        historialPedidosComida.add(pedidoComida1);
        historialPedidosComida.add(pedidoComida2);

        System.out.println("Historial de pedidos de comida:");
        //Se recorre la lista y cada elemento del recorrido se guarda en la variable "p"
        for (PedidoComida p : historialPedidosComida){
            //Se llama al metodo "mostrarHistorial"
            p.mostrarHistorial();
        }

        PedidoEncomienda pedidoEncomienda1 = new PedidoEncomienda(21, "Pedido Encomienda", "Pasaje el roble 321, Quilpué", 4.5);
        pedidoEncomienda1.mostrarResumen();
        pedidoEncomienda1.asignarRepartidor();
        pedidoEncomienda1.asignarRepartidor("Martina Fernandez");
        pedidoEncomienda1.calcularTiempoEntrega();
        pedidoEncomienda1.despachar();

        PedidoEncomienda pedidoEncomienda2 = new PedidoEncomienda(22, "Pedido Encomienda", "La Retuca 456, Peñablanca", 4);
        pedidoEncomienda2.mostrarResumen();
        pedidoEncomienda2.asignarRepartidor("Francisco Romero");
        pedidoEncomienda2.calcularTiempoEntrega();
        pedidoEncomienda2.cancelar();

        historialPedidosEncomienda.add(pedidoEncomienda1);
        historialPedidosEncomienda.add(pedidoEncomienda2);

        System.out.println("Historial de pedidos de encomienda:");
        for (PedidoEncomienda pe : historialPedidosEncomienda){
            pe.mostrarHistorial();
        }



        PedidoExpress pedidoExpress1 = new PedidoExpress(12, "Pedido Express", "Calle Venecia 012, Valparaiso", 6);
        pedidoExpress1.mostrarResumen();
        pedidoExpress1.asignarRepartidor();
        pedidoExpress1.asignarRepartidor("Pedro Suarez");
        pedidoExpress1.calcularTiempoEntrega();
        pedidoExpress1.despachar();

        PedidoExpress pedidoExpress2 = new PedidoExpress(13, "Pedido Express", "Calle Santa Maria 034, Valparaiso", 2);
        pedidoExpress2.mostrarResumen();
        pedidoExpress2.asignarRepartidor();
        pedidoExpress2.asignarRepartidor("Mario Gonzalez");
        pedidoExpress2.calcularTiempoEntrega();
        pedidoExpress2.cancelar();

        historialPedidosExpress.add(pedidoExpress1);
        historialPedidosExpress.add(pedidoExpress2);

        System.out.println("Historial de pedidos express:");
        for (PedidoExpress pex : historialPedidosExpress){
            pex.mostrarHistorial();
        }

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        //Se crea una lista vacía de tipo Pedido
        List<Pedido> pedidos1 = new ArrayList<>();
        //Se reutilizan los objetos de tipo PedidoComida y se agregan a la lista
        pedidos1.add(pedidoComida1);
        pedidos1.add(pedidoComida2);
        //Se crea una instancia de Repartidor, se ingresa su nombre y un pedido asignado
        Repartidor repartidor1 = new Repartidor("Victor Espinoza", pedidos1, zonaDeCarga);

        List<Pedido> pedidos2 = new ArrayList<>();
        pedidos2.add(pedidoEncomienda1);
        pedidos2.add(pedidoEncomienda2);
        Repartidor repartidor2 = new Repartidor("Andres Suarez", pedidos2, zonaDeCarga);

        List<Pedido> pedidos3 = new ArrayList<>();
        pedidos3.add(pedidoExpress1);
        pedidos3.add(pedidoExpress2);
        Repartidor repartidor3 = new Repartidor("Felipe Fernandez", pedidos3, zonaDeCarga);

        //Se crea un administrador de hilos con 3 hilos disponibles para ejecutar tareas
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //Se agregan pedidos a la zona de carga
        zonaDeCarga.agregarPedido(pedidoComida1);
        zonaDeCarga.agregarPedido(pedidoComida2);
        zonaDeCarga.agregarPedido(pedidoEncomienda1);
        zonaDeCarga.agregarPedido(pedidoEncomienda2);
        zonaDeCarga.agregarPedido(pedidoExpress1);

        //cada Repartidor toma un hilo para ejecutar tareas
        executorService.execute(repartidor1);
        executorService.execute(repartidor2);
        executorService.execute(repartidor3);


        // Simulación activa durante 15 segundos
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Apagar el sistema
        executorService.shutdownNow();

        try {
            //Comprobar por dos segundos que los hilos se hayan terminado de ejecutar
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                //Si no han terminado, se lanza un mensaje informativo
                System.out.println("Algunos hilos no finalizaron correctamente.");
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        System.out.println();
        System.out.println("Todos los pedidos han sido entregados correctamente.");
        System.out.println();*/

        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}