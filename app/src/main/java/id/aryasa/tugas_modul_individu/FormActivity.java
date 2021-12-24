package id.aryasa.tugas_modul_individu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FormActivity extends AppCompatActivity {

    private EditText jumlah_bayar, nama;
    private Spinner metode_bayar;
    private CheckBox c_menyanyi, c_olahraga, c_gambar, c_game, c_travel, c_baca;
    private Button reset, submit;
    private Intent toRecycler;
    private RadioButton kelaminTerpilih, radiolaki;
    private RadioGroup grup_kelamin;
    private SeekBar bulan;
    private TextView angka_bulan;

    private String nama_siswa, jumlah_pembayaran, jeniskelamin_siswa, strMetode, hobi_siswa, strBulan, seekbarValueString;
    private Integer seekbarValueInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

//      Set ID komponen form java dan xml
        jumlah_bayar = findViewById(R.id.inputjumlah);
        nama = findViewById(R.id.inputnama);
        grup_kelamin = findViewById(R.id.grup_kelamin);
        radiolaki = findViewById(R.id.laki_laki);
        metode_bayar = findViewById(R.id.inputmetode);
        bulan = findViewById(R.id.bulan);
        angka_bulan = findViewById(R.id.angka_bulan);


        c_menyanyi = findViewById(R.id.C_menyanyi);
        c_olahraga = findViewById(R.id.C_olahraga);
        c_gambar = findViewById(R.id.C_menggambar);
        c_game = findViewById(R.id.C_game);
        c_travel = findViewById(R.id.C_travel);
        c_baca = findViewById(R.id.C_baca);

        reset = findViewById(R.id.buttonResetForm);
        submit = findViewById(R.id.buttonSubmitForm);


//      Instance object Intent
        toRecycler = new Intent(this, RecyclerActivity.class);

//      Atur input nama menjadi focus pertama
        nama.requestFocus();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Berhasil di Reset";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                resetForm();
            }
        });

        bulan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarValueInt = progress;
                seekbarValueString = Integer.toString(seekbarValueInt);
                angka_bulan.setText("Bulan : " + seekbarValueString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Berhasil di Submit";
                int duration = Toast.LENGTH_SHORT;

                nama_siswa = nama.getText().toString();
                jumlah_pembayaran = jumlah_bayar.getText().toString();
                jeniskelamin_siswa = "";
                int idKelamin = grup_kelamin.getCheckedRadioButtonId();
                kelaminTerpilih = (RadioButton) findViewById(idKelamin);
                jeniskelamin_siswa = kelaminTerpilih.getText().toString();
                strMetode = metode_bayar.getSelectedItem().toString();
                hobi_siswa = "";
                if (c_menyanyi.isChecked()) {
                    hobi_siswa = hobi_siswa + c_menyanyi.getText().toString() + ",";
                }
                if (c_olahraga.isChecked()) {
                    hobi_siswa = hobi_siswa + c_olahraga.getText().toString() + ",";
                }
                if (c_gambar.isChecked()) {
                    hobi_siswa = hobi_siswa + c_gambar.getText().toString() + ",";
                }
                if (c_game.isChecked()) {
                    hobi_siswa = hobi_siswa + c_game.getText().toString() + ",";
                }
                if (c_travel.isChecked()) {
                    hobi_siswa = hobi_siswa + c_travel.getText().toString() + ",";
                }
                if (c_baca.isChecked()) {
                    hobi_siswa = hobi_siswa + c_baca.getText().toString() + ",";
                }
                if (!hobi_siswa.isEmpty()) {
                    hobi_siswa = hobi_siswa.substring(0, hobi_siswa.length() - 1);
                }
                Integer semesterTerpilih = bulan.getProgress();
                strBulan = semesterTerpilih.toString();

                if (nama_siswa.isEmpty()) {
                    nama.requestFocus();
                    nama.setError("Silahkan isi nama dahulu!");
                } else if (jumlah_pembayaran.isEmpty()) {
                    jumlah_bayar.requestFocus();
                    jumlah_bayar.setError("Silahkan isi jumlah pembayaran dahulu!");
                } else if (jeniskelamin_siswa.isEmpty()) {
                    grup_kelamin.requestFocus();
                } else if (strMetode.isEmpty()) {
                    metode_bayar.requestFocus();
                } else if (hobi_siswa.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pilih minimal 1 hobi", Toast.LENGTH_LONG).show();
                } else if (strBulan.isEmpty()) {
                    bulan.requestFocus();
                } else {
                    simpanDataMahasiswa();
                }
            }
        });
        Log.i("State", "on Create Activity");
    }


    private void simpanDataMahasiswa() {

        StringBuilder rekapData = new StringBuilder();
        rekapData.append("Nama : " + nama_siswa + "\n\n");
        rekapData.append("Jumlah Pembayaran : " + jumlah_pembayaran + "\n\n");
        rekapData.append("Jenis Kelamin : " + jeniskelamin_siswa + "\n\n");
        rekapData.append("Metode Pembayaran : " + strMetode + "\n\n");
        rekapData.append("Hobi : " + hobi_siswa + "\n\n");
        rekapData.append("Bulan : " + strBulan + "\n\n");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title dialog
        alertDialogBuilder.setTitle("Apakah data sudah benar?");


        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(rekapData.toString())
                .setCancelable(false)
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                        DatabaseSiswa databaseSiswa = new DatabaseSiswa(getApplicationContext());
                        SiswaHandler siswaHandler = new SiswaHandler();
                        siswaHandler.setNama(nama_siswa);
                        siswaHandler.setJumlah_pembayaran(jumlah_pembayaran);
                        siswaHandler.setJenisKelamin(jeniskelamin_siswa);
                        siswaHandler.setMetode_bayar(strMetode);
                        siswaHandler.setHobi(hobi_siswa);
                        siswaHandler.setBulan(strBulan);

                        boolean tambah_siswa = databaseSiswa.addSiswa(siswaHandler);
                        if (tambah_siswa) {
                            Toast.makeText(FormActivity.this, "Tambah Kas Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(FormActivity.this, "Tambah Kas Gagal", Toast.LENGTH_SHORT).show();
                        }
                        databaseSiswa.close();

                        Intent intent = new Intent(getApplicationContext(),RecyclerActivity.class);
                        intent.putExtra("Nama",nama_siswa );
                        intent.putExtra("Jumlah_Pembayaran",jumlah_pembayaran );
                        intent.putExtra("Jenis_Kelamin",jeniskelamin_siswa );
                        intent.putExtra("Metode_Pembayaran",strMetode );
                        intent.putExtra("Hobi",hobi_siswa );
                        intent.putExtra("Bulan",strBulan );
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Data telah disimpan!", Toast.LENGTH_SHORT).show();
                    }

                })
                .setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }

    public void resetForm() {
        jumlah_bayar.setText(null);
        nama.setText(null);
        metode_bayar.setSelection(0);
        grup_kelamin.clearCheck();
        radiolaki.setChecked(true);
        bulan.setProgress(0);
        c_menyanyi.setChecked(false);
    }
}