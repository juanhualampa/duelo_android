package grupo6uis.dueloentreleyendas.dueloApp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import grupo6uis.dueloentreleyendas.R;

public class PersonajeSeleccionadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String personaje = intent.getStringExtra("personaje");
        String[] especialidades = intent.getStringArrayExtra("especialidades");
        String[] debilidades = intent.getStringArrayExtra("debilidades");
        String mejorPosicion = intent.getStringExtra("mejorPosicion");
        TextView textView = new TextView(this);
        textView.setText(personaje);
        setContentView(textView);

        ListView listView = new ListView(this);
        ArrayAdapter<String> especialidadesAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,
                especialidades
        );
        listView.setAdapter(especialidadesAdapter);

        ListView listView2 = new ListView(this);
        ArrayAdapter<String> debilidadesAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,
                debilidades
        );
        listView.setAdapter(debilidadesAdapter);

        TextView textView2 = new TextView(this);
        textView.setText(mejorPosicion);
        setContentView(textView);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //



        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(PersonajeSeleccionadoFragment.ARG_ITEM_ID,
                    getIntent().getSerializableExtra(PersonajeSeleccionadoFragment.ARG_ITEM_ID));
            PersonajeSeleccionadoFragment fragment = new PersonajeSeleccionadoFragment();
            fragment.setArguments(arguments);
            //getSupportFragmentManager().beginTransaction()
                    //.add(R.id.pelicula_detail_container, fragment)
                    //.commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, SeleccionarPersonajeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
