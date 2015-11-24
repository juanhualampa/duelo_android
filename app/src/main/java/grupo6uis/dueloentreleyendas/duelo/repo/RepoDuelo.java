package grupo6uis.dueloentreleyendas.duelo.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;

/**
 * Created by Juan on 24-Nov-15.
 */
public class RepoDuelo {

    private List<Personaje> personajes;
    private static final int MAX_RESULTS = 10;

    /**
     * SINGLETON
     */
    private static RepoDuelo instance;

    private RepoDuelo() {
        personajes = new ArrayList<Personaje>();
    }

    public static RepoDuelo getInstance() {
        if (instance == null) {
            instance = new RepoDuelo();
            instance.init();
        }
        return instance;
    }

    /**
     * AGREGAR TODO LOS DATOS DE LOS PERSONAJES ACA
     */

    /**
     *
     *   Inicializacion Juego de Datos
     */
    private void init() {
        RepoDuelo.getInstance().agregarPersonaje(new Personaje());
    }

    private void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public Personaje getPersonaje(Long id) {
        for (Personaje personaje : this.personajes) {
            if (personaje.getId().equals(id)) {
                return personaje;
            }
        }
        return null;
    }

    public List<Personaje> getPersonajes(String titulo) {
        return getPersonajes(titulo, MAX_RESULTS);
    }

    public List<Personaje> getPersonajes(String nombre, int max) {
        Iterator<Personaje> it = personajes.iterator();
        List<Personaje> result = new ArrayList<Personaje>();
        while (it.hasNext() && max > 0) {
            Personaje personaje = it.next();
            max--;
            if (nombre == null || personaje.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
                result.add(personaje);
            }
        }
        return result;
    }

}
