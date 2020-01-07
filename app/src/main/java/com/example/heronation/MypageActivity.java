package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MypageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
    }
    /*
     * Item 버튼을  눌렀을 때, Item Activity 이동
     * Shop 버튼을 눌렀을 때, Shop Activity 이동
     * 측정 버튼을 눌렀을 때, Measurement Activity 이동
     * 찜 버튼을 눌렀을 때, Wishlist Activity 이동
     * 마이 페이지 버튼을 눌렀을 때, Mypage Activity 이동
     */
    public void Click_itemButton(View view){
        Intent intent=new Intent(this, ItemActivity.class);
        startActivity(intent);
    }
    public void Click_shopButton(View view){
        Intent intent=new Intent(this,ShopActivity.class);
        startActivity(intent);
    }
    public void Click_measurementButton(View view){
        Intent intent=new Intent(this,MeasurementActivity.class);
        startActivity(intent);
    }
    public void Click_wishlistButton(View view){
        Intent intent=new Intent(this,WishlistActivity.class);
        startActivity(intent);
    }

}
