package com.example.weatherapi;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button checkBtn, clearBtn;
    EditText city;
    TextView etTemp, etMinTemp, etMaxTemp, etHumidity, etWeather, etDescription;

    Context c = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initialize();
        apiCall();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialize() {
        city = findViewById(R.id.city);
        etTemp = findViewById(R.id.etTemp);
        etMinTemp = findViewById(R.id.etMinTemp);
        etMaxTemp = findViewById(R.id.etMaxTemp);
        etHumidity = findViewById(R.id.etHumidity);
        etWeather = findViewById(R.id.etWeather);
        etDescription = findViewById(R.id.etDescription);
        checkBtn = findViewById(R.id.check);
        clearBtn = findViewById(R.id.clear);

        checkBtn.setOnClickListener(v ->
        {
            apiCall();
        });

        clearBtn.setOnClickListener(v -> {
            city.setText("");
            etTemp.setText("");
            etMinTemp.setText("");
            etMaxTemp.setText("");
            etHumidity.setText("");
            etWeather.setText("");
            etDescription.setText("");
        });
    }

    public void apiCall(){
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city.getText().toString() + "&appid=e06a35447c0f14a8e10b880451a1dcdd";

        RequestQueue r = Volley.newRequestQueue(c);

        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");

                            String temp = main.getString("temp");
                            String minTemp = main.getString("temp_min");
                            String maxTemp = main.getString("temp_max");
                            String humidity = main.getString("humidity");

                            String weather = response
                                    .getJSONArray("weather")
                                    .getJSONObject(0)
                                    .getString("main");

                            String description = response
                                    .getJSONArray("weather")
                                    .getJSONObject(0)
                                    .getString("description");

                            etTemp.setText(temp);
                            etMinTemp.setText(minTemp);
                            etMaxTemp.setText(maxTemp);
                            etHumidity.setText(humidity);
                            etWeather.setText(weather);
                            etDescription.setText(description);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(c, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        r.add(json);

    }
}