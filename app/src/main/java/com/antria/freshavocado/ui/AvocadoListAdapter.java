package com.antria.freshavocado.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.antria.freshavocado.R;
import com.antria.freshavocado.database.Avocado;
import java.util.List;

public class AvocadoListAdapter extends RecyclerView.Adapter<AvocadoListAdapter.AvocadoViewHolder> {

    class AvocadoViewHolder extends RecyclerView.ViewHolder {
        private final TextView avocadoRipeness;
        private final TextView avocadoDate;

        private AvocadoViewHolder(View itemView) {
            super(itemView);
            avocadoRipeness = itemView.findViewById(R.id.avocado_ripeness);
            avocadoDate = itemView.findViewById(R.id.avocado_purchase_date);
        }
    }

    private final LayoutInflater mInflater;
    private List<Avocado> mAvocados;

    public AvocadoListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public AvocadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AvocadoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AvocadoViewHolder holder, int position) {
        if (mAvocados != null) {
            Avocado current = mAvocados.get(position);
            holder.avocadoRipeness.setText(String.valueOf(current.ripeness));
            holder.avocadoDate.setText((current.purchaseDate).toString());
        } else {
            // Covers the case of data not being ready yet.
            holder.avocadoRipeness.setText("No Word");
            holder.avocadoDate.setText("No Word");
        }
    }

    public void setAvocados(List<Avocado> avocados){
        mAvocados = avocados;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mAvocados has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAvocados != null)
            return mAvocados.size();
        else return 0;
    }
}