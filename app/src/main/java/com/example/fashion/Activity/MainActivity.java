package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.fashion.Adapter.PopularListAdapter;
import com.example.fashion.Domain.PopularDomain;
import com.example.fashion.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        private RecyclerView.Adapter adapterPupolar;
        private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecyclerView();

        bottom_navigation();


    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        homeBtn.setOnClickListener(view -> startActivity( new Intent(MainActivity.this,MainActivity.class)));
        cartBtn.setOnClickListener(view -> startActivity( new Intent(MainActivity.this,CartActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> item=new ArrayList<>();
        item.add(new PopularDomain("slkdjflsadjflksjfd","jsdfsfkjksafdjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj\n" +
                "lksjdflksjdfffffffffffffffffffjkllffffffffffffffff\n" +
                "lksadffffffffffffffffffffffffffffffffffffffff\n" +
                "lsdkdddddddddddddddddddddddddddddddddddddddddddddd","pic1",15,20,4152000));
        item.add(new PopularDomain("asdflkjfhhkjhhhhhhh","sdkkkkkkkkkkkkkkkkkkkkkkkkkkkkdd\n" +
                "sdddddddddddddddddddddddddddddddddddddddddddddddddddd\n" +
                "dfffffffffffffffffffffffffffffffffffffffffffffffffffff\n" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbf\n" +
                "gggfgfdgfdgfdgfdgdgfdgdgfdgfdgfdgdgdgdgdgdgf\n" +
                "hjgfhgfhgfhfhgfhfhgfhfhfhfhfhfhf\n","pic2",10,25,50025));
        item.add(new PopularDomain("sdlkfjlsdjflsakjdf","iiiiiiiiiiiiiiiiiiiiiiiiiisoadifoosdf\n" +
                "allllllllllllllllllllllllllllllllllllllllksdlf\n" +
                "laksdddddkkkdkjfkgjgggggggggggggggggg\n" +
                "asdffffffffffffffffffffffffffffffffff\n" +
                "dfffffffffffffffffffffffff","pic3",13,30,412256.5));
        item.add(new PopularDomain("fffffffffsdfdd","asldlklklkkkkkkkkkkkkkkkkkkklsldfllkjlkdjjl\n" +
                "alskdflksjfddddddddddddddddddddd\n" +
                "sdddddddddddddddddddddddddddddddddddddddddddd\n","pic1",13,33,7500.5));
        recyclerView =findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterPupolar=new PopularListAdapter(item);
        recyclerView.setAdapter(adapterPupolar);


    }
}