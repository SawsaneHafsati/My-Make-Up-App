package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Récupération des données de l'intent et insertion dans les labels correspondants
        Intent intent = getIntent();
        TextView name = findViewById(R.id.detailName);
        TextView price = findViewById(R.id.detailPrice);
        TextView brand = findViewById(R.id.detailBrand);
        TextView color = findViewById(R.id.detailColor);
        ImageView image = findViewById(R.id.detailImage);

        name.setText(intent.getStringExtra("name"));
        price.setText(intent.getStringExtra("price"));
        brand.setText(intent.getStringExtra("brand"));
        color.setText(intent.getStringExtra("color"));

        Picasso.get().load(intent.getStringExtra("imgURL")).into(image);
    }
}
