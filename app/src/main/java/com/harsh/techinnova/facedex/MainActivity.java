package com.harsh.techinnova.facedex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    private static final int CAMERA_REQUEST1 =1888;
    private ImageView imageView;
    static Bitmap photo;
    static Bitmap photo1;
    String string2="Gallery";


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
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView1);
        Button photoButton = (Button) findViewById(R.id.button1);
        Button checkButton = (Button) findViewById(R.id.button2);
        Button gallery = (Button) findViewById(R.id.gallery);
        Button friends = (Button) findViewById(R.id.friends);

        //set listener
        photoButton.setOnClickListener(this);
        checkButton.setOnClickListener(this);
        gallery.setOnClickListener(this);
        friends.setOnClickListener(this);
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

            case R.id.gallery:
                try {
                    listGalleries();
                }
                catch (JSONException e){}
                catch (Exception e){}




            case R.id.friends:
                try {
                    listSubjectsForGallery(string2);
                }
                catch (JSONException e){}
                catch (Exception e){}



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

    public void listGalleries() throws JSONException, UnsupportedEncodingException {

        AsyncHttpClient client = new AsyncHttpClient();

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);


                String responseCode=String.valueOf(statusCode);
                String respnse=String.valueOf(response);
                //String json = EntityUtils.toString(response.getEntity());

                Toast.makeText(getBaseContext(),responseString, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),responseCode, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),respnse, Toast.LENGTH_LONG).show();


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
        StringEntity entity = new StringEntity(jsonParams.toString());
        client.addHeader("app_id","f76938b4");
        client.addHeader("app_key", "6c6ee7506b26175f3b4859aacbb84409");
        client.post(my_context, "http://api.kairos.com/gallery/list_all", entity, "application/json", responseHandler);
    }

    public void listSubjectsForGallery(String galleryId) throws JSONException, UnsupportedEncodingException {

        AsyncHttpClient client = new AsyncHttpClient();

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);


                String responseCode=String.valueOf(statusCode);
                String respnse=String.valueOf(response);
                //String json = EntityUtils.toString(response.getEntity());

                Toast.makeText(getBaseContext(),responseString, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),responseCode, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),respnse, Toast.LENGTH_LONG).show();


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
        jsonParams.put("gallery_name", galleryId);
        StringEntity entity = new StringEntity(jsonParams.toString());
        client.addHeader("app_id","f76938b4");
        client.addHeader("app_key", "6c6ee7506b26175f3b4859aacbb84409");
        client.post(my_context, "http://api.kairos.com/gallery/view", entity, "application/json", responseHandler);

    }






    }

