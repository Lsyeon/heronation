package com.example.heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

public class ItemActivity extends AppCompatActivity implements
        ItemHomeFragment.OnFragmentInteractionListener,
        ItemNewFragment.OnFragmentInteractionListener,
        ItemBestFragment.OnFragmentInteractionListener,
        ItemAiFragment.OnFragmentInteractionListener,
        ItemSaleFragment.OnFragmentInteractionListener{

    /*
     * Fragment Manager 선언 -- Acitivity 내에서 Fragment를 관리해주기 위해서는 FragmentManager를 사용해야함
     *
     * 각각의 Fragment를 선언하고, Fragment 객체 생성
     */
    private FragmentManager fragmentManager=getSupportFragmentManager(); //Fragment 가져오기
    private TabLayout item_tabLayout;
    private ItemHomeFragment itemHomeFragment =new ItemHomeFragment();
    private ItemNewFragment itemNewFragment =new ItemNewFragment();
    private ItemBestFragment itemBestFragment =new ItemBestFragment();
    private ItemAiFragment itemAiFragment =new ItemAiFragment();
    private ItemSaleFragment itemSaleFragment =new ItemSaleFragment();

    /* 프래그먼트 나타낼때, 프래그먼트를 담는 뷰페이저, 뷰페이저를 도와주는 어댑터 */
    private ViewPager viewPager;
    private ItemViewPagerAdapter itemViewPagerAdapter;

    /* 상단 메뉴 버튼을 눌렀을 때 뜨는 레이아웃을 위한 변수들 */
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_item);

          /* Item의 상단탭
         하단탭에서 Item의 상단탭을 선택했을 시에만 보여져야 함
         */
        item_tabLayout=(TabLayout)findViewById(R.id.item_tab_layout);

        /* 뷰페이져 어댑터 객체를 생성하고,
         * 생성자를 통해서 프래그먼트 관리를 도와주는 FragmentManager와
         * 페이지의 개수를 탭의 개수와 맞춰주기 위해 Page Count를 받아온다.
         * 뷰페이저에 어댑터를 설정한다.
         * 그 후, tabLayout과 viewPager 연결
         */
        viewPager=(ViewPager)findViewById(R.id.item_fragment_container);
        itemViewPagerAdapter=new ItemViewPagerAdapter(getSupportFragmentManager(),item_tabLayout.getTabCount());
        viewPager.setAdapter(itemViewPagerAdapter);

        /*
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        item_tabLayout.addOnTabSelectedListener(new ItemTopItemSelectedListener());

        /* ViewPager의 페이지가 변경될 때 알려주는 리스너*/
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(item_tabLayout));

        /* 상단바 메뉴 드로워 */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);
        ImageButton btn_open = (ImageButton) findViewById(R.id.btn_open);   //openimage 정의
        btn_open.setOnClickListener(new View.OnClickListener() {

            @Override   //클릭했을때 Drawer open
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        Button btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        /* 상단바 메뉴 드로워 */
    }

    /*
     * Item 상단 탭에 있는 특정 값을 선택하였을 때
     * Switch문으로 경우를 나누어
     * 홈 버튼을  눌렀을 때, ItemHomeFragment로 이동
     * 신상 버튼을 눌렀을 때, ItemNewFragment로 이동
     * 베스트 버튼을 눌렀을 때, ItemBestFragment로 이동
     * AI 버튼을 눌렀을 때, ItemAiFragment로 이동
     * 세일 버튼을 눌렀을 때, ItemSaleFragment로 이동*/
    class ItemTopItemSelectedListener implements TabLayout.OnTabSelectedListener{
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
     * Item 버튼을  눌렀을 때, Item Activity 이동
     * Shop 버튼을 눌렀을 때, Shop Activity 이동
     * 측정 버튼을 눌렀을 때, Measurement Activity 이동
     * 찜 버튼을 눌렀을 때, Wishlist Activity 이동
     * 마이 페이지 버튼을 눌렀을 때, Mypage Activity 이동*/
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
    public void Click_mypageButton(View view){
        Intent intent=new Intent(this,MypageActivity.class);
        startActivity(intent);
    }



    ///그냥 나중에 필요할까봐 넣어 놓았습니다
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override//슬라이드했을때 호출
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override// 무언가가 오픈됐을때
        public void onDrawerOpened(@NonNull View drawerView) {
        }

        @Override//닫혔을때
        public void onDrawerClosed(@NonNull View drawerView) {
        }

        @Override //바뀌었을때
        public void onDrawerStateChanged(int newState) {
        }
    };
    /* 수정필요 */
    public void button_listener(){
        final ImageButton item_button=(ImageButton)findViewById(R.id.item_bottom_menu);
        item_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        item_button.setBackgroundResource(R.drawable.item_svg2);
                    case MotionEvent.ACTION_UP:
                        item_button.setBackgroundResource(R.drawable.item_svg);
                }
                return false;
            }
        });

    }

    /*
     * onFragmenInteraciton~ 를 한 이유
     * Acitivity와 Fragment가 통신할 때, OnFragmentInteractionListener를 사용함.
     * 프래그먼트에서 액티비티로 통신(데이터 주고 받는 것)이 있을 수도 있기 때문에
     * MainActivity 에서 이를 implement한 후 오버라이딩 (임시로)*/
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
