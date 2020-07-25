package com.antria.freshavocado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.antria.freshavocado.database.AvocadoViewModel;
import com.antria.freshavocado.ui.AvocadoListAdapter;

public class MainActivity extends AppCompatActivity {

    private AvocadoViewModel mAvocadoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AvocadoListAdapter adapter = new AvocadoListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAvocadoViewModel = new ViewModelProvider(this).get(AvocadoViewModel.class);
        mAvocadoViewModel.getAllAvocados().observe(this, avocados -> {
            // Update the cached copy of the words in the adapter.
            adapter.setAvocados(avocados);
        });

    }
}
