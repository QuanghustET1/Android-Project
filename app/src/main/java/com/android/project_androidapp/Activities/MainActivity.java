package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.project_androidapp.Adapter.categoryAdapter;
import com.android.project_androidapp.Adapter.popularAdapter;
import com.android.project_androidapp.DB.DB_ManageCart;
import com.android.project_androidapp.Domain.categoryList;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.DB.ManageCart;
import com.android.project_androidapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerPopularViewFood;
    private RecyclerView.Adapter recyclerViewFoodAdapter;
    private FloatingActionButton btnShowListCart;
//    private ManageCart manageCart;
    private DB_ManageCart dbManageCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.manageCart = new ManageCart(this);
        this.dbManageCart = new DB_ManageCart(this);
        //Ham nay de set category vao Layout cho RecyclerView
        recyclerViewCategory();
        popularViewFood();
        showListCartManager();
    }

    private void showListCartManager() {
        this.btnShowListCart = findViewById(R.id.btnShowListCart);
        this.btnShowListCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartManager.class).putExtra("cartManage", MainActivity.this.dbManageCart.getListFood()));
            }
        });
    }

    private void popularViewFood() {
        recyclerPopularViewFood = findViewById(R.id.recyclerView1);
        recyclerPopularViewFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<foodDomain> foodDomainsList = new ArrayList<>();
        foodDomainsList.add(new foodDomain("Pepperoni Pizza", "pop_1", "pizza description", 9.6, 0));
        foodDomainsList.add(new foodDomain("Vegetable Pizza", "pop_2", "vegetable description", 8.9, 0));
        foodDomainsList.add(new foodDomain("Cheese Burger", "pop_3", "burger description", 7.7, 0));
        recyclerViewFoodAdapter = new popularAdapter(foodDomainsList);
        recyclerPopularViewFood.setAdapter(recyclerViewFoodAdapter);
    }

    private void recyclerViewCategory() {
        //Tao ra 1 LinearLayoutManager voi flow theo chieu ngang, vi cac item se sap xep theo chieu ngang
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //Connect attribute recyclerViewCategoryList voi recyclerView tren layout
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        //Set LinearLayoutManager da setup vao recyclerViewList
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        //Tao ra List cac Category voi Ten va Ten_anh_background de truyen sang cho Adapter
        ArrayList<categoryList> arrayList = new ArrayList<>();
        arrayList.add(new categoryList("pizza", "pic_pizza"));
        arrayList.add(new categoryList("rice", "pic_rice"));
        arrayList.add(new categoryList("salad", "pic_salad"));
        arrayList.add(new categoryList("HotDog", "pic_hotdog"));
        arrayList.add(new categoryList("Hambuger", "pic_hambuger"));
        //Truyen list Category vao Adapter de Config va tao ra doi tuong recyclerView.Adapter
        recyclerAdapter = new categoryAdapter(arrayList);
        //Set Adapter vao recyclerViewCategoryList
        recyclerViewCategoryList.setAdapter(recyclerAdapter);
    }
}