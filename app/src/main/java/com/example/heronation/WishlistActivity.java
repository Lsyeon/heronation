package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

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

    /* 프래그먼트 나타낼때, 프래그먼트를 담는 뷰페이저, 뷰페이저를 도와주는 어댑터 */
    private ViewPager viewPager;
    private WishlistViewPagerAdapter wishlistViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wishlist);

           /* Wishlist의 상단탭
         하단탭에서 Wishlist의 상단탭을 선택했을 시에만 보여져야 함
          */
        wishlist_tabLayout=(TabLayout)findViewById(R.id.wishlist_tab_layout);

        /* 뷰페이져 어댑터 객체를 생성하고,
         * 생성자를 통해서 프래그먼트 관리를 도와주는 FragmentManager와
         * 페이지의 개수를 탭의 개수와 맞춰주기 위해 Page Count를 받아온다.
         * 뷰페이저에 어댑터를 설정한다.
         * 그 후, tabLayout과 viewPager 연결
         */
        viewPager=(ViewPager)findViewById(R.id.wishlist_fragment_container);
        wishlistViewPagerAdapter=new WishlistViewPagerAdapter(getSupportFragmentManager(),wishlist_tabLayout.getTabCount());
        viewPager.setAdapter(wishlistViewPagerAdapter);

        /*
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        wishlist_tabLayout.addOnTabSelectedListener(new WishlistTopItemSelectedListener());

        /* ViewPager의 페이지가 변경될 때 알려주는 리스너*/
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(wishlist_tabLayout));
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
            viewPager.setCurrentItem(tab.getPosition());
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
