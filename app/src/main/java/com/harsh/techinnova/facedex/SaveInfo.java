package com.harsh.techinnova.facedex;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by samsung on 23-Jan-17.
 */
public class SaveInfo extends Activity implements View.OnClickListener {

    private ImageView saveimage;
    Bitmap bitmap;
    EditText bio;
    String appId = "4e1fa2ad";
    String appKey = "ebe99430089461bd18189b5613ac82cc";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saveinfo);
        saveimage = (ImageView) findViewById(R.id.saveimage);
        bio = (EditText) findViewById(R.id.bio);
        Button savebutton = (Button) findViewById(R.id.savebutton);
        savebutton.setOnClickListener(this);
        //Intent intent = getIntent();
        //Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        bitmap = getIntent().getParcelableExtra("BitmapImage");
        saveimage.setImageBitmap(bitmap);







    }



    private String getAuthHeader(String ID, String pass){ String encoded = Base64.encodeToString((ID + ":" + pass).getBytes(), Base64.NO_WRAP); String returnThis = "Basic " + encoded; return returnThis; }



    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savebutton:

                try {
                    /************** For getting response from HTTP URL start ***************/
                    URL object = new URL("https://api.kairos.com/enroll");
                    JSONObject json=new JSONObject();
                    json.put("image",bitmap);
                    json.put("subject_id",bio);

                    HttpURLConnection connection = (HttpURLConnection) object.openConnection();
                    // int timeOut = connection.getReadTimeout();
                    connection.setReadTimeout(60 * 1000);
                    connection.setConnectTimeout(60 * 1000);
                    String authHeader = getAuthHeader(appId,appKey);
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    StringEntity params = new StringEntity(json.toString());
                    connection.setRequestProperty("Authorization", authHeader);
                    connection.addRequestProperty("Content-Type", "application/json");
                    OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
                    streamWriter.write(json.toString());
                    streamWriter.flush();
                    StringBuilder stringBuilder = new StringBuilder();

                    Toast.makeText(getBaseContext(), "You are Done", Toast.LENGTH_LONG).show();
                    connection.connect();
                    Toast.makeText(getBaseContext(), "You are not Done", Toast.LENGTH_LONG).show();
                    int responseCode = connection.getResponseCode();
                    String responseMsg = connection.getResponseMessage();

                    if (responseCode == 200) {
                        Toast.makeText(getBaseContext(), "You are Done", Toast.LENGTH_LONG).show();

                    }
                    else{Toast.makeText(getBaseContext(), "You are not Done", Toast.LENGTH_LONG).show();}
                } catch (Exception e) {
                    e.printStackTrace();

                }
        }
    }






}
