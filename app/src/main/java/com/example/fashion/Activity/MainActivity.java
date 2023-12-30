package com.example.fashion.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fashion.Adapter.PopularListAdapter;
import com.example.fashion.Domain.ProductResult;
import com.example.fashion.Helper.ServerDetail;
import com.example.fashion.R;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
        private RecyclerView.Adapter adapterPupolar;
        private RecyclerView recyclerView;
       private RequestQueue requestProductQueue, requestCategoryQueue;
        private RecyclerView recycler_categorie;
        private StringRequest productStringRequest, categoryStringRequest;
        private LinearLayout profileBtn;


        private LinearLayout wishlistBtn,cartBtn,homeBtn;
        private ImageView notification_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        bottom_navigation();
        sendProductRequest();

    }


    private void sendProductRequest(){
        requestProductQueue = Volley.newRequestQueue(this);
        productStringRequest =new StringRequest(Request.Method.GET, "http://192.168.1.2:8000/api/products/?page=1", response -> {
            Gson gson = new Gson();
            ProductResult item = gson.fromJson(response, ProductResult.class);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapterPupolar = new PopularListAdapter(item);
            recyclerView.setAdapter(adapterPupolar);
            Log.i("RESPONSE", "OnResponse: " + item.getResults().size());
        }, error -> {
            Log.i("RESPONSE", "OnErrorResponse: " + error.toString());


        });
        requestProductQueue.add(productStringRequest);

    }

    private void bottom_navigation() {
        profileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);            });
        homeBtn.setOnClickListener(view -> startActivity( new Intent(MainActivity.this,MainActivity.class)));
        cartBtn.setOnClickListener(view -> startActivity( new Intent(MainActivity.this,CartActivity.class)));
        notification_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);            });
        wishlistBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FovoriteActivity.class);
            startActivity(intent);

        });

    }

    private void init() {
        wishlistBtn = findViewById(R.id.wishlistBtn);
        notification_btn = findViewById(R.id.notifiction_btn);
        profileBtn = findViewById(R.id.profileBtn);
        recyclerView = findViewById(R.id.view1);
        homeBtn = findViewById(R.id.homeBtn);
        cartBtn = findViewById(R.id.cartBtn);






//        ArrayList<PopularDomain> item = new ArrayList<>();
//        item.add(new PopularDomain("slkdjflsadjflksjfd", "jsdfsfkjksafdjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj\n" +
//                "lksjdflksjdfffffffffffffffffffjkllffffffffffffffff\n" +
//                "lksadffffffffffffffffffffffffffffffffffffffff\n" +
//                "lsdkdddddddddddddddddddddddddddddddddddddddddddddd", "pic1", 15, 20, 4152000));
//        item.add(new PopularDomain("asdflkjfhhkjhhhhhhh", "sdkkkkkkkkkkkkkkkkkkkkkkkkkkkkdd\n" +
//                "sdddddddddddddddddddddddddddddddddddddddddddddddddddd\n" +
//                "dfffffffffffffffffffffffffffffffffffffffffffffffffffff\n" +
//                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbf\n" +
//                "gggfgfdgfdgfdgfdgdgfdgdgfdgfdgfdgdgdgdgdgdgf\n" +
//                "hjgfhgfhgfhfhgfhfhgfhfhfhfhfhfhf\n", "pic2", 10, 25, 50025));
//        item.add(new PopularDomain("sdlkfjlsdjflsakjdf", "iiiiiiiiiiiiiiiiiiiiiiiiiisoadifoosdf\n" +
//                "allllllllllllllllllllllllllllllllllllllllksdlf\n" +
//                "laksdddddkkkdkjfkgjgggggggggggggggggg\n" +
//                "asdffffffffffffffffffffffffffffffffff\n" +
//                "dfffffffffffffffffffffffff", "pic3", 13, 30, 412256.5));
//        item.add(new PopularDomain("fffffffffsdfdd", "asldlklklkkkkkkkkkkkkkkkkkkklsldfllkjlkdjjl\n" +
//                "alskdflksjfddddddddddddddddddddd\n" +
//                "sdddddddddddddddddddddddddddddddddddddddddddd\n", "pic1", 13, 33, 7500.5));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        adapterPupolar = new PopularListAdapter(item);


    }
}




