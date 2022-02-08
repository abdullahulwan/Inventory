package uas.kelompok7.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//    Inialisais view
    Button btnView, btnInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//    inialisasi id view pada layout yang digunakan
        btnView = findViewById(R.id.btnview);
        btnInput = findViewById(R.id.btninput);
//        mengaktifkan tombol btnview pada MainActivity layout
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               berpidah dari activity MainActivity ke activity ShowDataActivity
                startActivity(new Intent(MainActivity.this, ShowDataActivity.class));
            }
        });

//        mengaktifkan tombol btninput pada MainActivity layout
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               berpidah dari activity MainActivity ke activity InputActivity
                startActivity(new Intent(MainActivity.this, InputActivity.class));
            }
        });
    }
}