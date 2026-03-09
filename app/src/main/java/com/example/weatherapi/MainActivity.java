package com.example.weatherapi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button checkBtn, clearBtn;
    EditText etCity;
    TextView etTemp, etMinTemp, etMaxTemp, etHumidity, etWeather, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initialize();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void initialize(){
        etCity = findViewById(R.id.etCity);
        etTemp = findViewById(R.id.etTemp);
        etMinTemp = findViewById(R.id.etMinTemp);
        etMaxTemp = findViewById(R.id.etMaxTemp);
        etHumidity = findViewById(R.id.etHumidity);
        etWeather = findViewById(R.id.etWeather);
        etDescription = findViewById(R.id.etDescription);
        checkBtn = findViewById(R.id.checkBtn);
        clearBtn = findViewById(R.id.clearBtn);

        checkBtn.setOnClickListener(v -> {

        });

        clearBtn.setOnClickListener(v ->{
            etCity.setText("");
            etTemp.setText("");
            etMinTemp.setText("");
            etMaxTemp.setText("");
            etHumidity.setText("");
            etWeather.setText("");
            etDescription.setText("");
        });
    }
}