package uas.Kelompok7.inventory.model;

public class inventorymodel {
    public String id_barang, nama_barang, stock;

    public inventorymodel(String id_barang, String nama_barang, String stock) {
        this.id_barang = id_barang;
        this.nama_barang = nama_barang;
        this.stock = stock;
    }

    public String getId_barang() {
        return id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getStock() {
        return stock;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
