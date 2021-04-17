package com.unicocoder.bottomnavigation.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenuItemView;
import com.unicocoder.bottomnavigation.OnBackPressedListner;
import com.unicocoder.bottomnavigation.R;
import com.unicocoder.bottomnavigation.databinding.ActivityMainBinding;
import com.unicocoder.bottomnavigation.view.fragment.HomeFragment;
import com.unicocoder.bottomnavigation.view.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    NavHostFragment navHost;

    @SuppressLint({"NonConstantResourceId", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        appBarConfiguration = new AppBarConfiguration.Builder(R.navigation.nav_graph).build();

        //Initialize NavController.
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);

        navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        assert navHost != null;
        navController = navHost.getNavController();

        binding.bottomNavView.setSelectedItemId(R.id.navigation_home); // Selected Item Navigation

        binding.bottomNavView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (navController.getCurrentDestination().getLabel().equals("FavoriteFragment")) {
                        navController.navigate(R.id.action_favoriteFragment_to_homeFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("ProfileFragment")) {
                        navController.navigate(R.id.action_profileFragment_to_homeFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("SearchFragment")) {
                        navController.navigate(R.id.action_searchFragment_to_homeFragment);
                    }
                    return true;

                case R.id.navigation_search:
                    if (navController.getCurrentDestination().getLabel().equals("FavoriteFragment")) {
                        navController.navigate(R.id.action_favoriteFragment_to_searchFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("ProfileFragment")) {
                        navController.navigate(R.id.action_profileFragment_to_searchFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("HomeFragment")) {
                        navController.navigate(R.id.action_homeFragment_to_searchFragment);
                    }
                    return true;

                case R.id.navigation_favourites:
                    if (navController.getCurrentDestination().getLabel().equals("ProfileFragment")) {
                        navController.navigate(R.id.action_profileFragment_to_favoriteFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("SearchFragment")) {
                        navController.navigate(R.id.action_searchFragment_to_favoriteFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("HomeFragment")) {
                        navController.navigate(R.id.action_homeFragment_to_favoriteFragment);
                    }
                    return true;

                case R.id.navigation_profile:
                    if (navController.getCurrentDestination().getLabel().equals("FavoriteFragment")) {
                        navController.navigate(R.id.action_favoriteFragment_to_profileFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("SearchFragment")) {
                        navController.navigate(R.id.action_searchFragment_to_profileFragment);
                    } else if (navController.getCurrentDestination().getLabel().equals("HomeFragment")) {
                        navController.navigate(R.id.action_homeFragment_to_profileFragment);
                    }
                    return true;
            }
            return false;
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        final Fragment currentFragment = navHost.getChildFragmentManager().getFragments().get(0);
        navController = Navigation.findNavController(this, R.id.fragment);
        if (currentFragment instanceof OnBackPressedListner)
            ((OnBackPressedListner) currentFragment).onBackPressed();
        else if (!navController.popBackStack())
            finish();

    }
}