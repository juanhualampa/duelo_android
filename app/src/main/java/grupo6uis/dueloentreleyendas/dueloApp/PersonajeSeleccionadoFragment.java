package grupo6uis.dueloentreleyendas.dueloApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class PersonajeSeleccionadoFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    public PersonajeSeleccionadoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String itemID = getArguments().getString(ARG_ITEM_ID);
            pedirDatosPesonaje(itemID);
        }
    }

    private void pedirDatosPesonaje(String libroId) {
        DueloService dueloService = DueloServiceInstance.createDueloService();
        dueloService.getCaracteristicasPersonaje(libroId,new retrofit.Callback<Personaje>() {
            @Override
            public void onResponse(Response<Personaje> response, Retrofit retrofit) {
                agregarCaracteristicasPersonaje(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("DueloApp", t.getMessage());
            }

        });
    }

    private void agregarCaracteristicasPersonaje(Personaje personaje) {
       //setListAdapter(new PersonajeAdapter(getActivity(), personaje));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_personaje_seleccionado, container, false);

        // Show the dummy content as text in a TextView.
        //if (personaje != null) {
        //   ((TextView) rootView.findViewById(R.id.pelicula_genero)).setText(pelicula.getDescripcionGenero());
        //    ImageView imgGenero = ((ImageView) rootView.findViewById(R.id.imgGenero));
        //    imgGenero.setImageDrawable(getResources().getDrawable(new GeneroAdapter().getIconoGenero(pelicula)));
        //    ((TextView) rootView.findViewById(R.id.pelicula_actores)).setText(pelicula.getActores());
        //    ((TextView) rootView.findViewById(R.id.pelicula_sinopsis)).setText(pelicula.getSinopsis());
        //}

        return rootView;
    }
}
