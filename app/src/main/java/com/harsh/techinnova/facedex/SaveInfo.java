package com.harsh.techinnova.facedex;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;


/**
 * Created by samsung on 23-Jan-17.
 */
public class SaveInfo extends Activity implements View.OnClickListener {

    private ImageView saveimage;
    Bitmap bitmap;
    EditText bio;
    String string;
    protected String my_app_id;
    protected String my_api_key;
    protected String my_host;
    protected Context my_context;


    public void setAuthentication(Context ctx, String app_id, String api_key) {

        my_context = ctx;
        my_app_id = app_id;
        my_api_key = api_key;
        my_host = "http://api.kairos.com/";

    }



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
         string=bio.toString();







    }






    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savebutton:
                try {
                    enroll(bitmap, string, null, null, null, null);

                } catch (JSONException e){}
                catch (Exception e){}
        }
    }




    public void enroll(Bitmap image,
                       String subjectId,
                       String galleryId,
                       String selector,
                       String multipleFaces,
                       String minHeadScale
                       )  throws JSONException, UnsupportedEncodingException {

        AsyncHttpClient client = new AsyncHttpClient();

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                Toast.makeText(getBaseContext(), responseString, Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {

            }




            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }

        };

        JSONObject jsonParams = new JSONObject();
        jsonParams.put("image", base64FromBitmap(image));
        jsonParams.put("subject_id", subjectId);
        jsonParams.put("gallery_name", galleryId);

        if(selector != null) {
            jsonParams.put("selector", selector);
        }

        if(minHeadScale != null) {
            jsonParams.put("minHeadScale", minHeadScale);
        }

        if(multipleFaces != null) {
            jsonParams.put("multiple_faces", multipleFaces);
        }

        StringEntity entity = new StringEntity(jsonParams.toString());
        client.addHeader("app_id","f76938b4");
        client.addHeader("app_key","6c6ee7506b26175f3b4859aacbb84409");
        client.post(my_context, "http://api.kairos.com/enroll", entity, "application/json", responseHandler);

    }


    protected String base64FromBitmap(Bitmap image){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encoded;
    }



}
