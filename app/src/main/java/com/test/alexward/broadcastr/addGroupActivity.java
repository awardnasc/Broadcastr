package com.test.alexward.broadcastr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addGroupActivity extends AppCompatActivity {
    String phoneNums;
    String groupName;
    EditText nums;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        name = (EditText)findViewById(R.id.editText);
        nums = (EditText)findViewById(R.id.editText3);

        Button butt = (Button) findViewById(R.id.Save);
            butt.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v){
                    groupName = name.getText().toString();
                    phoneNums = nums.getText().toString();
                    Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(myIntent);

                }
            });

    }
}
