package com.zlayar.zlayar.FragmentMain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;

public class terms extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        textView = (TextView) findViewById(R.id.textTerms);
        imageView = (ImageView) findViewById(R.id.backTerms);

        if (MainActivity.text.isEmpty()){
            textView.setText("We are sorry, this not available for now");
        }
        else {
            textView.setText(MainActivity.text);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(terms.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(terms.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
