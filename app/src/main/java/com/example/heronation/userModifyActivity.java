package com.example.heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class userModifyActivity extends AppCompatActivity {
    /* 상단 메뉴 버튼을 눌렀을 때 뜨는 레이아웃을 위한 변수들 */
    private DrawerLayout drawerLayout;
    private View drawerView;

    /* 회원정보 수정에 있는 생일 선택을 위한 변수들*/
    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* 상태표시바를 삭제해주는 작업 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_modify);

        this.InitializeView();
        this.InitializeListener();

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
    /* 회원정보 edit버튼을 눌렀을때 다시 Mypage Activity 이동
     * Item 버튼을  눌렀을 때, Item Activity 이동
     * Shop 버튼을 눌렀을 때, Shop Activity 이동
     * 측정 버튼을 눌렀을 때, Measurement Activity 이동
     * 찜 버튼을 눌렀을 때, Wishlist Activity 이동
     * 마이 페이지 버튼을 눌렀을 때, Mypage Activity 이동
     */
    public void Click_userModify_edit_btn(View view){
        Intent intent=new Intent(this, myPageConnectingActivity.class);
        startActivity(intent);
        Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_LONG).show();
    }

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
    public void Click_mypageButton(View view){
        Intent intent=new Intent(this,MypageActivity.class);
        startActivity(intent);
    }

    /*날짜정보를 TextView에 표시하기 위해서 findViewByld()를 통해 화면에 표시되는 TextView의 참조객체를 얻어오는 함수*/
    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    /* OnDateSetListener를 구현하는 함수 */
    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
            }
        };
    }

    /*이벤트 발생에 대해 처리를 담당*/
    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2019, 5, 24);

        dialog.show();
    }
}