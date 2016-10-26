package com.test.alexward.broadcastr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
/*
FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), addGroupActivity.class);
                startActivity(myIntent);
            }
        });
 */
        Button butt = (Button) findViewById(R.id.Save);
            butt.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v){
                    Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(myIntent);
                }
            });

    }
}
