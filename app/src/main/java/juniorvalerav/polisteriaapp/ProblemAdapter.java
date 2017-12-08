package juniorvalerav.polisteriaapp;

/**
 * Created by SNAP on 7/12/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder> {


    private List<Problem> Problemas;
   private static Context activity;


    public void setActivity(Context activity) {
        this.activity = activity;
    }

    public ProblemAdapter(List<Problem> problemas, Context applicationContext) {
        this.Problemas = problemas;
        this.activity =  applicationContext;
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
            Picasso.with(activity.getApplicationContext()).load("http://i.imgur.com/DvpvklR.png").into(imageCard);
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
        holder.Titulo.setText(problema.getTitulo());
        holder.Descripcion.setText(problema.getDescripcion());


    }

    @Override
    public int getItemCount() {
        return Problemas.size();
    }




}
