package grupo6uis.dueloentreleyendas.duelo.repo;

import java.util.ArrayList;
import java.util.Arrays;
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

    private Personaje conDatos(String nombre, String[] especialidades ,String[] debilidades, String mejorPosicion){
        Personaje person = new Personaje();
        person.setNombre(nombre);
        person.setEspecialidades(especialidades);
        person.setDebilidades(debilidades);
        person.setMejorPosicion(mejorPosicion);
        return person;
    }

    private ArrayList<Personaje> damePersonajes(){
        String[] especialidades = {"Bailar","Ni idea"};
        String[] debilidades = {"Descansar","Ni idea"};
        Personaje unPersonaje1 = this.conDatos("Amumu",especialidades,debilidades,"Bottom") ;
        Personaje unPersonaje2 = this.conDatos("Axe",especialidades,debilidades,"Bottom");
        Personaje unPersonaje3 = this.conDatos("Ashe",especialidades,debilidades,"Bottom");
        Personaje unPersonaje4 = this.conDatos("Master Yi",especialidades,debilidades,"Bottom") ;
        Personaje unPersonaje5 = this.conDatos("Sniper",especialidades,debilidades,"Bottom") ;
        Personaje unPersonaje6 = this.conDatos("Katarina",especialidades,debilidades,"Bottom") ;
        Personaje unPersonaje7 = this.conDatos("Techies",especialidades,debilidades,"Bottom") ;

        return new ArrayList<>(Arrays.asList(
                unPersonaje1,unPersonaje2,unPersonaje3,unPersonaje4,unPersonaje5,unPersonaje6,unPersonaje7
        ));

    }

    private void init() {
        for ( Personaje p :this.damePersonajes()){
            RepoDuelo.getInstance().agregarPersonaje(p);
        }

    }

    private void agregarPersonaje(Personaje personaje) {
        personajes.add(personaje);
    }

    public Personaje getPersonaje(int id) {
        for (Personaje personaje : this.personajes) {
            if (personaje.getId()== id) {
                return personaje;
            }
        }
        return null;
    }

    public List<Personaje> getPersonajes(String titulo) {
        return getPersonajes(titulo, MAX_RESULTS);
    }

    public List<Personaje> getPersonajes(){
        return this.personajes;
    }

    public Personaje getPersonaje(String nombreP){
        Iterator<Personaje> it = personajes.iterator();
        Personaje res = null;
        while (it.hasNext()) {
            Personaje personaje = it.next();
            if (personaje.getNombre() == nombreP) {
                res = personaje;
            }
        }
        return res;
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
