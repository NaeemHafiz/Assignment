package com.naeem.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.naeem.assignment.Models.User;

public class DetailActivity extends AppCompatActivity {
    private User user;
    TextView textView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView = findViewById(R.id.textview);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            assert bundle != null;
            user = bundle.getParcelable("hmm");
            if (null != user) {
                url = user.getUrl();
//                textView.setText(url);
            }

        }
    }
}
