package com.royal.sqlitedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class menuActivity extends AppCompatActivity {

    private Button btnPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView tvClickme = (TextView) findViewById(R.id.tv_clickme);
        registerForContextMenu(tvClickme);
        btnPopup = (Button) findViewById(R.id.btn_popup);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.search:
                Toast.makeText(this, "Search is clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Toast.makeText(this, "Delete is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                Toast.makeText(this, "Update is clicked", Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.search:
                Toast.makeText(this, "Search is clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Toast.makeText(this, "Delete is clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.update:
                Toast.makeText(this, "Update is clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void heet(View view) {


        final PopupMenu popupMenu = new PopupMenu(this, btnPopup);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Toast.makeText(menuActivity.this, "Item is"+item.getTitle(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });


    }
}
