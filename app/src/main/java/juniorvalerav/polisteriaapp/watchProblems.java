package juniorvalerav.polisteriaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class watchProblems extends AppCompatActivity {
    Problem problemas;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private TextView mEstatus;
    private TextView mDescripcion;
    private TextView mTitulo;
    private TextView mEstado;

    private String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_problems);
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        final String key = getIntent().getStringExtra("key");
        final String titulo = getIntent().getStringExtra("titulo");
        final String descripcion = getIntent().getStringExtra("descripcion");
        final String estado = getIntent().getStringExtra("estado");
        final String estatus = getIntent().getStringExtra("estatus");


        mDescripcion = findViewById(R.id.descripcionTextView);
        mEstatus = findViewById(R.id.estatusTextView);
        mTitulo = findViewById(R.id.tituloTextView);
        mEstado = findViewById(R.id.supervisorTextView);


       /* mTitulo.setText(titulo);
        mDescripcion.setText(descripcion);
        mEstado.setText(estado);
        mEstatus.setText(estatus);*/

       Query query = myRef.child("Problemas").orderByChild("key").equalTo(key);
       //Toast.makeText(this, "Key "+ key, Toast.LENGTH_SHORT).show();

       query.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               Problem problema = null;
               for (DataSnapshot child : dataSnapshot.getChildren()) {
                   problema = child.getValue(Problem.class);
                   mTitulo.setText(problema.gettitulo());
                   mDescripcion.setText(problema.getdescripcion());
                   mEstatus.setText(problema.getEstatus());
                   mEstado.setText(problema.getEstado());
                   Toast.makeText(watchProblems.this, "Titulo" + problema.gettitulo(), Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });



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


    public void Voluntario(View view) {
        Intent intent = new Intent(getApplicationContext(),problemsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

