package com.example.exercise2_kelasb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.exercise2_kelasb.adapter.TemanAdapter;
import com.example.exercise2_kelasb.database.DBController;
import com.example.exercise2_kelasb.database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements TemanAdapter.OnItemSelectedListener , View.OnClickListener {
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id, nm, tlp;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        BacaData();
        adapter = new TemanAdapter(temanArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(intent);
            }
        });
    }

    public void BacaData(){
        ArrayList<HashMap<String, String>> daftarTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();

        //memindah dari hasil query kedalam teman
        for (int i=0;i<daftarTeman.size();i++) {
            Teman teman = new Teman();
            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setNama(daftarTeman.get(i).get("nama").toString());
            teman.setTelpon(daftarTeman.get(i).get("telpon").toString());

            //pindah dari teman kedalam arraylist teman di adapter
            temanArrayList.add(teman);
        }
    }

    public void getData(String id) {
        Teman b = controller.getByid(id);
        Intent i = new Intent(this, LihatDataTeman.class);
        Bundle bun = new Bundle();
        bun.putString("id", b.getId());
        bun.putString("nama", b.getNama());
        bun.putString("telepon", b.getTelpon());
        i.putExtras(bun);
        controller.close();
        startActivity(i);
    }

    @Override
    public void onSelected(Teman object) {
        Teman b = controller.getByid(id);
        Intent i = new Intent(this, LihatDataTeman.class);
        Bundle bun = new Bundle();
        bun.putString("id", b.getId());
        bun.putString("nama", b.getNama());
        bun.putString("telepon", b.getTelpon());
        i.putExtras(bun);
        controller.close();
        startActivity(i);
    }

    @Override
    public void onMenu( Teman position, MenuItem item) {
       switch (item.getItemId()){
           case R.id.mnview:
               Intent in = new Intent(MainActivity.this, LihatDataTeman.class);
               startActivity(in);
               break;
           case R.id.mnedit:
               break;
       }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LihatDataTeman.class);
        startActivity(intent);
    }
}