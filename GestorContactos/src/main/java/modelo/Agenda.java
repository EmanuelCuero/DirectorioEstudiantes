/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author emanu
 */
public class Agenda {

    // Arreglo donde se guardan los contactos y cuantos hay guardados
    private Contacto[] listaContactos;
    private int total;

    public Agenda() {
        listaContactos = new Contacto[100];
        total = 0;
    }

    // Guarda el contacto en la siguiente posicion libre
    // Devuelve false si ya no hay espacio o si el telefono ya esta guardado
    public boolean guardarContacto(Contacto c) {
        if (total < listaContactos.length && !existeTelefono(c.getTelefono())) {
            listaContactos[total] = c;
            total++;
            return true;
        }
        return false;
    }

    // Revisa si ya hay un contacto con ese telefono
    public boolean existeTelefono(String telefono) {
        for (int i = 0; i < total; i++) {
            if (listaContactos[i].getTelefono().equals(telefono)) {
                return true;
            }
        }
        return false;
    }

    // La agenda esta llena cuando ya se ocuparon las 100 posiciones
    public boolean estaLlena() {
        return total == listaContactos.length;
    }

    public Contacto[] getListaContactos() {
        return listaContactos;
    }

    public int getTotal() {
        return total;
    }
}
