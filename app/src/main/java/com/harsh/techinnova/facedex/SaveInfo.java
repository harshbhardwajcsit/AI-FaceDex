package com.harsh.techinnova.facedex;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by samsung on 23-Jan-17.
 */
public class SaveInfo extends Activity implements View.OnClickListener{

    private ImageView saveimage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saveinfo);
        saveimage = (ImageView)findViewById(R.id.saveimage);

      //  Button photoButton = (Button)findViewById(R.id.button1);
        //photoButton.setOnClickListener(this);
        //Intent intent = getIntent();
        //Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        Bitmap bitmap = getIntent().getParcelableExtra("BitmapImage");
        saveimage.setImageBitmap(bitmap);
    }
    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:

        }}


}
