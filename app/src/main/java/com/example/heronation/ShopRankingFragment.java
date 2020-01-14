package com.example.heronation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;


public class ShopRankingFragment extends Fragment {
    private RecyclerView shop_recyclerView;
    private ArrayList<Shop> shop_list=new ArrayList<>();
    private ImageButton filter_button;
    private Button search_button;

    /* 배너 슬라이딩을 위한 변수 */
    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Shop 목록을 생성함 */
        this.make_shop_list();

        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_shop_ranking, container,false);
        shop_recyclerView=(RecyclerView)rootView.findViewById(R.id.recycler_view_shop_ranking);


        /* 리사이클러뷰 객체 생성 */
        ShopRecyclerViewAdapter shopRecyclerViewAdapter=new ShopRecyclerViewAdapter(getActivity(),shop_list);
        /* 레이아웃 매니저 수평으로 지정 */
        shop_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        /* 리사이클러뷰에 어댑터 지정 */
        shop_recyclerView.setAdapter(shopRecyclerViewAdapter);

        /* 필터 버튼
        *  필터 버튼을 눌렀을 때, 팝업창을 띄어줌
        */
        filter_button=(ImageButton)rootView.findViewById(R.id.shop_ranking_filter);
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ((ShopActivity)getActivity()).open_panel();
            }
        });

        /* 검색 버튼
        *  검색 버튼을 눌렀을 때 Shop 검색 Activity로 이동*/
        search_button=(Button)rootView.findViewById(R.id.shop_ranking_search);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ShopRankingSearchActivity.class);
                startActivity(intent);
            }
        });

        /* 이미지 슬라이딩을 위해 뷰페이저를 이용했고, 이를 설정해주는 이미지 어댑터를 설정하여 슬라이딩 구현 */
        viewPager=(ViewPager)rootView.findViewById(R.id.image_view_shop_ranking);
        imageAdapter=new ImageAdapter(getActivity());
        viewPager.setAdapter(imageAdapter);

        return rootView;
    }

    public void make_shop_list(){
        /* Shop 목록을 생성함 */

        shop_list.add(new Shop("1","크림치즈마켓","#20대 #심플베이직 #러블리",
                "data:image/jpeg;base64,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",
                "data:image/jpeg;base64,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",
                "data:image/jpeg;base64,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"));

        shop_list.add(new Shop("2","기프티박스","#10대 #20대 #심플베이직 #페미닌",
                "https://shop-phinf.pstatic.net/20191125_109/1574647089099J0mEe_JPEG/12007827730061169_1986553615.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191218_109/1576635660713BY1b0_JPEG/13997203139003091_948360031.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191217_209/1576546329655vHwlr_JPEG/13907068283633976_1742837673.jpg?type=f260"));

        shop_list.add(new Shop("3","슬로우베리","#20대 #페미닌 #러블리",
                "https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fscontent.cdninstagram.com%2Fv%2Ft51.2885-15%2Fsh0.08%2Fe35%2Fs640x640%2F79378202_260690368242068_8567282746947102671_n.jpg%3F_nc_ht%3Dscontent.cdninstagram.com%26_nc_ohc%3DZoVtD8ke2sAAX-FPnlo%26oh%3D42f1d4055d2b254a8b4943f424e032cd%26oe%3D5E947627%22&twidth=353&theight=353&opts=12",
                "https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fscontent.cdninstagram.com%2Fv%2Ft51.2885-15%2Fsh0.08%2Fe35%2Fs640x640%2F72231290_2685527241536547_5781413796061254177_n.jpg%3F_nc_ht%3Dscontent.cdninstagram.com%26_nc_ohc%3D-jjghygC95YAX8k-s4G%26oh%3Dd6d0641caa41c87017d3de6fffd8c0ff%26oe%3D5E8D703C%22&twidth=353&theight=353&opts=12",
                "https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fscontent.cdninstagram.com%2Fv%2Ft51.2885-15%2Fsh0.08%2Fe35%2Fs640x640%2F79981404_585579498666535_1326040980112499335_n.jpg%3F_nc_ht%3Dscontent.cdninstagram.com%26_nc_ohc%3DWeUgSvZAwrUAX-uhnLM%26oh%3D5f0e9cc45ae817f461b9ca1983d61ec8%26oe%3D5EAA85DD%22&twidth=353&theight=353&opts=12"));

        shop_list.add(new Shop("4","코튼로그","#20대 #심플베이직 #러블리",
                "https://shop-phinf.pstatic.net/20191125_109/1574647089099J0mEe_JPEG/12007827730061169_1986553615.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191218_109/1576635660713BY1b0_JPEG/13997203139003091_948360031.jpg?type=f260",
                "https://shop-phinf.pstatic.net/20191217_209/1576546329655vHwlr_JPEG/13907068283633976_1742837673.jpg?type=f260"));

        shop_list.add(new Shop("5","핫핑","#20대 #심플베이직 #러블리",
                "https://creamcheese.co.kr/web/product/extra/big/20200103/a6f044e55e57a52499d86d8d52fbbe97.jpg",
                "https://creamcheese.co.kr/web/product/extra/big/201910/771a37dd6951ee991d401d58000999d6.jpeg",
                "https://creamcheese.co.kr/web/product/extra/big/201908/4bb1ddaaaacbbc33aef005355888877a.jpeg"));


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    /* Acitivity와 Fragment가 통신할 때, OnFragmentInteractionListener를 사용함.
     * 프래그먼트에서 액티비티로 통신(데이터 주고 받는 것)이 있을 수도 있기 때문에
     * MainActivity 에서 이를 implement한 후 오버라이딩 시켜줄 것이다. (임시로)
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
