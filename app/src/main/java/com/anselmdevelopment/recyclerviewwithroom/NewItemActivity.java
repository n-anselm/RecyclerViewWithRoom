package com.anselmdevelopment.recyclerviewwithroom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewItemActivity extends AppCompatActivity {

    private EditText editText;
    private TextView tvSave;
    public static final String EXTRA_REPLY = "addnewitem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        editText = findViewById(R.id.editText);
        tvSave = findViewById(R.id.tvSave);
        tvSave.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editText.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String itemString = editText.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, itemString);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}