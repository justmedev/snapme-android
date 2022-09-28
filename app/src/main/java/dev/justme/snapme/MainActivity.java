package dev.justme.snapme;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dev.justme.snapme.databinding.ActivityMainBinding;
import dev.justme.snapme.helpers.DataManager;
import dev.justme.snapme.helpers.HttpClient;
import dev.justme.snapme.helpers.TaskRunner;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskRunner taskRunner = new TaskRunner();
        HttpClient httpClient = new HttpClient();
        try {
            taskRunner.executeAsync((String)httpClient.get("http://192.168.4.48:8080/profiles/?uuid=9735bd33-a9a4-4abb-8014-3ba9705a3141"), (String data) -> {
                Log.d("SNAPME", data);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

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