package id.aryasa.tugas_modul_individu;

public class SiswaHandler {

    private Integer id;
    private String Nama;
    private String Jumlah_pembayaran;
    private String JenisKelamin;
    private String Metode_bayar;
    private String Hobi;
    private String Bulan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJumlah_pembayaran() {
        return Jumlah_pembayaran;
    }

    public void setJumlah_pembayaran(String jumlah_pembayaran) {
        Jumlah_pembayaran = jumlah_pembayaran;
    }

    public String getJenisKelamin() {
        return JenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        JenisKelamin = jenisKelamin;
    }

    public String getMetode_bayar() {
        return Metode_bayar;
    }

    public void setMetode_bayar(String metode_bayar) {
        Metode_bayar = metode_bayar;
    }

    public String getHobi() {
        return Hobi;
    }

    public void setHobi(String hobi) {
        Hobi = hobi;
    }

    public String getBulan() {
        return Bulan;
    }

    public void setBulan(String bulan) {
        Bulan = bulan;
    }
}
