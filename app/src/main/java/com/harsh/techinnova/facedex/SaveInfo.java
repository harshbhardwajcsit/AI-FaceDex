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

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.DataOutputStream;
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

                Toast.makeText(getBaseContext(), "1", Toast.LENGTH_LONG).show();
                try {
                    /************** For getting response from HTTP URL start ***************/
                    URL object = new URL("https://api.kairos.com/enroll");
                    JSONObject json=new JSONObject();
                    json.put("image",bitmap);
                    json.put("subject_id",bio);
                    Toast.makeText(getBaseContext(), "2", Toast.LENGTH_LONG).show();
                    //open connection
                    HttpURLConnection connection = (HttpURLConnection) object.openConnection();

                    //set time fields
                    connection.setReadTimeout(60 * 1000);
                    connection.setConnectTimeout(60 * 1000);
                    //String authHeader = getAuthHeader(appId,appKey);

                    //set request type
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    StringEntity params = new StringEntity(json.toString());
                    //set header
                    Toast.makeText(getBaseContext(), "3", Toast.LENGTH_LONG).show();


                    //set header
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("app_id",appId);
                    connection.setRequestProperty("app_key",appKey);



                    Toast.makeText(getBaseContext(), "4", Toast.LENGTH_LONG).show();

                    //`5.1 Use Jackson object mapper to convert Contnet object into JSON
                   /*
                    System.out.println("status: " + response.getStatus());
                    System.out.println("headers: " + response.getHeaders());
                    System.out.println("body:" + response.readEntity(String.class));
*/
                    // 5.2 Get connection output stream
                    DataOutputStream streamWriter = new DataOutputStream(connection.getOutputStream());

                   // streamWriter.write(json);
                    Toast.makeText(getBaseContext(), "You are Done", Toast.LENGTH_LONG).show();

                    streamWriter.flush();

                    // 5.5 close
                    streamWriter.close();


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
