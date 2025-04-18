package com.example.persianencryptor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView outputText;
    private Button encryptButton, copyButton;
    
    private static final HashMap<Character, String> PERSIAN_CHAR_MAP = new HashMap<>();

    static {
        // رمزنگاری حروف فارسی
        PERSIAN_CHAR_MAP.put('ا', "Xy7@!");
        PERSIAN_CHAR_MAP.put('ب', "Qw3*&");
        PERSIAN_CHAR_MAP.put('پ', "Zr5#$");
        // بقیه حروف...
        PERSIAN_CHAR_MAP.put('ی', "Jj5#+");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);
        encryptButton = findViewById(R.id.encryptButton);
        copyButton = findViewById(R.id.copyButton);

        encryptButton.setOnClickListener(v -> {
            String input = inputText.getText().toString();
            String encrypted = encryptPersian(input);
            outputText.setText(encrypted);
        });

        copyButton.setOnClickListener(v -> copyToClipboard(outputText.getText().toString()));
    }

    private String encryptPersian(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(PERSIAN_CHAR_MAP.getOrDefault(c, String.valueOf(c)));
        }
        return result.toString();
    }

    private void copyToClipboard(String text) {
        if (text.isEmpty()) {
            Toast.makeText(this, "متن رمزنگاری شده وجود ندارد", Toast.LENGTH_SHORT).show();
            return;
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("رمز رمزنگاری شده", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "رمز کپی شد!", Toast.LENGTH_SHORT).show();
    }
}