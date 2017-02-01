package com.harsh.techinnova.facedex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_REQUEST1 =1888;
    private ImageView imageView;
    static Bitmap photo;
    static Bitmap photo1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView1);
        Button photoButton = (Button) findViewById(R.id.button1);
        Button checkButton = (Button) findViewById(R.id.button2);
        //set listener
        photoButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            case R.id.button2:
                Intent intent1=new Intent(MainActivity.this,Check.class);
                startActivity(intent1);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            //photo1 = (Bitmap) data.getExtras().get("data");
            Intent intent = new Intent(MainActivity.this, SaveInfo.class);
            intent.putExtra("BitmapImage", photo);

            startActivity(intent);

        }
    }


    }

