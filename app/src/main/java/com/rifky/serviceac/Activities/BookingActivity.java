package com.rifky.serviceac.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rifky.serviceac.Database.DatabaseHelper;
import com.rifky.serviceac.R;

public class BookingActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    EditText namanya, notelpnya, alamatnya, sTanggal, keluhannya;
    String edit;
    Button req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // memanggil button booking
        req = findViewById(R.id.book);
        // memanggil variabel input tex form
        namanya = findViewById(R.id.nama);
        alamatnya = findViewById(R.id.alamat);
        notelpnya = findViewById(R.id.no_telp);
        sTanggal = findViewById(R.id.waktu);
        keluhannya = findViewById(R.id.keluhan);
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();


        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //meringkas variabel dengan variabel array edit
                edit = namanya.getText().toString();
                edit = alamatnya.getText().toString();
                edit = notelpnya.getText().toString();
                edit = sTanggal.getText().toString();
                edit = keluhannya.getText().toString();
                //penggunaan array variabel edit untuk mendeklarasikan jika form kosong
                if (edit.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Data Harus Lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                                        db.execSQL("insert into tb_booking (  nama,  alamat, no_telp, tanggal,keluhan) values('" +
                                                namanya.getText().toString() + "','" +
                                                alamatnya.getText().toString() + "','" +
                                                notelpnya.getText().toString() + "','" +
                                                sTanggal.getText().toString() + "','" +
                                                keluhannya.getText().toString() + "')");
                                        Toast.makeText(getApplicationContext(), "Booking Berhasil", Toast.LENGTH_LONG).show();
                                        Intent konfirmasi_pembayaran;
                                        konfirmasi_pembayaran = new Intent(BookingActivity.this, KonfirmPembayaran.class);
                                        startActivity(konfirmasi_pembayaran);
                                        finish();
                                    }

            }
        });
        setupToolbar();
    }
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbKrl);
        toolbar.setTitle("Form Booking");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}