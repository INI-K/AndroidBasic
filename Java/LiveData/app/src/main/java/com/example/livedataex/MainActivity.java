package com.example.livedataex;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    EditText nameInputView;
    TextView nameTextView;
    Button inputButton;

    NameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInputView = findViewById(R.id.inputName);
        nameTextView = findViewById(R.id.nameView);
        inputButton = findViewById(R.id.inputButton);

        viewModel = new ViewModelProvider(this , new NameViewModelFactory()).get(NameViewModel.class);

        Observer<String> nameObserver = newName -> nameTextView.setText(newName);

        viewModel.getCurrentName().observe(this, nameObserver);

        inputButton.setOnClickListener(view -> {
            String inputName = nameInputView.getText().toString();
            viewModel.getCurrentName().setValue(inputName);
        });
    }
}