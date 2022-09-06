package com.royal.sqlitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list);

        DatabaseHandler db = new DatabaseHandler(this);
        final ArrayList<ContactModel> contacts = db.getAllRecords();

        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this,contacts);
        listView.setAdapter(myBaseAdapter);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });
        if (contacts.size() > 0) {
            for (int i = 0; i < contacts.size(); i++) {

                Log.e("Name====>",contacts.get(i).getFirstName());
            }
        }
    }
}

