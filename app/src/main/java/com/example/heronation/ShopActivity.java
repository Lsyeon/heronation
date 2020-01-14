package com.example.heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

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

    /* 프래그먼트 나타낼때, 프래그먼트를 담는 뷰페이저, 뷰페이저를 도와주는 어댑터 */
    private ViewPager viewPager;
    private ShopViewPagerAdapter shopViewPagerAdapter;

    /* 상단 메뉴 버튼을 눌렀을 때 뜨는 레이아웃을 위한 변수들 */
    private DrawerLayout drawerLayout;
    private View drawerView;

    /* Shop Ranking에 필터 버튼 눌렀을 때, seekBar 설정에 필요한 변수들 */
    int number=0;
    private SeekBar seekBar;
    private TextView age;
    /* Shop Ranking에 필터 버튼에 필요한 변수들 */
    private Button gender_male;
    private Button gender_female;
    private Button category_cloth;
    private Button category_acc;
    private Button category_bag;
    private Button category_shoes;
    private Button style_feminine;
    private Button style_modern;
    private Button style_simple;
    private Button style_lovely;
    private Button style_unique;
    private Button style_missy;
    private Button style_campus;
    private Button style_vintage;
    private Button style_sexy;
    private Button style_school;
    private Button style_romantic;
    private Button style_office;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop);


           /* Shop의 상단탭
        */
        shop_tabLayout=(TabLayout)findViewById(R.id.shop_tab_layout);

        /* 뷰페이져 어댑터 객체를 생성하고,
         * 생성자를 통해서 프래그먼트 관리를 도와주는 FragmentManager와
         * 페이지의 개수를 탭의 개수와 맞춰주기 위해 Page Count를 받아온다.
         * 뷰페이저에 어댑터를 설정한다.
         * 그 후, tabLayout과 viewPager 연결
         */
        viewPager=(ViewPager)findViewById(R.id.shop_fragment_container);
        shopViewPagerAdapter=new ShopViewPagerAdapter(getSupportFragmentManager(),shop_tabLayout.getTabCount());
        viewPager.setAdapter(shopViewPagerAdapter);

        /*
         상단탭이 선택되었을 때, 상단탭의 선택된 현재 위치를 얻어 Fragment를 이동시킨다.
         */
        shop_tabLayout.addOnTabSelectedListener(new ShopTopItemSelectedListener());

        /* ViewPager의 페이지가 변경될 때 알려주는 리스너*/
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(shop_tabLayout));

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
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
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


    void open_panel(){

        /* 필터 PopUp창 띄우기 */
        PopupWindow mPopupWindow;
        View popupView = getLayoutInflater().inflate(R.layout.activity_filter_pop_up, null);
        mPopupWindow = new PopupWindow(popupView);
        mPopupWindow.setWindowLayoutMode(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        //팝업 터치 가능
        mPopupWindow.setTouchable(true);
        //팝업 외부 터치 가능(외부 터치시 나갈 수 있게)
        mPopupWindow.setOutsideTouchable(true);
        //외부터치 인식을 위한 추가 설정 : 미 설정시 외부는 null로 생각하고 터치 인식 X
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //애니메이션 활성화
        mPopupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        // PopUp 창 띄우기
        mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);

        seekBar=(SeekBar)popupView.findViewById(R.id.seekBar);
        age=(TextView)popupView.findViewById(R.id.shop_ranking_search_age);
        /* seekBar의 값이 설정되었을 때, textview를 설정해준다. */
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                number=seekBar.getProgress();
                   if(number==0){
                    age.setText("10대");
                }else if(number==1){
                    age.setText("20대 초반");
                }else if(number==2){
                    age.setText("20대 중반");
                } else if(number==3){
                    age.setText("20대 후반");
                } else if(number==4){
                    age.setText("30대 초반");
                }else if(number==5){
                    age.setText("30대 중반");
                }else if(number==6){
                    age.setText("30대 후반");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                number=seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                number=seekBar.getProgress();
            }
        });

        /* 필터 버튼 정보 전달, 버튼 색, 글자 색 변경 위한 변수들 */
        gender_male = (Button) popupView.findViewById(R.id.shop_filter_gender_male);
        gender_female = (Button) popupView.findViewById(R.id.shop_filter_gender_female);
        category_cloth = (Button) popupView.findViewById(R.id.shop_filter_category_cloth);
        category_acc = (Button) popupView.findViewById(R.id.shop_filter_category_acc);
        category_bag = (Button) popupView.findViewById(R.id.shop_filter_category_bag);
        category_shoes = (Button) popupView.findViewById(R.id.shop_filter_category_shoes);
        style_feminine = (Button) popupView.findViewById(R.id.shop_filter_style_feminine);
        style_modern = (Button) popupView.findViewById(R.id.shop_filter_style_modern);
        style_simple = (Button) popupView.findViewById(R.id.shop_filter_style_simple);
        style_lovely = (Button) popupView.findViewById(R.id.shop_filter_style_lovely);
        style_unique = (Button) popupView.findViewById(R.id.shop_filter_style_unique);
        style_missy = (Button) popupView.findViewById(R.id.shop_filter_style_missy);
        style_campus = (Button) popupView.findViewById(R.id.shop_filter_style_campus);
        style_vintage = (Button) popupView.findViewById(R.id.shop_filter_style_vintage);
        style_sexy = (Button) popupView.findViewById(R.id.shop_filter_style_sexy);
        style_school = (Button) popupView.findViewById(R.id.shop_filter_style_school);
        style_romantic = (Button) popupView.findViewById(R.id.shop_filter_style_romantic);
        style_office = (Button) popupView.findViewById(R.id.shop_filter_style_office);

        /* 버튼 클릭 시 색상, 글자색 변경, 정보 전달할 때 이용 */
        gender_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gender_male.isSelected()==false) {
                    gender_male.setSelected(true);
                    gender_male.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    gender_male.setSelected(false);
                    gender_male.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        gender_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gender_female.isSelected()==false) {
                    gender_female.setSelected(true);
                    gender_female.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    gender_female.setSelected(false);
                    gender_female.setTextColor(Color.parseColor("#000000"));
                }
            }

        });

        category_cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_cloth.isSelected()==false) {
                    category_cloth.setSelected(true);
                    category_cloth.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_cloth.setSelected(false);
                    category_cloth.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        category_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_acc.isSelected()==false) {
                    category_acc.setSelected(true);
                    category_acc.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_acc.setSelected(false);
                    category_acc.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        category_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_bag.isSelected()==false) {
                    category_bag.setSelected(true);
                    category_bag.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_bag.setSelected(false);
                    category_bag.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        category_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(category_shoes.isSelected()==false) {
                    category_shoes.setSelected(true);
                    category_shoes.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    category_shoes.setSelected(false);
                    category_shoes.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_feminine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_feminine.isSelected()==false) {
                    style_feminine.setSelected(true);
                    style_feminine.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_feminine.setSelected(false);
                    style_feminine.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_modern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_modern.isSelected()==false) {
                    style_modern.setSelected(true);
                    style_modern.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_modern.setSelected(false);
                    style_modern.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_simple.isSelected()==false) {
                    style_simple.setSelected(true);
                    style_simple.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_simple.setSelected(false);
                    style_simple.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_lovely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_lovely.isSelected()==false) {
                    style_lovely.setSelected(true);
                    style_lovely.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_lovely.setSelected(false);
                    style_lovely.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_unique.isSelected()==false) {
                    style_unique.setSelected(true);
                    style_unique.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_unique.setSelected(false);
                    style_unique.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_missy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_missy.isSelected()==false) {
                    style_missy.setSelected(true);
                    style_missy.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_missy.setSelected(false);
                    style_missy.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_campus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_campus.isSelected()==false) {
                    style_campus.setSelected(true);
                    style_campus.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_campus.setSelected(false);
                    style_campus.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_vintage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_vintage.isSelected()==false) {
                    style_vintage.setSelected(true);
                    style_vintage.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_vintage.setSelected(false);
                    style_vintage.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_sexy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_sexy.isSelected()==false) {
                    style_sexy.setSelected(true);
                    style_sexy.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_sexy.setSelected(false);
                    style_sexy.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_school.isSelected()==false) {
                    style_school.setSelected(true);
                    style_school.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_school.setSelected(false);
                    style_school.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_romantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_romantic.isSelected()==false) {
                    style_romantic.setSelected(true);
                    style_romantic.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_romantic.setSelected(false);
                    style_romantic.setTextColor(Color.parseColor("#000000"));
                }
            }
        });

        style_office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style_office.isSelected()==false) {
                    style_office.setSelected(true);
                    style_office.setTextColor(Color.parseColor("#ffffff"));
                } else{
                    style_office.setSelected(false);
                    style_office.setTextColor(Color.parseColor("#000000"));
                }
            }
        });



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
