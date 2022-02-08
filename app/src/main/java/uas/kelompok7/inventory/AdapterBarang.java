package uas.kelompok7.inventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.MyViewHolder> {
//    inisialisasi variabel daftar data yang ditampilkan
    private List<ModelBarang> barangList;
//    inisialisasi class activity
    private Activity activity;
//    inisialsasi fungsi - fungsi database FireBase
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

// construktor untuk memasukkan paremeter data dan Ativity yang akan di tampilkan
    public AdapterBarang(List<ModelBarang> barangList, Activity activity) {
        this.barangList = barangList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Memasukkan layout_item kedalam RecycleView
//        untuk digunakan sebagai penampil data
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBarang.MyViewHolder holder, int position) {
//        mengubah format data agar mudah digunakan
        final ModelBarang data = barangList.get(position);
//        menampilkan data pada RecycleView
        holder.tvNama.setText(data.getNama());
        holder.tvJumlah.setText("jumlah : " + data.getJumlah());
//        mengaktifkan tombol hapus pada data yang ditampilkan
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Membuat pesan peringatan ketikan tombol telah ditekan
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
//                        melakukan penghapusan data
                        database.child("Barang").child(data.getKey()).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
//                                Menampilkan Pesan Toast berisi pesan berhasil
//                                saat data berhasil dihapus
                                Toast.makeText(
                                        activity,
                                        "Data Berhasil dihapus!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                Menampilkan Pesan Toast berisi pesan gagal
//                                saat data gagal dihapus
                                Toast.makeText(
                                        activity,
                                        "Gagal menghapus Data!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Menutup dialog Inteface ketika membatalkan penghapusan
                        dialog.dismiss();
                    }
//                    pesan Untuk menampikan pertanyaan untuk penghapusan data
                }).setMessage("Data akan Dihapus Permanent!\nApakah Anda Yakin Akan Menghapus data?");
//                Menampilkan pesan peringatan
                builder.show();
            }
        });

//        aktifkan Edit Data dengan cara menkantahan tahan pada data yang akan diubah
        holder.cv_hasil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Membuat dialog form untuk mengedit Data
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(
                        data.getNama(),
                        data.getJumlah(),
                        data.getKey(),
                        "ubah"
                );
//                menampilkan dialog form
                dialog.show(manager, "form");
                return true;
            }
        });
    }

//    jumlah layout dimunculkan sesuia dengan data yang ada
    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        inialisasi View yang ada di layout_item
        TextView tvNama, tvJumlah;
        ImageView btnHapus;
        CardView cv_hasil;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJumlah = itemView.findViewById(R.id.tv_jml);
            btnHapus = itemView.findViewById(R.id.iv_hapus);
            cv_hasil = itemView.findViewById(R.id.card_hasil);
        }
    }
}
