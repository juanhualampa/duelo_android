package grupo6uis.dueloentreleyendas.dueloApp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import grupo6uis.dueloentreleyendas.R;
import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;

public class PersonajeSeleccionadoFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private Personaje personaje;

    public PersonajeSeleccionadoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            personaje = (Personaje) getArguments().get(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(personaje.getNombre());
            } else {
                activity.setTitle(personaje.getNombre());
            }
        }
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
