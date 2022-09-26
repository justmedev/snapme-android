package dev.justme.snapme.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dev.justme.snapme.R;
import dev.justme.snapme.databinding.FragmentNotificationsBinding;
import dev.justme.snapme.databinding.FragmentProfileBinding;
import dev.justme.snapme.helpers.AsyncImageLoader;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.profileUsername;
        textView.setText("Username");

        new AsyncImageLoader((ImageView) binding.profilePicture).execute("https://randomuser.me/api/portraits/women/13.jpg");
        binding.profilePicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}