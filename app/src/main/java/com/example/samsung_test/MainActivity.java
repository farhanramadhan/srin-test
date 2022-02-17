package com.example.samsung_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<DBHelper> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(arrayList));
    }
    public void getData(){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET,"https://reqres.in/api/users",null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArr = response.getJSONArray("data");
                    for (int i=0;i<jsonArr.length();i++){
                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                        Log.d("my-api","==== "+jsonObj.getString("id"));
                        Log.d("my-api","==== "+jsonObj.getString("email"));
                        Log.d("my-api","==== "+jsonObj.getString("first_name"));
                        Log.d("my-api","==== "+jsonObj.getString("last_name"));
                        Log.d("my-api","==== "+jsonObj.getString("avatar"));
                        arrayList.add(new DBHelper(jsonObj.getString("id"), jsonObj.getString("email"), jsonObj.getString("first_name"), jsonObj.getString("last_name"), jsonObj.getString("avatar")));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                recyclerView.setAdapter(new ListAdapter(arrayList));
            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("my-api","went Wrong");
            }
        });
        requestQueue.add(jsonObjRequest);
    }
}