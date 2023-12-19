package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> item=new ArrayList<>();
        item.add(new PopularDomain("slkdjflsadjflksjfd","jsdfsfkjksafdjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj\n" +
                "lksjdflksjdfffffffffffffffffffjkllffffffffffffffff\n" +
                "lksadffffffffffffffffffffffffffffffffffffffff\n" +
                "lsdkdddddddddddddddddddddddddddddddddddddddddddddd","pic1",15,20,4));
        item.add(new PopularDomain("asdflkjfhhkjhhhhhhh","sdkkkkkkkkkkkkkkkkkkkkkkkkkkkkdd\n" +
                "sdddddddddddddddddddddddddddddddddddddddddddddddddddd\n" +
                "dfffffffffffffffffffffffffffffffffffffffffffffffffffff\n" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbf\n","pic2",10,25,5));
        item.add(new PopularDomain("sdlkfjlsdjflsakjdf","iiiiiiiiiiiiiiiiiiiiiiiiiisoadifoosdf\n" +
                "allllllllllllllllllllllllllllllllllllllllksdlf\n" +
                "laksdddddkkkdkjfkgjgggggggggggggggggg\n" +
                "asdffffffffffffffffffffffffffffffffff\n" +
                "dfffffffffffffffffffffffff","pic3",13,30,4.5));
        item.add(new PopularDomain("fffffffffsdfdd","asldlklklkkkkkkkkkkkkkkkkkkklsldfllkjlkdjjl\n" +
                "alskdflksjfddddddddddddddddddddd\n" +
                "sdddddddddddddddddddddddddddddddddddddddddddd\n","pic1",13,33,7.5));
        recyclerView =findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterPupolar=new PopularListAdapter(item);
        recyclerView.setAdapter(adapterPupolar);


    }
}