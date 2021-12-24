package id.aryasa.tugas_modul_individu;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private List<SiswaHandler> siswaHandlerList;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView textNama;
        public TextView textJumlah;
        public TextView textJenisKelamin;
        public TextView textMetode;
        public TextView textHobi;
        public TextView textBulan;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            textNama = itemView.findViewById(R.id.Nama);
            textJumlah = itemView.findViewById(R.id.viewJumlah);
            textJenisKelamin = itemView.findViewById(R.id.textJenisKelamin);
            textMetode = itemView.findViewById(R.id.viewMetode);
            textHobi = itemView.findViewById(R.id.hobi);
            textBulan = itemView.findViewById(R.id.bulan);
        }
    }

    public RecyclerAdapter(List<SiswaHandler> siswaHandlerList){
        this.siswaHandlerList = siswaHandlerList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(v);
        return  recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        SiswaHandler siswaHandler = siswaHandlerList.get(position);
        holder.textNama.setText(siswaHandler.getNama());
        holder.textJumlah.setText(siswaHandler.getJumlah_pembayaran());
        holder.textJenisKelamin.setText(siswaHandler.getJenisKelamin());
        holder.textMetode.setText(siswaHandler.getMetode_bayar());
        holder.textHobi.setText(siswaHandler.getHobi());
        holder.textBulan.setText(siswaHandler.getBulan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer id = Integer.valueOf(siswaHandler.getId());
                Intent update = new Intent(holder.itemView.getContext(), UpdateActivity.class);
                update.putExtra("id", id);
                holder.itemView.getContext().startActivity(update);
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswaHandlerList.size();
    }
}