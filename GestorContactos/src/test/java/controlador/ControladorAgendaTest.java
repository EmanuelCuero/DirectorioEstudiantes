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
import modelo.Agenda;
import modelo.Contacto;
import org.junit.jupiter.api.Test;
import vista.VentanaAgenda;
import static org.junit.jupiter.api.Assertions.*;

public class ControladorAgendaTest {

    // Ventana falsa: no abre dialogos y deja controlar lo que "escribe" el usuario
    static class VentanaFalsa extends VentanaAgenda {

        String nombre = "";
        String telefono = "";
        String consulta = "";
        String ultimoAviso = null;
        String ultimaRespuesta = null;
        boolean camposLimpios = false;
        boolean visible = false;

        @Override
        public String getNombre() {
            return nombre;
        }

        @Override
        public String getTelefono() {
            return telefono;
        }

        @Override
        public String getConsulta() {
            return consulta;
        }

        @Override
        public void mostrarAviso(String mensaje) {
            ultimoAviso = mensaje;
        }

        @Override
        public void mostrarRespuesta(String texto) {
            ultimaRespuesta = texto;
        }

        @Override
        public void limpiarCampos() {
            camposLimpios = true;
        }

        @Override
        public void setVisible(boolean b) {
            visible = b;
        }
    }

    // Simula un clic sobre un boton
    private void clic(ControladorAgenda controlador, Object boton) {
        controlador.actionPerformed(new ActionEvent(boton, ActionEvent.ACTION_PERFORMED, ""));
    }

    @Test
    public void testIniciar_HaceVisibleLaVentana() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        controlador.iniciar();
        // iniciar() llama a setVisible(true)

        assertTrue(ventana.visible);
    }

    @Test
    public void testGuardar_NombreVacio() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "";
        ventana.telefono = "3001234567";
        clic(controlador, ventana.getBtnGuardar());
        // Falta el nombre: muestra el mensaje de error

        assertEquals("Debe ingresar nombre y teléfono.", ventana.ultimoAviso);
    }

    @Test
    public void testGuardar_TelefonoVacio() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Andres";
        ventana.telefono = "";
        clic(controlador, ventana.getBtnGuardar());
        // Falta el telefono: muestra el mensaje de error

        assertEquals("Debe ingresar nombre y teléfono.", ventana.ultimoAviso);
    }

    @Test
    public void testGuardar_CamposVaciosNoGuarda() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "";
        ventana.telefono = "";
        clic(controlador, ventana.getBtnGuardar());
        // Con campos vacios no se guarda nada: total sigue en 0

        assertEquals(0, agenda.getTotal());
    }

    @Test
    public void testGuardar_CamposVaciosNoLimpiaCampos() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "";
        ventana.telefono = "";
        clic(controlador, ventana.getBtnGuardar());
        // Si hubo error, los campos NO se limpian

        assertFalse(ventana.camposLimpios);
    }

    @Test
    public void testGuardar_DatosValidosMuestraAviso() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Andres";
        ventana.telefono = "3001234567";
        clic(controlador, ventana.getBtnGuardar());
        // Datos completos: muestra "Contacto guardado."

        assertEquals("Contacto guardado.", ventana.ultimoAviso);
    }

    @Test
    public void testGuardar_DatosValidosGuardaContacto() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Andres";
        ventana.telefono = "3001234567";
        clic(controlador, ventana.getBtnGuardar());
        // El contacto queda guardado: total = 1 y se encuentra su telefono

        assertEquals(1, agenda.getTotal());
        assertEquals("3001234567", agenda.buscarTelefono("Andres"));
    }

    @Test
    public void testGuardar_DatosValidosLimpiaCampos() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Andres";
        ventana.telefono = "3001234567";
        clic(controlador, ventana.getBtnGuardar());
        // Al guardar bien, se llama a limpiarCampos()

        assertTrue(ventana.camposLimpios);
    }

    @Test
    public void testGuardar_TelefonoRepetido() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Valentina";
        ventana.telefono = "3001234567";
        clic(controlador, ventana.getBtnGuardar());
        // Ese telefono ya esta guardado: muestra el aviso y no se guarda

        assertEquals("Ya existe un contacto con ese teléfono.", ventana.ultimoAviso);
        assertEquals(1, agenda.getTotal());
    }

    @Test
    public void testGuardar_AgendaLlena() {
        Agenda agenda = new Agenda();
        for (int i = 0; i < 100; i++) {
            agenda.guardarContacto(new Contacto("Contacto" + i, "300" + i));
        }
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Extra";
        ventana.telefono = "3999999999";
        clic(controlador, ventana.getBtnGuardar());
        // Con 100 contactos no cabe otro: muestra "La agenda está llena."

        assertEquals("La agenda está llena.", ventana.ultimoAviso);
    }

    @Test
    public void testBuscar_ContactoGuardado() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Valentina", "3109876543"));
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.consulta = "Valentina";
        clic(controlador, ventana.getBtnBuscar());
        // Encuentra a Valentina: muestra "Teléfono: 3109876543"

        assertEquals("Teléfono: 3109876543", ventana.ultimaRespuesta);
    }

    @Test
    public void testBuscar_ContactoNoGuardado() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Valentina", "3109876543"));
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.consulta = "Camilo";
        clic(controlador, ventana.getBtnBuscar());
        // Camilo no esta en la agenda: muestra "Contacto no encontrado."

        assertEquals("Contacto no encontrado.", ventana.ultimaRespuesta);
    }

    @Test
    public void testBuscar_AgendaVacia() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.consulta = "Valentina";
        clic(controlador, ventana.getBtnBuscar());
        // Agenda sin contactos: muestra "Contacto no encontrado."

        assertEquals("Contacto no encontrado.", ventana.ultimaRespuesta);
    }

    @Test
    public void testBuscar_NoGuardaContactos() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Andres";
        ventana.telefono = "3001234567";
        ventana.consulta = "Andres";
        clic(controlador, ventana.getBtnBuscar());
        // El boton Buscar no debe hacer lo mismo que Guardar: total = 0

        assertEquals(0, agenda.getTotal());
    }

    @Test
    public void testActionPerformed_OrigenDesconocidoNoHaceNada() {
        Agenda agenda = new Agenda();
        VentanaFalsa ventana = new VentanaFalsa();
        ControladorAgenda controlador = new ControladorAgenda(agenda, ventana);
        ventana.nombre = "Andres";
        ventana.telefono = "3001234567";
        clic(controlador, new Object());
        // Un evento que no viene de ningun boton conocido no guarda ni busca

        assertEquals(0, agenda.getTotal());
    }
}
