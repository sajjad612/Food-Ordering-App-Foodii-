package com.example.fooodii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fooodii.Adapters.MainAdapter;
import com.example.fooodii.Models.MainModel;
import com.example.fooodii.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger,"Ham Burger","5","Chicken Burger with Extra Cheese"));
        list.add(new MainModel(R.drawable.bur,"Burger","1","Veg Crisp Burger with Extra Cheese"));
        list.add(new MainModel(R.drawable.fries,"Fries","2","Crispy Fries with Mayo"));
        list.add(new MainModel(R.drawable.kfc,"KFC","3","Crisp Chicken Dumstick"));
        list.add(new MainModel(R.drawable.noodle,"Noodle","5","Chatpata Chowmein Noodle "));
        list.add(new MainModel(R.drawable.nuggets,"Nuggets","1","Chicken Tikka 12pc"));
        list.add(new MainModel(R.drawable.pasta,"Pasta","2","White Sauce pasta with full cheese overloaded"));
        list.add(new MainModel(R.drawable.pizza,"Pizza","3","Peri Peri Pizza with with a Pepsi"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}