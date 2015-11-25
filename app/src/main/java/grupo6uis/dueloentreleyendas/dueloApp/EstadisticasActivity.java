package grupo6uis.dueloentreleyendas.dueloApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import grupo6uis.dueloentreleyendas.R;

public class EstadisticasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_app_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String personaje = intent.getStringExtra("personaje");

        TextView textView = (TextView) findViewById(R.id.nombrePersonaje);
        textView.setText(personaje);

    }

}
