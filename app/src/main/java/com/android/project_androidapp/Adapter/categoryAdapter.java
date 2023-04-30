package com.android.project_androidapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.project_androidapp.Domain.categoryList;
import com.android.project_androidapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Cho nay nhu kieu framwork, khong can hieu het
public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
    //Tao ra list category de truyen vao ham tao de nhan tham so la list category truyen vao tu ben kia
    ArrayList<categoryList> categoryLists;

    //tao ra mot category duoc cau hinh va co data la list category
    public categoryAdapter(ArrayList<categoryList> categoryLists) {
        this.categoryLists = categoryLists;
    }


    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ham nay giong kieu set Layout nao de lam viec, giong ham setContentView(R.layout.activity_main) trong ham main
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    //ham nay de cau hinh xem cac category se duoc hien thi nhu the nao
    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, int position) {
        //Set ten cua category bang cach lay ten theo vi tri cua category trong list(mang) truyen vao
        holder.categoryName.setText(categoryLists.get(position).getName());
        //Tao bien ten anh background
        String picUrl = "";
        //Set background theo vi tri category trong List(mang) truyen vao
        switch (position){
            case 0:{
                picUrl = "cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }
            case 1:{
                picUrl = "cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }
            case 2:{
                picUrl = "cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }
            case 3:{
                picUrl = "cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background4));
                break;
            }
            case 4:{
                picUrl = "cat_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background5));
                break;
            }
        }
        //Ham nay de lay avatar picUrl theo ten picUrl lay duoc ben tren
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
    }


    @Override
    public int getItemCount() {
        return categoryLists.size();
    }

    //class nay chinh tao ra categoryAdapter.ViewHolder de truyen vao cac method tren
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Mot category tren layout se co ConstrainLayout, textView de hien thi ten category, ImageView de hien thi hinh icon
        //Xem file viewholder_category.xml
        //category mau duoc tao ra trong file viewholder_category.xml chi la de lay Id cua ConstrainLayout, ImageView voi TextView thoi
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;
        public ViewHolder(View inflate) {
            super(inflate);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayoutCat);
        }
    }
}
