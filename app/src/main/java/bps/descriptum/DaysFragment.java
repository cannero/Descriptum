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

import bps.descriptum.utilities.InjectorUtil;
import bps.descriptum.view.DayListAdapter;
import bps.descriptum.viewmodel.DaysViewModel;
import bps.descriptum.viewmodel.DaysViewModelFactory;

public class DaysFragment extends Fragment {
    private DaysViewModel mDaysViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_days, container, false);
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        RecyclerView daysView = getView().findViewById(R.id.daysview);
        final DayListAdapter adapter = new DayListAdapter(context);
        daysView.setAdapter(adapter);
        daysView.setLayoutManager(new LinearLayoutManager(context));

        DaysViewModelFactory daysViewModelFactory = InjectorUtil.provideDaysViewModelFactory(context);
        mDaysViewModel = ViewModelProviders.of(this, daysViewModelFactory).get(DaysViewModel.class);
        mDaysViewModel.getAllDays().observe(this, days -> adapter.setDays(days));
    }
}
