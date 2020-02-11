package com.example.urbandictonaryapp.view.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.urbandictonaryapp.R;

public class MainActivity extends AppCompatActivity {
    private NavHostFragment navHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    }

    public void navigate(int id){
        NavHostFragment.findNavController(navHost).navigate(id);
    }
}
