package com.example.exercise2_kelasb;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.exercise2_kelasb.database.DBController;
import com.example.exercise2_kelasb.database.Teman;

import androidx.appcompat.app.AppCompatActivity;


public class EditDataTeman extends AppCompatActivity implements View.OnClickListener {

    private String nama;
    private String telpon;

    private EditText edNama;
    private EditText edTelpon;

    private DBController teman;
    private Button btnSave;

    public void onClick(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_teman);

        edNama = (EditText) findViewById(R.id.edNama);
        edTelpon = (EditText)findViewById(R.id.edTelpon);

        teman = new DBController(this);
        teman.getReadableDatabase();

        Bundle bun = this.getIntent().getExtras();

        nama = bun.getString("harga");
        telpon = bun.getString("telpon");

        edNama.setText(nama);
        edTelpon.setText(telpon);
    }


    @Override
    public void onClick(View v) {
        btnSave = (Button) findViewById(R.id.fabSimpan);
        btnSave.setOnClickListener(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataTeman.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
