package com.android.gifts.slidingmenuexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {
    List<ItemMenu> menuList;
    Context context;
    LayoutInflater layoutInflater;

    public MenuRecyclerViewAdapter(List<ItemMenu> menuList, Context context) {
        this.menuList = menuList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = layoutInflater.inflate(R.layout.menu_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(row);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ItemMenu item = menuList.get(position);

        holder.title.setText(item.title);
        holder.icon.setImageResource(item.iconRes);

        holder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Item Num: " + position + " Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rowLayout;
        ImageView icon;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.row_icon);
            title = (TextView) itemView.findViewById(R.id.row_title);
            rowLayout = (LinearLayout) itemView.findViewById(R.id.single_menu_row);
        }
    }
}
