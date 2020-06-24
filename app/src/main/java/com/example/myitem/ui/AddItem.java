package com.example.myitem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myitem.R;
import com.example.myitem.database.DatabaseHandler;
import com.example.myitem.pojo.Item;

public class AddItem extends AppCompatActivity {

    private EditText id_addItem_edtxt, id_itemDesc_edtxt;
    private Button id_cancelBtn, id_doneBtn;
    private DatabaseHandler dbHandler;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        init();

        id_doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName = id_addItem_edtxt.getText().toString().trim();
                String itemDesc = id_itemDesc_edtxt.getText().toString().trim();


                if (itemName.isEmpty()){
                    id_addItem_edtxt.requestFocus();
                    id_addItem_edtxt.setError("Enter item name");
                }else if(itemDesc.isEmpty()){
                    id_itemDesc_edtxt.requestFocus();
                    id_itemDesc_edtxt.setError("Enter item description");
                }else {

                    item.setItem_title(itemName);
                    item.setItem_desc(itemDesc);
                    item.setItem_check(0);

                    dbHandler.addItem(item);

                    getScreen();
                }

            }
        });

        id_cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });

    }

    private void getScreen() {

        Intent intent = new Intent(AddItem.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void init() {

        id_addItem_edtxt    = findViewById(R.id.id_addItem_edtxt);
        id_itemDesc_edtxt   = findViewById(R.id.id_itemDesc_edtxt);
        id_cancelBtn        = findViewById(R.id.id_cancelBtn);
        id_doneBtn          = findViewById(R.id.id_doneBtn);
        item = new Item();
        dbHandler = new DatabaseHandler(this);

    }
}
