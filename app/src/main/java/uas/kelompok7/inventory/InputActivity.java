package uas.kelompok7.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputActivity extends AppCompatActivity {
//    Inialisais view
    EditText edNama, edjumlah;
    Button btnSimpan;
//    inisialsasi fungsi - fungsi database FireBase
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

//    Fungsi onCreate dipanggil saat pembuatan Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
//    inialisasi id view pada layout yang digunakan
        edNama = findViewById(R.id.ed_nama);
        edjumlah = findViewById(R.id.ed_jumlah);
        btnSimpan = findViewById(R.id.btn_simpan);
//        mengaktifkan tombol simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mengabil data dari kolom edit text
                String inputNama = edNama.getText().toString();
                String inputBarang = edjumlah.getText().toString();
                if (inputNama.isEmpty()){
//                    Menampilakan Error ketika nama barang kosong
                    edNama.setError("Masukkan Nama Barang!!");
                }else if (inputBarang.isEmpty()){
//                    Menampilakan Error ketika jumlah barang kosong
                    edjumlah.setError("Masukkan Jumlah Barang!!");
                }else {
//                    Menambahakan data ke database
                    database.child("Barang").push().setValue(new ModelBarang(inputNama, inputBarang)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
//                                Menampilkan Pesan Toast berisi pesan berhasil
//                                saat data berhasil disimpan
                            Toast.makeText(InputActivity.this, "Barang berhasil disimpan", Toast.LENGTH_SHORT).show();
//                            berpidah dari activity InputActivity ke activity MainActivity
                            startActivity(new Intent(InputActivity.this, MainActivity.class));
//                            menghapus activity
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                                Menampilkan Pesan Toast berisi pesan gagal
//                                saat data gagal disimpan
                            Toast.makeText(InputActivity.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}