package com.example.exercise2_kelasb;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LihatDataTeman extends AppCompatActivity {
    TextView tvnama, tvnomor;
    private static final String TAG = "LihatDataTeman";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data_teman);
        Log.d(TAG, "onCreate: called");


        tvnama = (TextView) findViewById(R.id.tvNamaKontak);
        tvnomor = (TextView) findViewById(R.id.tvNomorTelepon);

        tvnama.setText(getIntent().getExtras().getString("nama"));
        tvnomor.setText(getIntent().getExtras().getString("telepon"));
    }
}
