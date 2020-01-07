package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;

public class WishlistActivity extends AppCompatActivity implements
        WishlistShopFragment.OnFragmentInteractionListener,
        WishlistRecentlyViewedItemFragment.OnFragmentInteractionListener,
        WishlistItemFragment.OnFragmentInteractionListener {
        /*
         * Fragment Manager 선언 -- Acitivity 내에서 Fragment를 관리해주기 위해서는 FragmentManager를 사용해야함
         *
         * 각각의 Fragment를 선언하고, Fragment 객체 생성
         */
        private FragmentManager fragmentManager = getSupportFragmentManager(); //Fragment 가져오기{

        private TabLayout wishlist_tabLayout;
        private WishlistItemFragment wishlistItemFragment = new WishlistItemFragment();
        private WishlistShopFragment wishlistShopFragment = new WishlistShopFragment();
        private WishlistRecentlyViewedItemFragment wishlistRecentlyViewedItemFragment = new WishlistRecentlyViewedItemFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wishlist);

           /* Wishlist의 상단탭
         하단탭에서 Wishlist의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        wishlist_tabLayout=(TabLayout)findViewById(R.id.wishlist_tab_layout);
        wishlist_tabLayout.addOnTabSelectedListener(new WishlistTopItemSelectedListener());

        /* 첫 화면이 WishlistItemFragment이므로, Transaction을 getSupportFragmentManager().beginTransaction()을 통해 가져온 후,
         * acitivity_wishlist.xml에 있는 framelayout인 wishlist_fragment_container의 화면을 shopFragment로 변경해준 후,
         * commit 호출해주어야 Transaction 작업이 완료됨.
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.wishlist_fragment_container, wishlistItemFragment).commit();
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
    public void Click_mypageButton(View view){
        Intent intent=new Intent(this,MypageActivity.class);
        startActivity(intent);
    }

    /*
     * Wishlist 상단 탭에 있는 특정 값을 선택하였을 때
     * Switch문으로 경우를 나누어
     * 찜 버튼을  눌렀을 때, wishlistItemFragment로 이동
     * 샵 버튼을 눌렀을 때, wishlistShopFragment로 이동
     * 최근 본 상품 버튼을 눌렀을때, wishlistRecentlyViewedItemFragment로 이동
     */
    class WishlistTopItemSelectedListener implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            FragmentTransaction transaction=fragmentManager.beginTransaction(); // FragmentTransaction 가져오기
            int position=tab.getPosition(); // 상단탭의 선택된 현재 위치 받아오기
            switch(position) {
                case 0:
                    transaction.replace(R.id.wishlist_fragment_container, wishlistItemFragment).commit();
                    break;
                case 1:
                    transaction.replace(R.id.wishlist_fragment_container, wishlistShopFragment).commit();
                    break;
                case 2:
                    transaction.replace(R.id.wishlist_fragment_container, wishlistRecentlyViewedItemFragment).commit();
                    break;
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    /*
     * onFragmenInteraciton~ 를 한 이유
     * Acitivity와 Fragment가 통신할 때, OnFragmentInteractionListener를 사용함.
     * 프래그먼트에서 액티비티로 통신(데이터 주고 받는 것)이 있을 수도 있기 때문에
     * MainActivity 에서 이를 implement한 후 오버라이딩 (임시로)
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
