package bps.descriptum;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import bps.descriptum.databinding.FragmentDayDetailBinding;
import bps.descriptum.persistence.Day;
import bps.descriptum.persistence.HourAndMinute;
import bps.descriptum.utilities.InjectorUtil;
import bps.descriptum.view.TimePickerDialogFragment;
import bps.descriptum.viewmodel.DayDetailViewModel;
import bps.descriptum.viewmodel.DayDetailViewModelFactory;

/**
 * A detail view for a Day
 */
public class DayDetailFragment extends Fragment
        implements TimePickerDialogFragment.OnTimeSetListener{

    private DayDetailViewModel mDayDetailViewModel;
    private View.OnClickListener setWokeUpTimeClicked = (v -> {
        Day currentDay = mDayDetailViewModel.getDay().getValue();
        HourAndMinute timeWokeUp = currentDay.getTimeWokeUp();
        TimePickerDialogFragment timePickerDialogFragment = TimePickerDialogFragment
                .newInstance(timeWokeUp.getHour(), timeWokeUp.getMinute());
        timePickerDialogFragment.setTargetFragment(DayDetailFragment.this, 0);
        timePickerDialogFragment.show(getFragmentManager(), "setTimeWokeUp");
    });

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
        binding.setClickListenerWokeUp(setWokeUpTimeClicked);
        binding.fab.setOnClickListener(view ->
                Snackbar.make(view, R.string.day_added, Snackbar.LENGTH_LONG).show());
        return binding.getRoot();
    }

    @Override
    public void onTimeSet(int hour, int minute) {
        Day currentDay = mDayDetailViewModel.getDay().getValue();
        currentDay.setWokeUpTime(hour, minute);
    }
}
