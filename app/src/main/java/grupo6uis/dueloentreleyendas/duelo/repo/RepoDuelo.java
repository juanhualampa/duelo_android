package grupo6uis.dueloentreleyendas.duelo.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;

/**
 * Created by Juan on 24-Nov-15.
 */
public class RepoDuelo {

    private List<Personaje> personajes;

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

    public Personaje getPersonaje(Long id) {
        for (Personaje personaje : this.personajes) {
            if (personajes.getId().equals(id)) {
                return personaje;
            }
        }
        return null;
    }

}
