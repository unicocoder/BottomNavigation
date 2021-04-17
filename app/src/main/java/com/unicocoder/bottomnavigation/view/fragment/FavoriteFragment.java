package com.unicocoder.bottomnavigation.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.unicocoder.bottomnavigation.OnBackPressedListner;
import com.unicocoder.bottomnavigation.R;
import com.unicocoder.bottomnavigation.databinding.FavoriteFragmentBinding;
import com.unicocoder.bottomnavigation.view.activity.MainActivity;
import com.unicocoder.bottomnavigation.viewModel.FavoriteViewModel;
import com.unicocoder.bottomnavigation.viewModel.HomeViewModel;

public class FavoriteFragment extends Fragment implements OnBackPressedListner {

    private FavoriteViewModel mViewModel;

    private FavoriteFragmentBinding binding;

    private boolean doubleBackToExitPressedOnce = false;

    private int counter = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(FavoriteViewModel.class);

        mViewModel.getLiveDataCounter().observe(getViewLifecycleOwner(), integer -> {
            counter = integer;
            binding.tvFavoriteFragment.setText("FAVORITE " + counter);
        });

        binding.btFavoriteFragmentAdd.setOnClickListener(v -> {
            counter++;
            mViewModel.setLiveDataCounter(counter);
        });
    }


    @Override
    public boolean onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            doubleBackToExitPressedOnce = true;
            Toast.makeText(getActivity(), "جهت خروج دوباره فشار دهید", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            return true;
        } else {
            getActivity().finish();
            return false;
        }
    }

}