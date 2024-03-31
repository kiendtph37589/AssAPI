package com.kiendtph37589.assigmentapi.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.kiendtph37589.assigmentapi.R;

import java.util.List;

public class HomeFrag extends Fragment {
//    ViewPager2 viewPager2;
    RecyclerView recycler_home;
    ViewFlipper v_flipper;
    private Handler handler = new Handler();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
//        viewPager2 = view.findViewById(R.id.view_paper2);
        recycler_home = view.findViewById(R.id.recyclerView);

        //RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recycler_home.setLayoutManager(layoutManager);




        //slide
        int images[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
        v_flipper = view.findViewById(R.id.v_flipper);

        // for loop
        //but i frefer foreach
        for (int image : images){
            flipperImages(image);
        }
    return view;
    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
    }
}
