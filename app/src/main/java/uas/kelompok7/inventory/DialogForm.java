package uas.kelompok7.inventory;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogForm extends DialogFragment {
//    inisialisasi data
    String nama, jumlah, key, pilih;
//    inisialsasi fungsi - fungsi database FireBase
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    //    Construktor untuk memanggil data yang akan ditampilkan sebelum di edit
    public DialogForm(String nama, String jumlah, String key, String pilih) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.key = key;
        this.pilih = pilih;
    }

//    inialisasi view yang digunakan
    TextView tnama, tjumlah;
    Button btnSimpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        memilih layout yang akan digunakan pada tampilan dialog
        final View view = inflater.inflate(R.layout.layout_edit, container, false);
//    inialisasi id view pada layout yang digunakan
        tnama = view.findViewById(R.id.edNama);
        tjumlah = view.findViewById(R.id.edJumlah);
        btnSimpan = view.findViewById(R.id.btnSimpan);
//        Menampilkan data sebelum diubah
        tnama.setText(this.nama);
        tjumlah.setText(this.jumlah);
//        mengaktifkan tombol simpan
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mengabil data dari kolom edit text
                String getNama = tnama.getText().toString();
                String getJumlah = tjumlah.getText().toString();
                if (pilih.equals("ubah")){
//                    mengubah Data
                    database.child("Barang").child(key).setValue(new ModelBarang(getNama, getJumlah))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
//                                Menampilkan Pesan Toast berisi pesan berhasil
//                                saat data berhasil diupdate
                            Toast.makeText(view.getContext(), "Data Berhasil diupdate", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                                Menampilkan Pesan Toast berisi pesan gagal
//                                saat data gagal diupdate
                            Toast.makeText(view.getContext(), "Gagal mengupdate data!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog !=null){
//            menentukan ukuran dialog
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
