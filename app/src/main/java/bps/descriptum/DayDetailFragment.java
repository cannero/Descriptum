package bps.descriptum;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import bps.descriptum.databinding.FragmentDayDetailBinding;
import bps.descriptum.utilities.InjectorUtil;
import bps.descriptum.viewmodel.DayDetailViewModel;
import bps.descriptum.viewmodel.DayDetailViewModelFactory;

/**
 * A detail view for a Day
 */
public class DayDetailFragment extends Fragment {
    private DayDetailViewModel mDayDetailViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDayDetailBinding binding = FragmentDayDetailBinding.inflate(inflater, container, false);
        DayDetailViewModelFactory dayDetailViewModelFactory =
                InjectorUtil.provideDayDetailViewModelFactory(getActivity());
        mDayDetailViewModel = ViewModelProviders.of(getActivity(), dayDetailViewModelFactory)
                .get(DayDetailViewModel.class);
        binding.setViewModel(mDayDetailViewModel);
        binding.setLifecycleOwner(this);
        binding.fab.setOnClickListener(view ->
                Snackbar.make(view, R.string.day_added, Snackbar.LENGTH_LONG).show());
        return binding.getRoot();
    }
}
