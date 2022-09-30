package dev.justme.snapme;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.util.concurrent.Executors;

import dev.justme.snapme.databinding.ActivityMainBinding;
import dev.justme.snapme.helpers.DataManager;
import dev.justme.snapme.helpers.HttpClient;
import dev.justme.snapme.helpers.Profile;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                HttpClient httpClient = new HttpClient();
                Profile profile = httpClient.getAndConvertToObject("http://192.168.4.48:8080/profile?uuid=9735bd33-a9a4-4abb-8014-3ba9705a3141", Profile.class);
                Log.d("SNAPME", profile.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DataManager dataManager = DataManager.getDataManager();
        dataManager.mySelf.name = "test";
        dataManager.mySelf.age = 14;
        dataManager.mySelf.friends = 3;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_settings, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}