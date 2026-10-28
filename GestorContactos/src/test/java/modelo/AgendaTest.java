/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author emanu
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AgendaTest {

    @Test
    public void testAgendaNuevaEstaVacia() {
        Agenda agenda = new Agenda();
        // Una agenda recien creada no tiene contactos: total = 0

        assertEquals(0, agenda.getTotal());
    }

    @Test
    public void testAgendaNuevaTieneCapacidad100() {
        Agenda agenda = new Agenda();
        // El arreglo se crea con 100 posiciones

        assertEquals(100, agenda.getListaContactos().length);
    }

    @Test
    public void testAgendaNuevaNoEstaLlena() {
        Agenda agenda = new Agenda();
        // Sin contactos todavia hay espacio: estaLlena() es false

        assertFalse(agenda.estaLlena());
    }

    @Test
    public void testGuardarContacto_Exitoso() {
        Agenda agenda = new Agenda();
        Contacto c = new Contacto("Andres", "3001234567");
        // Hay espacio disponible: devuelve true

        assertTrue(agenda.guardarContacto(c));
    }

    @Test
    public void testGuardarContacto_AumentaTotal() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        agenda.guardarContacto(new Contacto("Valentina", "3109876543"));
        // Se guardaron 2 contactos: total = 2

        assertEquals(2, agenda.getTotal());
    }

    @Test
    public void testGuardarContacto_QuedaEnPrimeraPosicion() {
        Agenda agenda = new Agenda();
        Contacto c = new Contacto("Andres", "3001234567");
        agenda.guardarContacto(c);
        // El primer contacto queda en la posicion 0 del arreglo

        assertSame(c, agenda.getListaContactos()[0]);
    }

    @Test
    public void testGuardarContacto_AgendaLlena() {
        Agenda agenda = new Agenda();
        for (int i = 0; i < 100; i++) {
            agenda.guardarContacto(new Contacto("Contacto" + i, "300" + i));
        }
        // Con 100 contactos la agenda esta llena: el 101 devuelve false

        assertFalse(agenda.guardarContacto(new Contacto("Extra", "3999999999")));
    }

    @Test
    public void testAgendaLlena_EstaLlenaEsTrue() {
        Agenda agenda = new Agenda();
        for (int i = 0; i < 100; i++) {
            agenda.guardarContacto(new Contacto("Contacto" + i, "300" + i));
        }
        // Con las 100 posiciones ocupadas estaLlena() devuelve true

        assertTrue(agenda.estaLlena());
    }

    @Test
    public void testGuardarContacto_TelefonoRepetido() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        // Otro contacto con el mismo telefono no se acepta: devuelve false

        assertFalse(agenda.guardarContacto(new Contacto("Valentina", "3001234567")));
    }

    @Test
    public void testExisteTelefono_Guardado() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        // El telefono ya esta en la agenda: devuelve true

        assertTrue(agenda.existeTelefono("3001234567"));
    }

    @Test
    public void testExisteTelefono_NoGuardado() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        // Un telefono que nadie tiene: devuelve false

        assertFalse(agenda.existeTelefono("3155555555"));
    }

    @Test
    public void testBuscarTelefono_ContactoGuardado() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        agenda.guardarContacto(new Contacto("Valentina", "3109876543"));
        // Buscar "Valentina" devuelve su telefono

        assertEquals("3109876543", agenda.buscarTelefono("Valentina"));
    }

    @Test
    public void testBuscarTelefono_IgnoraMayusculas() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        // Usa equalsIgnoreCase: "ANDRES" encuentra a "Andres"

        assertEquals("3001234567", agenda.buscarTelefono("ANDRES"));
    }

    @Test
    public void testBuscarTelefono_ContactoNoGuardado() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Andres", "3001234567"));
        // "Camilo" no esta en la agenda: devuelve null

        assertNull(agenda.buscarTelefono("Camilo"));
    }

    @Test
    public void testBuscarTelefono_AgendaVacia() {
        Agenda agenda = new Agenda();
        // No hay contactos que recorrer: devuelve null

        assertNull(agenda.buscarTelefono("Andres"));
    }

    @Test
    public void testBuscarTelefono_NombreRepetidoDevuelveElPrimero() {
        Agenda agenda = new Agenda();
        agenda.guardarContacto(new Contacto("Laura", "3001111111"));
        agenda.guardarContacto(new Contacto("Laura", "3002222222"));
        // Con nombres repetidos devuelve el telefono del primero que encuentra

        assertEquals("3001111111", agenda.buscarTelefono("Laura"));
    }
}
