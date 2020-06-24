package com.example.myitem.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myitem.R;
import com.example.myitem.pojo.Item;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private Context context;
    private List<Item> itemList;
    private clickListener listener;


    public interface clickListener {
        void clickInputSent(int check_OR_notCheck, String id);
    }


    public ItemAdapter(Context context, List<Item> itemList) {

        this.context  = context;
        this.itemList = itemList;

        listener = (clickListener) context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {

        holder.id_item_name.setText(itemList.get(position).getItem_title());
        holder.id_item_desc.setText(itemList.get(position).getItem_desc());

        if (itemList.get(position).getItem_check() == 1){

            holder.id_card.setBackgroundColor(Color.parseColor("#DCDBDB"));
            holder.id_itemCheck.setChecked(true);

        }else {
            holder.id_itemCheck.setChecked(false);
        }


        holder.id_itemCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isChecked = holder.id_itemCheck.isChecked();

                if (isChecked){


                    listener.clickInputSent(1, itemList.get(position).getId());


                }else {

                    listener.clickInputSent(0, itemList.get(position).getId());

                }


            }
        });




    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView id_item_name, id_item_desc;
        CheckBox id_itemCheck;
        LinearLayout id_card;



        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            id_item_name = itemView.findViewById(R.id.id_item_name);
            id_item_desc = itemView.findViewById(R.id.id_item_desc);
            id_itemCheck = itemView.findViewById(R.id.id_itemCheck);
            id_card      = itemView.findViewById(R.id.id_card);

        }
    }
}
