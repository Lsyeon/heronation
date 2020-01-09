package com.example.android.drawer_heronation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//다른 프리그먼트를 쓰고싶으면 이 코드 재사용하면됨
public class Fragment1 extends Fragment {

    public Fragment1() {
    }

    @Nullable
    @Override
    //같은 생명주기로 프리그먼트가 실행될때 불러옴
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1, container, false);
    }
}