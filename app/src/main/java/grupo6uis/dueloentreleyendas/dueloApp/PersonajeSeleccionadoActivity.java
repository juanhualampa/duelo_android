package grupo6uis.dueloentreleyendas.dueloApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import grupo6uis.dueloentreleyendas.R;

public class PersonajeSeleccionadoActivity extends AppCompatActivity {

    private String personaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.personaje_seleccionado_app_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        Intent intent = getIntent();
        personaje = intent.getStringExtra("personaje");
        String[] especialidades = intent.getStringArrayExtra("especialidades");
        String[] debilidades = intent.getStringArrayExtra("debilidades");
        String mejorPosicion = intent.getStringExtra("mejorPosicion");

        toolbar.setTitle(personaje);

        ListView listView = (ListView) findViewById(R.id.firstListView);
        ArrayAdapter<String> especialidadesAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,
                especialidades
        );
        listView.setAdapter(especialidadesAdapter);

        ListView listView2 = (ListView) findViewById(R.id.secondListView);
        ArrayAdapter<String> debilidadesAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,
                debilidades
        );



        listView2.setAdapter(debilidadesAdapter);

        TextView textView2 = (TextView) findViewById(R.id.secondTextbox);
        textView2.setText(mejorPosicion);

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
            navigateUpTo(new Intent(this, SeleccionarPersonajeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void estadisticas(View view){
        Intent intent = new Intent(this, EstadisticasActivity.class);
        intent.putExtra("personaje",personaje);
        startActivity(intent);
    }
}
