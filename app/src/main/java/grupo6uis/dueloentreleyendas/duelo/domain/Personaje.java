package grupo6uis.dueloentreleyendas.duelo.domain;

import java.io.Serializable;

/**
 * Created by Juan on 24-Nov-15.
 */
public class Personaje implements Serializable {
    Long id;
    private String nombre;


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

