package grupo6uis.dueloentreleyendas.duelo.service;

import java.util.List;

import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by luciano on 26/11/15.
 */
public interface DueloService {

    @GET("nombre_personajes")
    public Call<List<Personaje>> getPersonajes();
}
