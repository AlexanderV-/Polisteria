package juniorvalerav.polisteriaapp;

import android.content.Intent;
import android.net.Uri;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class setProblemActivity extends AppCompatActivity {
    private Problem mProblema;

    //Atributos Problemas

    private static final int GALLERY_REQ = 1;
    private Spinner mSpinner;
    private EditText mTitulo;
    private EditText mDescripcion;
    private EditText mAvenida;
    private EditText mCalle;
    private Button mSubirButton;
    private Button mSubirImageButton;
    private Uri mUriProblema;

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
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("Image/*");
        startActivityForResult(galleryIntent,GALLERY_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQ && resultCode == RESULT_OK){
            Uri uriImage = data.getData();
            CropImage.activity(uriImage)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                mUriProblema = result.getUri();
            }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
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

    public void SubirProblema(View view) {


        String titulo = mTitulo.getText().toString();
        String descripcion = mDescripcion.getText().toString();
        String avenida = mAvenida.getText().toString();
        String calle = mCalle.getText().toString();
        String estado = mSpinner.getSelectedItem().toString();


        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String uid = user.getUid();



        if(!TextUtils.isEmpty(titulo) && !TextUtils.isEmpty(descripcion)
                && mUriProblema != null){
            DatabaseReference nuevoProblema = myRef.push();
            String key = nuevoProblema.getKey();
            mProblema = new Problem(titulo,descripcion,"downloadURL","Esperando", avenida,calle,estado,
                    2,uid,key);
            nuevoProblema.setValue(mProblema);
            Cancel(view);

        }else{
            Toast.makeText(this, "Error al ingresar datos, verifique.", Toast.LENGTH_SHORT).show();
        }


    }
}
