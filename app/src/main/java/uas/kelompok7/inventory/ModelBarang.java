package uas.kelompok7.inventory;

public class ModelBarang {
//    inialaisasis variabel data
    private String nama;
    private String jumlah;
    private String key;

//    construktor tanpa parameter
    public ModelBarang(){
    }

//    construktor dengan parameter nama dan jumlah barang
    public ModelBarang(String nama, String jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
    }

//    Getter dan setter untuk variable data
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
