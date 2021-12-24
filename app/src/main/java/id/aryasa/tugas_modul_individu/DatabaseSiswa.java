package id.aryasa.tugas_modul_individu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DatabaseSiswa extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "Kas_Siswa.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DatabaseSiswa(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        //context.deleteDatabase(DATABASE_NAME);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE siswa(id INTEGER PRIMARY KEY AUTOINCREMENT, nama_siswa TEXT, jumlah_pembayaran TEXT, jeniskelamin_siswa TEXT, metode_pembayaran TEXT, hobi_siswa TEXT, bulan TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS siswa");
    }

    public boolean addSiswa(SiswaHandler siswaHandler){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nama_siswa", siswaHandler.getNama());
        cv.put("jumlah_pembayaran", siswaHandler.getJumlah_pembayaran());
        cv.put("jeniskelamin_siswa", siswaHandler.getJenisKelamin());
        cv.put("metode_pembayaran", siswaHandler.getMetode_bayar());
        cv.put("hobi_siswa", siswaHandler.getHobi());
        cv.put("bulan", siswaHandler.getBulan());
        return db.insert("siswa",null, cv) > 0;
    }
    public Cursor getSiswa() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from siswa", null);
    }

    public Cursor getDetailSiswa(int id_siswa) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from siswa where id = " + id_siswa, null);
    }

    public boolean updateSiswa(SiswaHandler siswaHandler, int id_siswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nama_siswa", siswaHandler.getNama());
        cv.put("jumlah_pembayaran", siswaHandler.getJumlah_pembayaran());
        cv.put("jeniskelamin_siswa", siswaHandler.getJenisKelamin());
        cv.put("metode_pembayaran", siswaHandler.getMetode_bayar());
        cv.put("hobi_siswa", siswaHandler.getHobi());
        cv.put("bulan", siswaHandler.getBulan());
        return db.update("siswa", cv, "id" + "=" + id_siswa, null) > 0;
    }

    public boolean deletesiswa(int id_siswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("siswa", "id" + "=" + id_siswa, null) > 0;
    }

    public DatabaseSiswa(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DatabaseSiswa(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }
}
