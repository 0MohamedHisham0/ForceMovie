package com.dal4.ForceMovie.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dal4.ForceMovie.Fragments.TopPopular_Frag;
import com.dal4.ForceMovie.Fragments.TopRated_Frag;
import com.dal4.ForceMovie.Fragments.UpComing_Frag;
import com.dal4.ForceMovie.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class FragmentContainer extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_BottomNav();

    }

    public void init_BottomNav() {
        bottomNavigation = findViewById(R.id.BottomNav);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_top_rated));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_up_coming));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_popular));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                String selected_Frag = null;
                switch (item.getId()) {
                    case 1:
                        selected_Frag = "TopRated";
                        break;
                    case 2:
                        selected_Frag = "UpComing";
                        break;
                    case 3:
                        selected_Frag = "TopPopular";
                        break;


                }
                Toast.makeText(FragmentContainer.this, selected_Frag + "", Toast.LENGTH_SHORT).show();

            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                androidx.fragment.app.Fragment selected_Frag = null;
                switch (item.getId()) {
                    case 1:
                        selected_Frag = new TopRated_Frag();
                        break;
                    case 2:
                        selected_Frag = new UpComing_Frag();
                        break;
                    case 3:
                        selected_Frag = new TopPopular_Frag();
                        break;


                }
                try {
                    getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, selected_Frag).commit();

                } catch (IllegalStateException illegalStateException) {
                    Toast.makeText(FragmentContainer.this, illegalStateException.getMessage() + "", Toast.LENGTH_SHORT).show();
                }

            }
        });


        SelectBottomNav();

    }

    public void SelectBottomNav() {
        bottomNavigation.show(1, false);
        getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, new TopRated_Frag()).commit();

    }

}
