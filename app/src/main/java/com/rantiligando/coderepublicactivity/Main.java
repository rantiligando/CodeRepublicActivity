package com.rantiligando.coderepublicactivity;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.rantiligando.coderepublicactivity.library.*;

public class Main extends Activity {
    private Intent intent;
    private Query query;
    private Bundle extras;
    private EditText txt_firstName;
    private EditText txt_lastName;
    private Button button_action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txt_firstName = (EditText) findViewById(R.id.editText_firstName);
        txt_lastName = (EditText) findViewById(R.id.editText_lastName);
        button_action = (Button) findViewById(R.id.button_register);

        query = new Query(this);


        if(this.getIntent().hasExtra("action")){
            extras = getIntent().getExtras();
            String str_firstName = extras.getString("first_name");
            String str_lastName = extras.getString("last_name");

            txt_firstName.setText(str_firstName);
            txt_lastName.setText(str_lastName);

            isEqualtoNull(extras.getString("action"))

           if(extras.getString("action").equals("update")) {
                button_action.setText("UPDATE");
            }

           Log.e("getStringExtra",getIntent().getStringExtra("action"));

        }

        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (button_action.getText().toString()){
                    case "REGISTER" :
                        if(txt_firstName.getText().toString().isEmpty() || txt_lastName.getText().toString().isEmpty()){
                            Toast.makeText(Main.this, "Fill in all fields.", Toast.LENGTH_LONG).show();
                        }else {
                            create();
                        }
                        break;
                    case "UPDATE" :
                        update();
                        break;
                    default:
                        break;
                }
            }
        };
        button_action.setOnClickListener(ocl);
    }
    public void create(){

        HashMap<String,Object> hm = new HashMap<String, Object>();
        hm.put("first_name", txt_firstName.getText().toString());
        hm.put("last_name", txt_lastName.getText().toString());

        query.insertNames(hm);

        intent = new Intent(this,NameList.class);
        startActivity(intent);
    }
    public void update(){
        int int_id = extras.getInt("_id");

        query.updateNames(txt_firstName.getText().toString(),txt_lastName.getText().toString(),int_id);

        intent = new Intent(this,NameList.class);
        startActivity(intent);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        txt_firstName.setText("");
        txt_lastName.setText("");
    }
    public static boolean isEqualtoNull(Object o1) {
        return  o1 != null;
    }
}

