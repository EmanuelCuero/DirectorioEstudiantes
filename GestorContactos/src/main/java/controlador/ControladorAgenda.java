/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author emanu
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Agenda;
import modelo.Contacto;
import vista.VentanaAgenda;

public class ControladorAgenda implements ActionListener {

    private Agenda agenda;
    private VentanaAgenda ventana;

    public ControladorAgenda(Agenda agenda, VentanaAgenda ventana) {
        this.agenda = agenda;
        this.ventana = ventana;
        // El controlador escucha los dos botones de la ventana
        this.ventana.getBtnGuardar().addActionListener(this);
        this.ventana.getBtnBuscar().addActionListener(this);
    }

    public void iniciar() {
        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventana.getBtnGuardar()) {
            guardar();
        } else if (e.getSource() == ventana.getBtnBuscar()) {
            buscar();
        }
    }

    private void guardar() {
        String nombre = ventana.getNombre();
        String telefono = ventana.getTelefono();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            ventana.mostrarAviso("Debe ingresar nombre y teléfono.");
        } else if (agenda.existeTelefono(telefono)) {
            ventana.mostrarAviso("Ya existe un contacto con ese teléfono.");
        } else {
            Contacto nuevo = new Contacto(nombre, telefono);
            if (agenda.guardarContacto(nuevo)) {
                ventana.mostrarAviso("Contacto guardado.");
                ventana.limpiarCampos();
            } else {
                ventana.mostrarAviso("La agenda está llena.");
            }
        }
    }

    private void buscar() {
        String telefono = agenda.buscarTelefono(ventana.getConsulta());

        if (telefono != null) {
            ventana.mostrarRespuesta("Teléfono: " + telefono);
        } else {
            ventana.mostrarRespuesta("Contacto no encontrado.");
        }
    }
}
