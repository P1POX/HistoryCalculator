package com.example.information;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaTextosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listctivity);

        ListView listView = findViewById(R.id.listTextos);
        Button btnVolver = findViewById(R.id.btnVolver);

        ArrayList<String> listaHistorial = getIntent().getStringArrayListExtra("listaHistorial");

        if (listaHistorial != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaHistorial);
            listView.setAdapter(adapter);
        }

        btnVolver.setOnClickListener(v -> finish());
    }
}
