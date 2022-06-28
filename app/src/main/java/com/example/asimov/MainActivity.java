package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;

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
        if (userType.equals("profesor")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardTeacherActivity()).addToBackStack(null).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardDirectorActivity()).addToBackStack(null).commit();
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardDirectorActivity()).addToBackStack(null).commit();
                break;
            case R.id.nav_dashboardTeacher:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardTeacherActivity()).addToBackStack(null).commit();
                break;
            case R.id.nav_announcements:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnnouncementsActivity()).addToBackStack(null).commit();
                break;
            case R.id.nav_teachers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TeacherProfile()).addToBackStack(null).commit();
                break;
            case R.id.nav_competences:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CourseInformationActivity()).commit();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CompetencesActivity())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.nav_courses:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CoursesList()).addToBackStack(null).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}