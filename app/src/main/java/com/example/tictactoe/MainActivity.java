package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listeners();
    }
    void init(){
        nav=findViewById(R.id.menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Computer()).commit();
        sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.Total), 0);
        editor.apply();
        editor.putInt(getString(R.string.Win), 0);
        editor.apply();
        editor.putInt(getString(R.string.Lose), 0);
        editor.apply();
    }
    private void listeners(){
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment selectedFragment=null;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.computer:
                        selectedFragment=new Computer();
                        break;
                    case R.id.friend:
                        selectedFragment=new Friends();
                        break;
                    case R.id.stats:
                        selectedFragment=new Stats();
                        break;

                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container,selectedFragment)
                        .commit();
                return true;
            }
        });
    }
}