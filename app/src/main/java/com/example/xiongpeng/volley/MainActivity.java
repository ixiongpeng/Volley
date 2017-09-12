package com.example.xiongpeng.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Map<String, String> appendHeader = new HashMap<String, String>();
        appendHeader.put("uesername", "admin");
        appendHeader.put("password","123456");
        String url = "http://172.27.35.1:8080/webTest/TestServlet";

        JsonRequestWithAuth<User> userRequest = new JsonRequestWithAuth<User>(url,User.class, new Response.Listener<User>(){

            @Override
            public void onResponse(User response) {
                Log.d("TAG", response.toString());
            }
        },appendHeader, null);
        requestQueue.add(userRequest);
    }
}
