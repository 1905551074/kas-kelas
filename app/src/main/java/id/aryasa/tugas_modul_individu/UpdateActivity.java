package id.aryasa.tugas_modul_individu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText jumlah_bayar, nama;
    private Spinner metode_bayar;
    private CheckBox c_menyanyi, c_olahraga, c_gambar, c_game, c_travel, c_baca;
    private Button delete, update;
    private Intent toRecycler;
    private RadioButton kelaminTerpilih, radioperempuan, radiolaki;
    private RadioGroup grup_kelamin;
    private SeekBar bulan;
    private TextView update_angka_bulan;
    private Integer id_siswa;

    private String nama_siswa, jumlah_pembayaran, jeniskelamin_siswa, strMetode, hobi_siswa, strBulan, seekbarValueString;
    private Integer seekbarValueInt;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        jumlah_bayar = findViewById(R.id.update_inputjumlah);
        nama = findViewById(R.id.update_inputnama);
        grup_kelamin = findViewById(R.id.update_grup_kelamin);
        radiolaki = findViewById(R.id.update_laki_laki);
        radioperempuan = findViewById(R.id.update_perempuan);
        metode_bayar = findViewById(R.id.update_inputmetode);
        bulan = findViewById(R.id.update_bulan);
        update_angka_bulan = findViewById(R.id.update_angka_bulan);

        c_menyanyi = findViewById(R.id.update_C_menyanyi);
        c_olahraga = findViewById(R.id.update_C_olahraga);
        c_gambar = findViewById(R.id.update_C_menggambar);
        c_game = findViewById(R.id.update_C_game);
        c_travel = findViewById(R.id.update_C_travel);
        c_baca = findViewById(R.id.update_C_baca);

        delete = findViewById(R.id.update_buttonDeleteForm);
        update = findViewById(R.id.update_buttonUpdateForm);

        Intent getData = getIntent();
        id_siswa = getData.getIntExtra("id", 0);

        bulan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarValueInt = progress;
                seekbarValueString = Integer.toString(seekbarValueInt);
                update_angka_bulan.setText("Bulan : " + seekbarValueString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if ( id_siswa > 0) {
            final DatabaseSiswa dh = new DatabaseSiswa(getApplicationContext());
            Cursor cursor = dh.getDetailSiswa(id_siswa);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    nama.setText((cursor.getString(cursor.getColumnIndexOrThrow("nama_siswa"))));
                    jumlah_bayar.setText((cursor.getString(cursor.getColumnIndexOrThrow("jumlah_pembayaran"))));
                    jeniskelamin_siswa = cursor.getString(cursor.getColumnIndexOrThrow("jeniskelamin_siswa"));
                    if (jeniskelamin_siswa.toString().equals("Laki-laki")) {
                        radiolaki.setChecked(true);
                    } else if (jeniskelamin_siswa.toString().equals("Perempuan")) {
                        radioperempuan.setChecked(true);
                    }
                    metode_bayar.setSelection(((ArrayAdapter<String>)metode_bayar.getAdapter()).getPosition((cursor.getString(cursor.getColumnIndexOrThrow("metode_pembayaran")))));
                    hobi_siswa = cursor.getString(cursor.getColumnIndexOrThrow("hobi_siswa"));
                    if (hobi_siswa.toString().contains("Menyanyi")) {
                        c_menyanyi.setChecked(true);
                    } else if (hobi_siswa.toString().contains("Olahraga")) {
                        c_olahraga.setChecked(true);
                    } else if (hobi_siswa.toString().contains("Menggambar")) {
                        c_gambar.setChecked(true);
                    } else if (hobi_siswa.toString().contains("Bermain Game")) {
                        c_game.setChecked(true);
                    } else if (hobi_siswa.toString().contains("Travelling")) {
                        c_travel.setChecked(true);
                    } else if (hobi_siswa.toString().contains("Membaca")) {
                        c_baca.setChecked(true);
                    }
                    bulan.setProgress(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("bulan"))));
                    cursor.moveToNext();
                }
                dh.close();
            }
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogAlertBuilder = new AlertDialog.Builder(UpdateActivity.this);
                dialogAlertBuilder.setTitle("Konfirmasi");
                dialogAlertBuilder
                        .setMessage("Hapus data?")
                        .setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseSiswa databaseSiswa = new DatabaseSiswa(getApplicationContext());

                                boolean hapusMahasiswa = databaseSiswa.deletesiswa(id_siswa);

                                if (hapusMahasiswa) {
                                    Toast.makeText(UpdateActivity.this, "Hapus Data Berhasil", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UpdateActivity.this, "Hapus Data Gagal", Toast.LENGTH_SHORT).show();
                                }
                                databaseSiswa.close();
                                Intent intent = new Intent(getApplicationContext(),RecyclerActivity.class);
                                startActivity(intent);

                                Toast.makeText(getApplicationContext(), "Data telah dihapus!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog dialog = dialogAlertBuilder.create();
                dialog.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Berhasil di Update";
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

                Integer bulanTerpilih = bulan.getProgress();
                strBulan = bulanTerpilih.toString();

                if (nama_siswa.isEmpty()) {
                    nama.requestFocus();
                    nama.setError("Silahkan isi nama dahulu!");
                } else if (jumlah_pembayaran.isEmpty()) {
                    jumlah_bayar.requestFocus();
                    jumlah_bayar.setError("Silahkan isi Jumlah Pembayaran dahulu!");
                } else if (jeniskelamin_siswa.isEmpty()) {
                    grup_kelamin.requestFocus();
                } else if (strMetode.isEmpty()) {
                    metode_bayar.requestFocus();
                } else if (hobi_siswa.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pilih minimal 1 hobi", Toast.LENGTH_LONG).show();
                } else if (strBulan.isEmpty()) {
                    bulan.requestFocus();
                } else {
                    updateDataMahasiswa();
                }
            }
        });
    }

    private void updateDataMahasiswa() {

        StringBuilder rekapData = new StringBuilder();
        rekapData.append("Nama : " + nama_siswa + "\n\n");
        rekapData.append("Jumlah : " + jumlah_pembayaran + "\n\n");
        rekapData.append("Kelamin : " + jeniskelamin_siswa + "\n\n");
        rekapData.append("Metode_pembayaran : " + strMetode + "\n\n");
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

                        boolean update_siswa = databaseSiswa.updateSiswa(siswaHandler, id_siswa);
                        if (update_siswa) {
                            Toast.makeText(UpdateActivity.this, "Update Mahasiswa Berhasil", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdateActivity.this, "Update Mahasiswa Gagal", Toast.LENGTH_SHORT).show();
                        }
                        databaseSiswa.close();

                        Intent intent = new Intent(getApplicationContext(),RecyclerActivity.class);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Data telah diupdate!", Toast.LENGTH_SHORT).show();
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
}