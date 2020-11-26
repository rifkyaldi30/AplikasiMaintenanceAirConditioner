package com.rifky.serviceac.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.rifky.serviceac.R;

public class KonfirmPembayaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirm_pembayaran);
        String contact = "+62 89522301498";

        String url = "https://api.whatsapp.com/send?phone=" + contact + "&text=Dear Admin,\n" +
                " Saya Sudah Booking yaa, saya tunggu konfirmasinya. Thanks    \n";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url)); startActivity(i);
    }
}
