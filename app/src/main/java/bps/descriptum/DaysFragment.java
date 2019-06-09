package bps.descriptum;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import bps.descriptum.databinding.FragmentDaysBinding;
import bps.descriptum.utilities.InjectorUtil;
import bps.descriptum.view.DatePickerDialogFragment;
import bps.descriptum.view.DayListAdapter;
import bps.descriptum.viewmodel.DaysViewModel;
import bps.descriptum.viewmodel.DaysViewModelFactory;

public class DaysFragment extends Fragment {
    private DaysViewModel mDaysViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDaysBinding binding = FragmentDaysBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        RecyclerView daysView = getView().findViewById(R.id.daysview);
        final DayListAdapter adapter = new DayListAdapter();
        daysView.setAdapter(adapter);

        DaysViewModelFactory daysViewModelFactory = InjectorUtil.provideDaysViewModelFactory(context);
        mDaysViewModel = ViewModelProviders.of(this, daysViewModelFactory).get(DaysViewModel.class);
        mDaysViewModel.getAllDays().observe(this, days -> adapter.submitList(days));

        FloatingActionButton floatingActionButton = getView().findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialogFragment datePicker = new DatePickerDialogFragment();
                datePicker.show(getFragmentManager(), "addDayDatePicker");
            }
        });
    }
}
