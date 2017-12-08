package juniorvalerav.polisteriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setProblemActivity extends AppCompatActivity {
    private Problem mProblema;

    //Atributos Problemas
    private Spinner mSpinner;
    private EditText mTitulo;
    private EditText mDescripcion;
    private EditText mAvenida;
    private EditText mCalle;
    private Button mSubirButton;
    private Button mSubirImageButton;
    //Firebase
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
        String avenida = mAvenida.getText().toString();
        String calle = mCalle.getText().toString();
        String estado = mSpinner.getSelectedItem().toString();


        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String uid = user.getUid();



        if(!TextUtils.isEmpty(titulo) && !TextUtils.isEmpty(descripcion)){
            DatabaseReference nuevoProblema = myRef.push();
            String key = myRef.push().getKey();
            mProblema = new Problem(titulo,descripcion,"downloadURL","Esperando", avenida,calle,estado,
                    2,uid,key);
            nuevoProblema.setValue(mProblema);

            Intent intent = new Intent(getApplicationContext(),problemsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();

        }else{
            Toast.makeText(this, "Error al ingresar datos, verifique.", Toast.LENGTH_SHORT).show();
        }

    }


    public  void BindUI(){

        mTitulo = findViewById(R.id.tituloEditText);
        mAvenida= findViewById(R.id.avenidaEditText);
        mCalle = findViewById(R.id.calleEditText);
        mDescripcion = findViewById(R.id.descripcionEditText);
        mSubirButton = findViewById(R.id.uploadButton);
        mSubirImageButton = findViewById(R.id.imageButton);
        mSpinner = findViewById(R.id.spinner);

    }

    public void setSpinner(){
         mSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);
    }


    public void Cancel(View view) {
        Intent intent = new Intent(getApplicationContext(),watchProblems.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
