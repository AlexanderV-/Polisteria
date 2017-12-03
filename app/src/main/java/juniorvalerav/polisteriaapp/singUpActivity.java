package juniorvalerav.polisteriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class singUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmailSignUp;
    private EditText mPasswordSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        mAuth = FirebaseAuth.getInstance();
        mEmailSignUp = findViewById(R.id.emailSignUp);
        mPasswordSignUp = findViewById(R.id.passwordSignUp);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser User = mAuth.getCurrentUser();
                if(User != null)
                    {Log.w("UsuarioStatus", "Usuario Logeado" + User.getEmail());}
                else
                    {Log.w("UsuarioStatus","Usuario No Logeado");}
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void Login(View view) {
        String mEmailUser = mEmailSignUp.getText().toString();
        String mPasswordUser = mPasswordSignUp.getText().toString();

        CrearUsuario(mEmailUser, mPasswordUser);
    }

    public void CrearUsuario(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                   /* Intent intent = new Intent(getApplicationContext(), problemsActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);*/
                    Toast.makeText(singUpActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    Log.d("SignUpTAG", "Inicio de sesion exitoso");
                    // startActivity(intent);
                }
                else {
                    Toast.makeText(singUpActivity.this, "Fallo de Sesion", Toast.LENGTH_SHORT).show();
                    Log.d("SignUpTAG", "Inicio de sesion fallido");
                }
            }
        });
    }

}
