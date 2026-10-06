package com.speedfast.gui;

import com.speedfast.model.Pedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private JPanel Inicio;
    private JLabel lblInicio;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnEntregas;
    private JButton btnListarEntregas;

    private List<Pedido> listaPedidos;


    public VentanaPrincipal() {
        this.listaPedidos = new ArrayList<>();
        setTitle("SPEEDFAST"); //título de la ventana
        setContentPane(Inicio); //Contenedor de la ventana Inicio
        setDefaultCloseOperation(EXIT_ON_CLOSE); //cerrar al presionar la "x"
        setSize(400, 300); //ancho por alto de la ventana
        setLocationRelativeTo(null); //ubicación al centro de la pantalla
        setResizable(false); //No se puede redimensionar

        /**
         * btnRegistrar abre la ventana de registro de pedidos
         */
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistroPedido ventanaRegistroPedido = new VentanaRegistroPedido();
                ventanaRegistroPedido.setVisible(true);
            }
        });

        /**
         * btnListar abre la ventana que lista los pedidos
         */
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListaPedidos ventana = new VentanaListaPedidos();
                ventana.setVisible(true);
            }
        });
        /**
         * btnEntregas abre la ventana que gestiona entregas
         */
        btnEntregas.addActionListener(e -> {
            VentanaRegistroEntrega ventana =
                    new VentanaRegistroEntrega();
            ventana.setVisible(true);
        });
        /**
         * btnEntregas abre la ventana que lista las entregas
         */
        btnListarEntregas.addActionListener(e -> {
            VentanaListaEntregas ventana =
                    new VentanaListaEntregas();
            ventana.setVisible(true);
        });
    }
}
