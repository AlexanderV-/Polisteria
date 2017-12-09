package juniorvalerav.polisteriaapp;

/**
 * Created by SNAP on 7/12/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;



public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder> {


    private List<Problem> Problemas;
    private static Context activity;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;

    public void setActivity(Context activity) {
        ProblemAdapter.activity = activity;
    }

    public ProblemAdapter(List<Problem> problemas, Context applicationContext) {
        this.Problemas = problemas;
        activity =  applicationContext;
    }

    public List<Problem> getProblemas() {
        return Problemas;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Titulo, Descripcion;
        ImageView imageCard;

        public ViewHolder(View itemView) {
            super(itemView);
            Titulo = itemView.findViewById(R.id.tituloProblem);
            Descripcion = itemView.findViewById(R.id.descripcionProblem);
            imageCard = itemView.findViewById(R.id.imageProblem);
            Picasso.with(activity.getApplicationContext())
                    .load("http://i.imgur.com/DvpvklR.png")
                    .fit()
                    .into(imageCard);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.problemcardview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Problem problema = Problemas.get(position);
        mDatabase = FirebaseDatabase.getInstance();
        final String key = problema.getKey();
        holder.Titulo.setText(problema.gettitulo());
        holder.Descripcion.setText(problema.getdescripcion());

        holder.imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getApplicationContext(),watchProblems.class);
                intent.putExtra("key", key);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return Problemas.size();
    }




}
