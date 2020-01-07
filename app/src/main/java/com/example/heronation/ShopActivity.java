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

public class ShopActivity extends AppCompatActivity implements
        ShopFavoritesFragment.OnFragmentInteractionListener,
        ShopRankingFragment.OnFragmentInteractionListener{
    /*
     * Fragment Manager 선언 -- Acitivity 내에서 Fragment를 관리해주기 위해서는 FragmentManager를 사용해야함
     *
     * 각각의 Fragment를 선언하고, Fragment 객체 생성
     */
    private FragmentManager fragmentManager=getSupportFragmentManager(); //Fragment 가져오기
    private TabLayout shop_tabLayout;
    private ShopRankingFragment shopRankingFragment=new ShopRankingFragment();
    private ShopFavoritesFragment shopFavoritesFragment=new ShopFavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);

           /* Shop의 상단탭
         하단탭에서 Shop의 상단탭을 선택했을 시에만 보여져야 함
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        shop_tabLayout=(TabLayout)findViewById(R.id.shop_tab_layout);
        shop_tabLayout.addOnTabSelectedListener(new ShopTopItemSelectedListener());

        /* 첫 화면이 ShopRankingFragment이므로, Transaction을 getSupportFragmentManager().beginTransaction()을 통해 가져온 후,
         * acitivity_shop.xml에 있는 framelayout인 shop_fragment_container의 화면을 shopFragment로 변경해준 후,
         * commit 호출해주어야 Transaction 작업이 완료됨.
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.shop_fragment_container, shopRankingFragment).commit();

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
    public void Click_measurementButton(View view){
        Intent intent=new Intent(this,MeasurementActivity.class);
        startActivity(intent);
    }
    public void Click_wishlistButton(View view){
        Intent intent=new Intent(this,WishlistActivity.class);
        startActivity(intent);
    }
    public void Click_mypageButton(View view){
        Intent intent=new Intent(this,MypageActivity.class);
        startActivity(intent);
    }

    /*
     * Shop 상단 탭에 있는 특정 값을 선택하였을 때
     * Switch문으로 경우를 나누어
     * Shop Ranking 버튼을  눌렀을 때, ShopRankingFragment로 이동
     * 즐겨찾기 버튼을 눌렀을 때, ShopFavoritesFragment로 이동
     */
    class ShopTopItemSelectedListener implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            FragmentTransaction transaction=fragmentManager.beginTransaction(); // FragmentTransaction 가져오기
            int position=tab.getPosition(); // 상단탭의 선택된 현재 위치 받아오기
            switch(position) {
                case 0:
                    transaction.replace(R.id.shop_fragment_container, shopRankingFragment).commit();
                    break;
                case 1:
                    transaction.replace(R.id.shop_fragment_container, shopFavoritesFragment).commit();
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
