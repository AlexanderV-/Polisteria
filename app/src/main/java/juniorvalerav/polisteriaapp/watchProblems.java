package juniorvalerav.polisteriaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class watchProblems extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_problems);

        ListView moviesList= findViewById(R.id.comentariosListView);

        ArrayList<String> movies = new ArrayList<String>();
          movies.add("Ya hemos ido a la alcadia para notificar el caso");
          movies.add("\n Muchas esperanza y fuerzas !! ");
          movies.add("\n Les deseamos lo mejor ");
          movies.add("\n Ya me ofreci como voluntario, ayudemos todos ");
          movies.add("Hola mundo");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    movies );

            moviesList.setAdapter(arrayAdapter);
            moviesList.setElevation(10);
        }

    }

