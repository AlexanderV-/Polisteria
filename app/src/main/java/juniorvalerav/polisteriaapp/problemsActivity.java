package juniorvalerav.polisteriaapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class problemsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<Problem> problemList = new ArrayList<>();
    public static Context appContext;
    //recyclerViewLayout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
        appContext = getApplicationContext();
        mRecyclerView = findViewById(R.id.recyclerViewLayout);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        llenar();
        mAdapter = new ProblemAdapter(problemList, appContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void llenar (){
        Problem problem = new Problem("Hola Titulo", "Hola Descripcion", "sdasdasfsdfsdfsfs.dhsdovm.com", 13);
        problemList.add(problem);

        problem = new Problem("Hola Titulo2", "Hola Descripc2ion", "sdasdasfsdfsdfsfs.dhsd2ovm.com", 123);
        problemList.add(problem);

        problem = new Problem("Hola T3itulo", "Hola De3scripcion", "sdasdasfsdfsdfsfs.dhsd3ovm.com", 13);
        problemList.add(problem);
    }

}

