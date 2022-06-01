package com.example.asimov;

// import android.os.Bundle;
// import android.view.LayoutInflater;
// import android.view.View;
// import android.view.ViewGroup;

// import androidx.annotation.NonNull;
// import androidx.fragment.app.Fragment;
// import androidx.navigation.fragment.NavHostFragment;

// import com.example.asimov.databinding.ActivityMainBinding;
// import com.example.asimov.R;

// public class MainActivity extends Fragment {
//     private ActivityMainBinding binding;

//     @Override
//     public View onCreateView(
//             LayoutInflater inflater, ViewGroup container,
//             Bundle savedInstanceState
//     ) {

//         binding = ActivityMainBinding.inflate(inflater, container, false);
//         return binding.getRoot();

//     }

//     public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//         super.onViewCreated(view, savedInstanceState);

//         binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 NavHostFragment.findNavController(MainActivity.this)
//                         .navigate(R.id.action_MainActivity_to_LoginFragment);
//             }
//         });
//     }

//     @Override
//     public void onDestroyView() {
//         super.onDestroyView();
//         binding = null;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView sideMenu;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userType = getIntent().getStringExtra("userType");
        drawerLayout = findViewById(R.id.drawerLayout);
        sideMenu = findViewById(R.id.side_menu);
        sideMenu.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.sidemenu_open, R.string.sidemenu_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            sideMenu.setCheckedItem(R.id.nav_dashboard);
        }
        if (userType.equals("docente")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardTeacherActivity()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardDirectorActivity()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardDirectorActivity()).commit();
                break;
            case R.id.nav_dashboardTeacher:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardTeacherActivity()).commit();
                break;
            case R.id.nav_announcements:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnnouncementsActivity()).commit();
                break;
            case R.id.nav_teachers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TeacherProfile()).commit();
                break;
            case R.id.nav_competences:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CompetencesActivity()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}