package com.example.fashion.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


// MyDialogFragment.java

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.fashion.R;

public class MyDialogFragment extends FragmentActivity {



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // قم بتحميل تخطيط صفحة الـ Dialog من هنا (يمكن أن يكون XML)
        View view = inflater.inflate(R.layout.viewholder_pop_detail, container, false);
        return view;
    }

    public void showDialog(FragmentManager supportFragmentManager, String myDialogFragment) {
    }
}
