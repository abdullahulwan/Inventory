package uas.kelompok7.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {
//    Melakukan instansi terhadap AdapterBarang
    AdapterBarang adapterBarang;
//    inisialsasi fungsi - fungsi database FireBase
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//    Array list untuk memasukan daftar data dari database
    ArrayList<ModelBarang> listBarang;
//    inilaisasi fungsi RecyclerView
    RecyclerView rvData;

//    Fungsi onCreate dipanggil saat pembuatan Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
//       inialisasi id Recycle View pada layout yang digunakan
        rvData = findViewById(R.id.rv_data);
        RecyclerView.LayoutManager barangList = new LinearLayoutManager(this);
        rvData.setLayoutManager(barangList);
        rvData.setItemAnimator(new DefaultItemAnimator());

//        menampilakan data
        showData();
    }

    private void showData() {
//        memanggil data dari
        database.child("Barang").addValueEventListener(new ValueEventListener() {
            @Override
//            Menampilkan Data ke Recycle view
//            saat data berhasil didapatkan dari Dataase
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listBarang = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    ModelBarang brg = item.getValue(ModelBarang.class);
                    assert brg != null;
                    brg.setKey(item.getKey());
                    listBarang.add(brg);
                }
                adapterBarang = new AdapterBarang(listBarang, ShowDataActivity.this);
                rvData.setAdapter(adapterBarang);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                                Menampilkan Pesan Toast berisi pesan gagal
//                                saat data gagal ditampilkan
                Toast.makeText(ShowDataActivity.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}