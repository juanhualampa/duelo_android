package grupo6uis.dueloentreleyendas.dueloApp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grupo6uis.dueloentreleyendas.R;

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
}
