package grupo6uis.dueloentreleyendas.duelo.service;

import android.util.Log;

import java.util.List;

import grupo6uis.dueloentreleyendas.duelo.domain.Personaje;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;
import retrofit.http.Path;

/**
 * Created by luciano on 27/11/15.
 */
public class DueloServiceInstance {

    public static DueloService createDueloService() {
        //MMM código repetido, habría que modificar esto no?
        String SERVER_IP = "10.0.2.2"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.56.1";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP_GENY +":9000";
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(API_URL).build();
        DueloService dueloService = restAdapter.create(DueloService.class);
        return dueloService;
    }

}
