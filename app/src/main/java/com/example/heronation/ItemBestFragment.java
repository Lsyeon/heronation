package com.example.heronation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ItemBestFragment extends Fragment {
    private RecyclerView category_recyclerView;
    private ItemBestCategoryAdapter itemBestCategoryAdapter;
    private ArrayList<ItemBestCategory> list=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* 카테고리 리스트에 아이템 추가
        *  여기서 카테고리 이름이나, 이미지 변경하면 됨
        */
        addItem(getResources().getDrawable(R.drawable.ic_item_all),"전체");
        addItem(getResources().getDrawable(R.drawable.ic_all_tshirts),"상의");
        addItem(getResources().getDrawable(R.drawable.ic_all_pants),"하의");
        addItem(getResources().getDrawable(R.drawable.ic_all_outer),"아우터");
        addItem(getResources().getDrawable(R.drawable.ic_all_onepiece),"원피스");
        addItem(getResources().getDrawable(R.drawable.ic_all_skirt),"스커트");
        addItem(getResources().getDrawable(R.drawable.ic_all_shoes),"슈즈");
        addItem(getResources().getDrawable(R.drawable.ic_all_bag),"가방");
        addItem(getResources().getDrawable(R.drawable.ic_all_acc),"액세서리");
        addItem(getResources().getDrawable(R.drawable.ic_all_socks),"패션소품");

        // Inflate the layout for this fragment
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_item_best,container,false);
        category_recyclerView=(RecyclerView)rootView.findViewById(R.id.item_best_item_category);

        /* 리사이클러뷰 객체 생성 */
        itemBestCategoryAdapter=new ItemBestCategoryAdapter(getActivity(),list);
        /* 레이아웃 매니저 수평으로 지정 */
        category_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
       /* 리사이클러뷰에 어댑터 지정 */
        category_recyclerView.setAdapter(itemBestCategoryAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }

    public void addItem(Drawable icon,String name){
        ItemBestCategory item=new ItemBestCategory(icon,name);
        list.add(item);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
