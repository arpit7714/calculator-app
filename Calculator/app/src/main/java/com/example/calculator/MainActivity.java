package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView1 = (GridView) findViewById(R.id.gridview1);
        //GridView gridView2=(GridView)findViewById(R.id.gridview2);
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("C");
        numbers.add("<");
        numbers.add("^");
        numbers.add("/");
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("*");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("-");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");
        numbers.add("+");
        numbers.add("0");
        numbers.add("00");
        numbers.add(".");
        numbers.add("=");

        //declared as final
        final EditText editText = (EditText) findViewById(R.id.edittext);
        //array adapter for ht
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.grid_item, numbers);
        //ArrayAdapter<String>adapter1=new ArrayAdapter<String>(this,R.layout.grid_item2,symbols);
        //gridView2.setAdapter(adapter1);
        gridView1.setAdapter(adapter);
        //final ArrayList<String> calculation=new ArrayList<>();
        //this is used to place the cursor after the number
        //editText.setSelection(editText.getText().length());

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(MainActivity.this, ((TextView)view).getText(),Toast.LENGTH_SHORT).show();
                CharSequence text = ((TextView) view).getText();
                if(text=="=")
                {
                    String calculation = editText.getText().toString();
                    Log.v("message",calculation);
                    String result=String.valueOf(calculations.calculateexpression(calculation));
                    editText.setText(result);
                    //to get the cursor after the text in the edit text box
                    editText.setSelection(editText.getText().length());
                }
                else if(text=="<")
                {
                    //this is used to clear the text present in the edit text by 1 amount
                    int length = editText.getText().length();
                    if (length > 0) {
                        editText.getText().delete(length - 1, length);
                    }

                }
                else if(text=="C")
                {
                    editText.setText("");
                }
                else {
                    editText.append(text);
                }
            }
        });
    }
}
