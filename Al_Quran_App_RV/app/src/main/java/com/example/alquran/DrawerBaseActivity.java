package com.example.alquran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void setContentView(View view) {

        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);

        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_menu, R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(this, MainActivity.class));
                drawerLayout.closeDrawer(GravityCompat.START);
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, About.class));
                drawerLayout.closeDrawer(GravityCompat.START);
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_noor:
                Intent noor_intent = new Intent(this, MainActivity.class);

                noor_intent.putExtra("font_family",0);
                startActivity(noor_intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_jameel:
                Intent jameel_intent = new Intent(this, MainActivity.class);
                jameel_intent.putExtra("font_family",1);
                startActivity(jameel_intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                overridePendingTransition(0, 0);
                break;
            case R.id.nav_logout:
                finishAndRemoveTask();
                System.exit(1);
                drawerLayout.closeDrawer(GravityCompat.START);
                overridePendingTransition(0, 0);
                break;
        }

        return false;
    }

    protected void allocateActivityTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}