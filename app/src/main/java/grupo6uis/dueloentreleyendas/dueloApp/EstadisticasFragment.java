package grupo6uis.dueloentreleyendas.dueloApp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grupo6uis.dueloentreleyendas.R;
import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;
import grupo6uis.dueloentreleyendas.duelo.service.DueloService;
import grupo6uis.dueloentreleyendas.duelo.service.DueloServiceInstance;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class EstadisticasFragment extends Fragment {


    public EstadisticasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_estadisticas, container, false);
    }

    private void pedirDatosPesonaje(String libroId) {
        DueloService dueloService = DueloServiceInstance.createDueloService();
        dueloService.getEstadisticasPersonaje(libroId,new retrofit.Callback<Personaje>() {
            @Override
            public void onResponse(Response<Personaje> response, Retrofit retrofit) {
                agregarEstadisticasPersonaje(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("DueloApp", t.getMessage());
            }

        });
    }

    private void agregarEstadisticasPersonaje(Personaje personaje) {
        //setListAdapter(new PersonajeAdapter(getActivity(), personaje));
    }




}
