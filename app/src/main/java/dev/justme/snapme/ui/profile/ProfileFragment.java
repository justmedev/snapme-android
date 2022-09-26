package dev.justme.snapme.ui.profile;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.time.Duration;

import dev.justme.snapme.R;
import dev.justme.snapme.databinding.FragmentProfileBinding;
import dev.justme.snapme.helpers.AsyncImageLoader;
import dev.justme.snapme.helpers.DataManager;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private DataManager dataManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dataManager = DataManager.getDataManager();

        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.profileUsername;
        textView.setText(dataManager.mySelf.name);

        new AsyncImageLoader(binding.profilePicture).execute("https://randomuser.me/api/portraits/women/13.jpg");
        binding.profilePicture.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return root;
    }

    @SuppressLint("ShowToast")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.birthdayContainer.setOnClickListener(v -> Snackbar.make(view.getContext(), view, String.format("You are %s years old!", dataManager.mySelf.age), Snackbar.LENGTH_SHORT).setBackgroundTint(Color.DKGRAY).setTextColor(Color.LTGRAY).show());
        binding.levelContainer.setOnClickListener(v -> Snackbar.make(view.getContext(), view, String.format("You have %s friends", dataManager.mySelf.friends), Snackbar.LENGTH_SHORT).setBackgroundTint(Color.DKGRAY).setTextColor(Color.LTGRAY).show());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}