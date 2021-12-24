package id.aryasa.tugas_modul_individu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    private TextView no_data;
    private ImageView empty_imageview;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter; //Jembatan antara data arraylist dengan recyclerview
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton add_button;
    private Object MahasiswaHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        ArrayList<SiswaHandler> siswaHandler = new ArrayList<SiswaHandler>();

//        Intent intent = getIntent();
//        String nama_siswa = intent.getStringExtra("Nama");
//        String str_pembayaran = intent.getStringExtra("Jumlah_Pembayaran");
//        String jeniskelamin_siswa = intent.getStringExtra("Jenis_Kelamin");
//        String str_metode = intent.getStringExtra("Metode_Pembayaran");
//        String hobi_siswa = intent.getStringExtra("Hobi");
//        String str_bulan = intent.getStringExtra("Bulan");

        no_data = findViewById(R.id.no_data);
        empty_imageview = findViewById(R.id.empty_imageview);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        final DatabaseSiswa db = new DatabaseSiswa(getApplicationContext());
        Cursor cursor = db.getSiswa();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            no_data.setVisibility(View.GONE);
            empty_imageview.setVisibility(View.GONE);
            while (!cursor.isAfterLast()) {
                SiswaHandler siswaHandlerList = new SiswaHandler();
                siswaHandlerList.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                siswaHandlerList.setNama((cursor.getString(cursor.getColumnIndexOrThrow("nama_siswa"))));
                siswaHandlerList.setJumlah_pembayaran((cursor.getString(cursor.getColumnIndexOrThrow("jumlah_pembayaran"))));
                siswaHandlerList.setJenisKelamin((cursor.getString(cursor.getColumnIndexOrThrow("jeniskelamin_siswa"))));
                siswaHandlerList.setMetode_bayar((cursor.getString(cursor.getColumnIndexOrThrow("metode_pembayaran"))));
                siswaHandlerList.setHobi((cursor.getString(cursor.getColumnIndexOrThrow("hobi_siswa"))));
                siswaHandlerList.setBulan((cursor.getString(cursor.getColumnIndexOrThrow("bulan"))));
                siswaHandler.add(siswaHandlerList);
                cursor.moveToNext();
            }
            db.close();
        }
        else {
            no_data.setVisibility(View.VISIBLE);
            empty_imageview.setVisibility(View.VISIBLE);
        }

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerAdapter(siswaHandler);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    protected void onStart(){
        Toast.makeText(getApplicationContext(), "Selamat Datang", Toast.LENGTH_SHORT).show();
        super.onStart();
        Log.i("State", "on Start Activity");
    }

    protected void onResume(){
        Toast.makeText(getApplicationContext(), "Selamat Datang Kembali", Toast.LENGTH_SHORT).show();
        super.onResume();
        Log.i("State", "on Resume Activity");
    }

    protected void onStop(){
        super.onStop();
        Log.i("State", "on Stop Activity");
    }

    protected void onDestroy(){
        Toast.makeText(getApplicationContext(), "Selamat Tinggal", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        Log.i("State", "on Destroy Activity");
    }

    protected void onPause(){
        super.onPause();
        Log.i("State", "on Pause Activity");
    }
}