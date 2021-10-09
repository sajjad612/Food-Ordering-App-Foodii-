package com.example.fooodii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.fooodii.Adapters.OrderAdapter;
import com.example.fooodii.Models.OrdersModel;
import com.example.fooodii.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("My Orders");

//        ArrayList<OrdersModel> list= new ArrayList<>();

        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrder();

//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));
//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));
//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));
//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));
//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));
//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));
//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));
//        list.add(new OrdersModel(R.drawable.bur,"Cheese Burger","4","1014520263"));

        OrderAdapter adapter = new OrderAdapter(list,this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }
}