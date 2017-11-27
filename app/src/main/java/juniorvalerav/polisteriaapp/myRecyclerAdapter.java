package juniorvalerav.polisteriaapp;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class myRecyclerAdapter extends RecyclerView.Adapter<myRecyclerAdapter.ViewHolder> {
    private String[] mDataSet = {"Deslave en Sta Juanta", "Basura en Chorros", "Sin luz en Hechichera",
    "No hay agua en la Vuelta de Lola", "Filtracion de agua en los la avenida los Proceres ",
    "Huecos en la via hacia tabay"};

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ViewHolder(TextView tv) {
            super(tv);
            textView = tv;
        }
    }


    public myRecyclerAdapter() {

    }


    @Override
    public myRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.problema_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.textView.setText(mDataSet[i]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}