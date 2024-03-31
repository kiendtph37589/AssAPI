package com.kiendtph37589.assigmentapi;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kiendtph37589.assigmentapi.Fragment.CartFrag;
import com.kiendtph37589.assigmentapi.Fragment.HomeFrag;
import com.kiendtph37589.assigmentapi.Fragment.LoveFrag;
import com.kiendtph37589.assigmentapi.Fragment.OrderFrag;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.Frag_layout);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        //Frag mac dinh
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFrag home_frag = new HomeFrag();
        fragmentManager.beginTransaction()
                .replace(R.id.Frag_layout, home_frag)
                .commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                if (menuItem.getItemId() == R.id.action_home){
                    fragment = new HomeFrag();
                }else  if(menuItem.getItemId() == R.id.action_cart){
                    fragment = new CartFrag();
                } else if (menuItem.getItemId() == R.id.action_order) {
                    fragment = new OrderFrag();
                }else if (menuItem.getItemId()==R.id.action_myfavorite){
                    fragment = new LoveFrag();
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Frag_layout,fragment)
                        .commit();
                return true;
            }
        });
    }
}