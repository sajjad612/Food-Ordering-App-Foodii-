package com.example.fooodii;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fooodii.databinding.ActivityDetailsBinding;
import com.example.fooodii.databinding.ActivityOrderBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailsImage.setImageResource(image);
            binding.pricelb1.setText(String.format("%d", price));
            binding.foodname.setText(name);
            binding.detailsDescription.setText(description);


            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,Integer.parseInt(binding.quantity.getText().toString()), image, name, description
                            );
                    if (isInserted)
                        Toast.makeText(DetailsActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            });
        } else {

            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(5);
            binding.detailsImage.setImageResource(cursor.getInt(5));
            binding.pricelb1.setText(String.format("%d",cursor.getInt(3)));
            binding.foodname.setText(cursor.getString(6));
            binding.detailsDescription.setText(cursor.getString(7));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));

            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.pricelb1.getText().toString()),
                            1,image,
                            binding.foodname.getText().toString(),
                            "1",id);

                    if (isUpdated){
                        Toast.makeText(DetailsActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DetailsActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
}