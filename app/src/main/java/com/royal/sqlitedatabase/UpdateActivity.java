package com.royal.sqlitedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText edtfn, edtLn;
    Button btnUpdate;
    private DatabaseHandler db;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edtfn = findViewById(R.id.etFirstName);
        edtLn = findViewById(R.id.etLastname);
        db = new DatabaseHandler(this);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
//Edit Code
        Intent i = getIntent();
        String strfn = i.getStringExtra("FN_KEY");
        String strln = i.getStringExtra("LN_KEY");
        final String strid = i.getStringExtra("ID_KEY");
        edtfn.setText(strfn);
        edtLn.setText(strln);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strfn = edtfn.getText().toString();
                String strln = edtLn.getText().toString();
                ContactModel contact = new ContactModel();
                contact.setID(strid);
                contact.setFirstName(strfn);
                contact.setLastName(strln);
                //sQLiteHelper.updateRecord(firstname, lastname, rowID);
                db.updateRecord(contact);
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContactModel contact = new ContactModel();
                contact.setID(strid);
                db.deleteRecord(contact);
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
    }
}

