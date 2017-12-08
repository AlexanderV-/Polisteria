package juniorvalerav.polisteriaapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setProblemActivity extends AppCompatActivity {
    private Problem mProblema;
    private Usuario mUser;
    private EditText mTitulo;
    private EditText mDescripcion;
    private Button mSubirButton;
    private Button mCancelButton;
    private Button mSubirImageButton;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_problem);
        setSpinner();
        BindUI();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
         myRef = mDatabase.getReference().child("Problemas");


    }

    public void SubirImagen(View view) {


        String titulo = mTitulo.getText().toString();
        String descripcion = mDescripcion.getText().toString();
        FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        String uid = user.getUid();
        mProblema = new Problem(titulo,descripcion,"downloadURL",2, uid);


        if(!TextUtils.isEmpty(titulo) && !TextUtils.isEmpty(descripcion)){
            DatabaseReference nuevoProblema = myRef.push();
            nuevoProblema.setValue(mProblema);

        }





    }


    public  void BindUI(){

        mTitulo = findViewById(R.id.tituloEditText);
        mDescripcion = findViewById(R.id.descripcionEditText);
        mSubirButton = findViewById(R.id.uploadButton);
        mCancelButton = findViewById(R.id.cancelButton);
        mSubirImageButton = findViewById(R.id.imageButton);

    }

    public void setSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    public void Llenar(){

    }
}
