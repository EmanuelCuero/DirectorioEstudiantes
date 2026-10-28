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

public class ContactoTest {

    @Test
    public void testConstructor_GuardaNombre() {
        Contacto c = new Contacto("Andres", "3001234567");
        // El nombre que se pasa al constructor queda guardado

        assertEquals("Andres", c.getNombre());
    }

    @Test
    public void testConstructor_GuardaTelefono() {
        Contacto c = new Contacto("Andres", "3001234567");
        // El telefono que se pasa al constructor queda guardado

        assertEquals("3001234567", c.getTelefono());
    }

    @Test
    public void testSetNombre() {
        Contacto c = new Contacto("Andres", "3001234567");
        c.setNombre("Valentina");
        // El nombre cambia de "Andres" a "Valentina"

        assertEquals("Valentina", c.getNombre());
    }

    @Test
    public void testSetTelefono() {
        Contacto c = new Contacto("Andres", "3001234567");
        c.setTelefono("3109876543");
        // El telefono cambia al nuevo valor

        assertEquals("3109876543", c.getTelefono());
    }
}
