package grupo6uis.dueloentreleyendas.dueloApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import grupo6uis.dueloentreleyendas.R;
import grupo6uis.dueloentreleyendas.duelo.adapter.PersonajeAdapter;
import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;
import grupo6uis.dueloentreleyendas.duelo.repo.RepoDuelo;
import grupo6uis.dueloentreleyendas.duelo.service.DueloService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

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
public class SeleccionarPersonajeActivity extends AppCompatActivity implements SeleccionarPersonajeFragment.Callbacks {

    private boolean mTwoPane;
    private RepoDuelo repo;
    private DueloService dueloService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duelo_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    @Override
    protected void onStart(){
        super.onStart();

        String BASE_URL = "http://10.0.2.2:8080/videoclub-ui-grails-homes-xtend/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dueloService = retrofit.create(DueloService.class);

        this.pedirPersonajes();

       /* if (findViewById(R.id.pelicula_detail_container) != null) {
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
        }*/
        filtrarPersonajes();
        verPersonajeElegido();
    }

    private void pedirPersonajes(){
        //HACER UN GET
        Call<List<Personaje>> call = dueloService.getPersonajes();
        call.enqueue(new Callback<List<Personaje>>() {
            @Override
            public void onResponse(Response<List<Personaje>> response, Retrofit retrofit) {
                List<Personaje> peliculas = response.body();

                setListAdapter(new PersonajeAdapter(
                        getActivity(),
                        peliculas));

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("PersonajesApp", t.getMessage());
            }
        });


    }



    private void filtrarPersonajes(){
    //para empezar
        EditText inputSearch = (EditText) findViewById(R.id.idFiltrarEditText);
        //repo = RepoDuelo.getInstance();
        ListView listaPersonajes = (ListView) findViewById(R.id.personajeslistView);
        final ArrayAdapter<Personaje> adapter = new ArrayAdapter<Personaje>(this,R.layout.personaje,repo.getPersonajes());
        listaPersonajes.setAdapter(adapter);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3){
            // When user changed the Text
                adapter.getFilter().filter(cs);
        }
            @Override
            public void afterTextChanged(Editable arg0) {}
        });
    }


    private void verPersonajeElegido() {
        ListView listaPersonajes = (ListView) findViewById(R.id.personajeslistView);

        listaPersonajes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SeleccionarPersonajeActivity.this, PersonajeSeleccionadoActivity.class);
                String persElegido = ((TextView) view).getText().toString();
                Personaje pers = repo.getPersonaje(persElegido);
                intent.putExtra("personaje", pers.getNombre());
                intent.putExtra("especialidades",pers.getEspecialidades());
                intent.putExtra("debilidades",pers.getDebilidades());
                intent.putExtra("mejorPosicion",pers.getMejorPosicion());
                startActivity(intent);
            }
        });
    }


@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccionar_personaje, menu);
        return true;
    }

    @Override
    public void onItemSelected(Personaje personaje) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(PersonajeSeleccionadoFragment.ARG_ITEM_ID, personaje);
            PersonajeSeleccionadoFragment fragment = new PersonajeSeleccionadoFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    //TODO pelicula_detail_container está en layout/activity_pelicula_detail.xml
                    //.replace(R.id.pelicula_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PersonajeSeleccionadoActivity.class);
            //Log.w("Pelis", personaje.getTitulo());
            detailIntent.putExtra(PersonajeSeleccionadoFragment.ARG_ITEM_ID, personaje);
            startActivity(detailIntent);
        }
    }
}
