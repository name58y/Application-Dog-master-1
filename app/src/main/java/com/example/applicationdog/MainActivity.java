package com.example.applicationdog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    Button picture;
    TextView result, detail;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        picture = findViewById(R.id.btnCamera);
        View btnDog = findViewById(R.id.btnDog);
        result = findViewById(R.id.result);
        detail = findViewById(R.id.detail);



        if (result != null && detail != null) {
            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                    }
                }
            });
        }
        btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListDogActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);

            DogBreedUtil.processAndDisplay(this, image, result, detail);

            String breed = result.getText().toString();
            String detailText = detail.getText().toString();

            // ส่งภาพและข้อมูลไปยัง ResultActivity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("breed", breed);
            intent.putExtra("detail", detailText);
            intent.putExtra("image", image);
            startActivity(intent);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
