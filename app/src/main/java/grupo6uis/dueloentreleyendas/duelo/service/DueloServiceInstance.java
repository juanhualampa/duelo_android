package grupo6uis.dueloentreleyendas.duelo.service;

import retrofit.Retrofit;

/**
 * Created by luciano on 27/11/15.
 */
public class DueloServiceInstance {

    public static DueloService createDueloService() {
        String SERVER_IP = "10.0.2.2"; //esta ip se usa para comunicarse con mi localhost en el emulador de Android Studio
        String SERVER_IP_GENY = "192.168.56.1";//esta ip se usa para comunicarse con mi localhost en el emulador de Genymotion
        String API_URL = "http://"+ SERVER_IP_GENY +":9000";
        Retrofit restAdapter = new Retrofit.Builder().baseUrl(API_URL).build();
        return restAdapter.create(DueloService.class);
    }

}
