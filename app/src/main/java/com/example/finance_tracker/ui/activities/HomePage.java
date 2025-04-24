package com.example.finance_tracker.ui.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finance_tracker.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finance_tracker.data.CheckDataParser;
import com.example.finance_tracker.data.CheckRepository;

import java.util.List;

import com.google.zxing.integration.android.IntentIntegrator;

public class HomePage extends AppCompatActivity {
    private CheckRepository checkRepository;
    private TextView checktext;
    private Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        checkRepository = new CheckRepository();
        checktext = findViewById(R.id.checkText);
        btnCheck = findViewById(R.id.Checkbutton);

        String qr = "t=20250412T1534&s=5499.00&fn=7384440800460879&i=889&fp=4292899965&n=1";

        checkRepository.fetchDataFromQr(qr, new CheckRepository.OnDataFetchedListener() {
            @Override
            public void onDataFetched(List<CheckDataParser.Item> items, double totalSum) {
                StringBuilder builder = new StringBuilder();
                for(CheckDataParser.Item item : items){
                    builder.append("Продукт: ").append(item.name).append("\n");
                    builder.append("Цена: ").append(item.price).append(" руб.\n\n");
                }
                builder.append("Итого: ").append(totalSum).append(" руб.");

                btnCheck.setOnClickListener(v -> {
                    runOnUiThread(() -> checktext.setText(builder.toString()));
                });


            }

            @Override
            public void onError(String errorMessage) {
                runOnUiThread(() -> checktext.setText("Ошибка: " + errorMessage));
            }
        });


    }
}