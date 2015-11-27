package grupo6uis.dueloentreleyendas.dueloApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import grupo6uis.dueloentreleyendas.R;

/**
 * An activity representing a list of Personajes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonajeSeleccionadoActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PersonajeSeleccionadoFragment} and the item details
 * (if present) is a {@link PersonajeSeleccionadoFragment}.
 * <p/>
 * This activity also implements the required
 * {@link SeleccionarPersonajeFragment.Callbacks} interface
 * to listen for item selections.
 */
public class SeleccionarPersonajeActivity extends FragmentActivity implements SeleccionarPersonajeFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duelo_app_bar);

        if (findViewById(R.id.personaje_seleccionado_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((SeleccionarPersonajeFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.personaje_list))
                    .setActivateOnItemClick(true);
        }

        //TODO old toolbar
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());*/

    }

    /**
     * Callback method from {@link SeleccionarPersonajeFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(PersonajeSeleccionadoFragment.ARG_ITEM_ID, id);
            PersonajeSeleccionadoFragment fragment = new PersonajeSeleccionadoFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.personaje_seleccionado_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PersonajeSeleccionadoActivity.class);
            detailIntent.putExtra(PersonajeSeleccionadoFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

/**
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccionar_personaje, menu);
        return true;
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(PersonajeSeleccionadoFragment.ARG_ITEM_ID, id);
            PersonajeSeleccionadoFragment fragment = new PersonajeSeleccionadoFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.personaje_seleccionado_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PersonajeSeleccionadoActivity.class);
            //Log.w("Pelis", personaje.getTitulo());
            detailIntent.putExtra(PersonajeSeleccionadoFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
    */
}
