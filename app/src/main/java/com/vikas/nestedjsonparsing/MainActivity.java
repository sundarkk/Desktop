package com.vikas.nestedjsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
TextView tv,tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        String url="http://searchkero.com/a/data.json";
        StringRequest sr=new StringRequest(1, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                databreak(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(sr);

        String url1="http://searchkero.com/a/math.json";
        StringRequest sr1=new StringRequest(1, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {

                mathbreak(response1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue1=Volley.newRequestQueue(this);
        requestQueue1.add(sr1);

        String url2="http://searchkero.com/a/file.json";
        StringRequest sr2=new StringRequest(1, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {

                filebreak(response2);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue2=Volley.newRequestQueue(this);
        requestQueue2.add(sr2);


    }

    private void filebreak(String response2) {

        try {
            JSONObject jo=new JSONObject(response2);
            JSONObject jo1=jo.getJSONObject("glossary");
            JSONObject jo2=jo1.getJSONObject("GlossDiv");
            JSONObject jo3=jo2.getJSONObject("GlossList");
            JSONObject jo4=jo3.getJSONObject("GlossEntry");
            JSONObject jo5=jo4.getJSONObject("GlossDef");
            JSONArray ja=jo5.getJSONArray("GlossSeeAlso");
            String gml=ja.getString(0);
            tv2.setText(gml.toString());

            String data=jo4.getString("GlossSee");
            tv2.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void mathbreak(String response1) {

        try {
            JSONObject jo=new JSONObject(response1);
            JSONObject jo1=jo.getJSONObject("quiz");
            JSONObject jo2=jo1.getJSONObject("maths");
            JSONObject jo3=jo2.getJSONObject("q2");
            JSONArray ja=jo3.getJSONArray("options");

            for(int i=0;i<4;i++)
            {
                String [] option=new String[ja.length()];
                option[i]=ja.getString(i);
                tv1.append(option[i].toString());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void databreak(String response) {


        try {
           JSONObject jo = new JSONObject(response);
            JSONObject jo1=jo.getJSONObject("web-app");
            JSONArray ja=jo1.getJSONArray("servlet");
            JSONObject jo2=ja.getJSONObject(4);
            JSONObject jo3=jo2.getJSONObject("init-param");
            String log=jo3.getString("log");
            tv.setText(log.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}