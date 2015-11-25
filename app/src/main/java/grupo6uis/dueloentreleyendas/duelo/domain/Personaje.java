package grupo6uis.dueloentreleyendas.duelo.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Juan on 24-Nov-15.
 */
public class Personaje implements Serializable {
    Long id;
    private String nombre;
    private String[] especialidades;
    private String[] debilidades;
    private String mejorPosicion;

    public void setEspecialidades(String[] especialidades) {
        this.especialidades = especialidades;
    }

    public void setDebilidades(String[] debilidades) {
        this.debilidades = debilidades;
    }

    public void setMejorPosicion(String mejorPosicion) {
        this.mejorPosicion = mejorPosicion;
    }

    @Override
    public String toString(){
        return this.getNombre();
    }

    public String[]  getEspecialidades(){return this.especialidades;}

    public String[] getDebilidades(){
        return this.debilidades;
    }

    public String getMejorPosicion(){return this.mejorPosicion;  }

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

