package com.example.myitem.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.myitem.R;
import com.example.myitem.adapter.ItemAdapter;
import com.example.myitem.database.DatabaseHandler;
import com.example.myitem.pojo.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.clickListener {

    private Button id_addBtn;
    private SearchView id_searchView;
    private RecyclerView id_itemRC;
    private TextView id_noData_txt;
    private DatabaseHandler dbHandler;
    private Item item;
    private List<Item> itemList;
    private ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        id_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                itemList = dbHandler.getSearchItem(newText);

                if(itemList.size() != 0){

                    id_noData_txt.setVisibility(View.GONE);
                    id_itemRC.setVisibility(View.VISIBLE);

                    adapter = new ItemAdapter(MainActivity.this, itemList);
                    id_itemRC.setAdapter(adapter);

                }else {
                    id_itemRC.setVisibility(View.GONE);
                    id_noData_txt.setVisibility(View.VISIBLE);

                    Log.e("Item ", "Not found");
                }



                return true;
            }
        });


        id_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, AddItem.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }

    private void init() {

        id_addBtn       = findViewById(R.id.id_addBtn);
        id_searchView   = findViewById(R.id.id_searchView);
        id_noData_txt   = findViewById(R.id.id_noData_txt);
        id_itemRC       = findViewById(R.id.id_itemRC);
        id_itemRC.setLayoutManager(new LinearLayoutManager(this));
        item = new Item();
        itemList = new ArrayList<>();
        dbHandler = new DatabaseHandler(this);

        getItemList();


    }

    private void getItemList() {

        itemList = dbHandler.getAllItem();

        if(itemList.size() != 0){

            id_noData_txt.setVisibility(View.GONE);
            id_itemRC.setVisibility(View.VISIBLE);

            adapter = new ItemAdapter(this, itemList);
            id_itemRC.setAdapter(adapter);

        }else {
            id_itemRC.setVisibility(View.GONE);
            id_noData_txt.setVisibility(View.VISIBLE);

            Log.e("Item ", "Not found");
        }




    }


    @Override
    public void clickInputSent(int check_OR_notCheck, String id) {

       dbHandler.updateItem_status(check_OR_notCheck, id);


        getItemList();
    }
}
