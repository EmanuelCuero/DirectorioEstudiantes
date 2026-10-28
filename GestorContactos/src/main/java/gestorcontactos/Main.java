/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorcontactos;

/**
 *
 * @author emanu
 */
import controlador.ControladorAgenda;
import modelo.Agenda;
import vista.VentanaAgenda;

public class Main {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        VentanaAgenda ventana = new VentanaAgenda();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        controlador.iniciar();
    }
}
