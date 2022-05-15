package com.example.vpisdogodkov;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MestaAdapter extends RecyclerView.Adapter<MestaAdapter.MestaViewHolder> {

    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        MestaAdapter.listener = listener;
    }

    List<MestoPrireditve> mestaPrireditve;

    public MestaAdapter(List<MestoPrireditve> mestaPrireditve) {
        this.mestaPrireditve = mestaPrireditve;
    }

    @NonNull
    @Override
    public MestaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MestaViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MestaViewHolder holder, int position) {
        MestoPrireditve mestoPrireditve = mestaPrireditve.get(position);
        holder.nazivTxt.setText(mestoPrireditve.vrniNaziv());
        holder.naslovTxt.setText(mestoPrireditve.vrniNaslov());
        holder.steviloSedezevTxt.setText("Število sedežev: " + mestoPrireditve.vrniSteviloSedezev());

        boolean isExpandable = mestaPrireditve.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mestaPrireditve.size();
    }

    public class MestaViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        RelativeLayout expandableLayout;
        TextView nazivTxt, naslovTxt, steviloSedezevTxt;
        Button izberiBtn;

        public MestaViewHolder(@NonNull View itemView) {
            super(itemView);

            nazivTxt = itemView.findViewById(R.id.city_name);
            naslovTxt = itemView.findViewById(R.id.naslov);
            steviloSedezevTxt = itemView.findViewById(R.id.stevilo_sedezev);
            izberiBtn = itemView.findViewById(R.id.izberi);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            linearLayout.setOnClickListener(view -> {
                MestoPrireditve mestoPrireditve = mestaPrireditve.get(getAdapterPosition());
                mestoPrireditve.setExpandable(!mestoPrireditve.isExpandable());
                notifyItemChanged(getAdapterPosition());
            });

            izberiBtn.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
}
