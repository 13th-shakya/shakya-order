package com.example.shakyaorder;

import android.content.Intent;
import android.os.Bundle;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.shakyaorder.databinding.ActivityBeverageBinding;

public class BeverageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityBeverageBinding binding = ActivityBeverageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.submitButton.setOnClickListener(view -> {
            String beverageName = binding.beverageNameEditText.getText().toString();
            String sugarLevel = getSelectedRadioButtonText(binding.sugarLevelRadioGroup);
            String iceLevel = getSelectedRadioButtonText(binding.iceLevelRadioGroup);

            Bundle bundle = new Bundle();
            bundle.putString("beverageName", beverageName);
            bundle.putString("sugarLevel", sugarLevel);
            bundle.putString("iceLevel", iceLevel);

            Intent intent = new Intent().putExtras(bundle);
            setResult(RESULT_OK, intent);

            finish();
        });
    }

    String getSelectedRadioButtonText(RadioGroup group) {
        int id = group.getCheckedRadioButtonId();
        RadioButton view = findViewById(id);
        return view.getText().toString();
    }
}

