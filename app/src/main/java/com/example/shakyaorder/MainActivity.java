package com.example.shakyaorder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.shakyaorder.databinding.ActivityMainBinding;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent intent = result.getData();
            if (intent != null && intent.getExtras() != null) {
                Bundle bundle = intent.getExtras();
                String beverageName = bundle.getString("beverageName");
                String sugarLevel = bundle.getString("sugarLevel");
                String iceLevel = bundle.getString("iceLevel");

                binding.beverageNameDetailsTextView.setText("飲料：" + beverageName);
                binding.sugarLevelDetailsTextView.setText("甜度：" + sugarLevel);
                binding.iceLevelDetailsTextView.setText("冰塊：" + iceLevel);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.orderButton.setOnClickListener(view -> {
            launcher.launch(new Intent(this, BeverageActivity.class));
        });
    }
}
