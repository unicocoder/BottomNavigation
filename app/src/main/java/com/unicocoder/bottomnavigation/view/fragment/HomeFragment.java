package com.unicocoder.bottomnavigation.view.fragment;

import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.unicocoder.bottomnavigation.OnBackPressedListner;
import com.unicocoder.bottomnavigation.R;
import com.unicocoder.bottomnavigation.databinding.HomeFragmentBinding;
import com.unicocoder.bottomnavigation.viewModel.HomeViewModel;

public class HomeFragment extends Fragment implements OnBackPressedListner {

    private HomeViewModel mViewModel;

    private HomeFragmentBinding binding;
    private boolean doubleBackToExitPressedOnce = false;
    private int counter = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        mViewModel.getLiveDataCounter().observe(getViewLifecycleOwner(), integer -> {
            counter = integer;
            binding.tvHomeFragment.setText("HOME " + counter);
        });

        binding.btHomeFragmentAdd.setOnClickListener(v -> {
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