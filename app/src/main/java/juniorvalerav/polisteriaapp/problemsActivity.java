package juniorvalerav.polisteriaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class problemsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private Problem Problema;
    public static Context appContext;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private TextView mUsername;
    private TextView mUserState;
    private TextView mUserExtra;
    private TextView mSignOut;
    private TextView mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
        appContext = getApplicationContext();

        //BindUI

        mUsername = findViewById(R.id.usernameTextView);
        mUserState = findViewById(R.id.stateUserTextView);
        mUserExtra = findViewById(R.id.extraUserTextView);
        mSignOut = findViewById(R.id.signOutTextView);
        mSettings = findViewById(R.id.settingsTextView);


        //Firebase
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        assert user != null;
        String uid = user.getUid();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference().child("Problemas");
        mUsername.setText(user.getEmail());
        mUserState.setText("Merida");
        mUserExtra.setText("Ciudadano");
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                goToLogin();
            }
        });

        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSetProblem();
            }
        });

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerViewLayout);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);




        llenar();

    }

    private void goToSetProblem() {
        Intent intent = new Intent(getApplicationContext(),setProblemActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void goToLogin() {
        Intent intent = new Intent(getApplicationContext(),loginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    public void llenar() {


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Problem> problemLista = new ArrayList<>();
                Problema = new Problem();
                problemLista.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Problema = ds.getValue(Problem.class);
                    problemLista.add(Problema);
                }

                mAdapter = new ProblemAdapter(problemLista, appContext);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
