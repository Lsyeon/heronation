package com.example.android.drawer_heronation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    Button btn1;
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override   //시작될때 설정들
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //다음에 사용할때 버튼 정의해주고 이 밑에 코드들 사용하면됩니다
        btn1 = (Button) findViewById(R.id.btn_alarm);   //알람버튼 정의
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment1 fragment1 = new Fragment1();
                transaction.replace(R.id.frame, fragment1); //프레임 교체해라
                transaction.commit();
                drawerLayout.closeDrawers();
            }
        });
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
}
