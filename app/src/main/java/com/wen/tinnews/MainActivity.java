package com.wen.tinnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wen.tinnews.R;


public class MainActivity extends AppCompatActivity {

    private NavController uniNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        uniNavController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, uniNavController);
        NavigationUI.setupActionBarWithNavController(this, uniNavController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        return uniNavController.navigateUp();
        }


}