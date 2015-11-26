package grupo6uis.dueloentreleyendas.dueloApp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import javax.security.auth.callback.Callback;

import grupo6uis.dueloentreleyendas.R;
import grupo6uis.dueloentreleyendas.duelo.adapter.PersonajeAdapter;
import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;
import grupo6uis.dueloentreleyendas.duelo.repo.RepoDuelo;
import grupo6uis.dueloentreleyendas.duelo.service.DueloService;
import retrofit.Response;
import retrofit.Retrofit;

public class SeleccionarPersonajeFragment extends ListFragment {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;

    public RepoDuelo repo;

    public interface Callbacks {
        void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SeleccionarPersonajeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pedirPersonajes();

        filtrarPersonajes();
        verPersonajeElegido();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        Personaje libro = (Personaje) listView.getAdapter().getItem(position);
        mCallbacks.onItemSelected(String.valueOf(libro.getId()));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }





    //de nuevo
    private void pedirPersonajes() {
        DueloService dueloService = createDueloService();
        dueloService.getPersonajes(new retrofit.Callback<List<Personaje>>() {
            @Override
            public void onResponse(Response<List<Personaje>> response, Retrofit retrofit) {
                agregarPersonajes(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.e("DueloApp", t.getMessage());
            }

        });
    }

    private void agregarPersonajes(List<Personaje> libros) {
        setListAdapter(new PersonajeAdapter(getActivity(), libros));
    }

    private DueloService createDueloService() {
        //MMM código repetido, habría que modificar esto no?
        String SERVER_IP = "10.0.2.2"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.56.1";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP_GENY +":9000";

        Retrofit restAdapter = new Retrofit.Builder().baseUrl(API_URL).build();
        DueloService dueloService = restAdapter.create(DueloService.class);
        return dueloService;
    }


    private void filtrarPersonajes(){
        //para empezar
        EditText inputSearch = (EditText) getView().findViewById(R.id.idFiltrarEditText);
        //repo = RepoDuelo.getInstance();
        ListView listaPersonajes = (ListView) getView().findViewById(R.id.personajeslistView);
        final ArrayAdapter<Personaje> adapter = new ArrayAdapter<Personaje>(this,R.layout.personaje,repo.getPersonajes());
        listaPersonajes.setAdapter(adapter);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }

    private void verPersonajeElegido() {
        ListView listaPersonajes = (ListView) getView().findViewById(R.id.personajeslistView);

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



}
