package grupo6uis.dueloentreleyendas.duelo.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

import grupo6uis.dueloentreleyendas.R;
import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;
import grupo6uis.dueloentreleyendas.dueloApp.PersonajeSeleccionadoFragment;

/**
 * Created by Juan on 24-Nov-15.
 */
public class PersonajeAdapter extends ArrayAdapter<Personaje> {
    public PersonajeAdapter(Context context, List<Personaje> personajes) {
        super(context, R.layout.personaje_row, personajes);
    }
}
