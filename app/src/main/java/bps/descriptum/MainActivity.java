package bps.descriptum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import bps.descriptum.persistence.Day;
import bps.descriptum.utilities.InjectorUtil;
import bps.descriptum.viewmodel.DayDetailViewModel;
import bps.descriptum.viewmodel.DayDetailViewModelFactory;
import bps.descriptum.viewmodel.DaysViewModel;
import bps.descriptum.viewmodel.DaysViewModelFactory;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private DaysViewModel mDaysViewModel;
    private DayDetailViewModel mDayDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
        DaysViewModelFactory daysViewModelFactory = InjectorUtil.provideDaysViewModelFactory(this);
        mDaysViewModel = ViewModelProviders.of(this, daysViewModelFactory).get(DaysViewModel.class);

        DayDetailViewModelFactory dayDetailViewModelFactory = InjectorUtil.provideDayDetailViewModelFactory(this);
        mDayDetailViewModel = ViewModelProviders.of(this, dayDetailViewModelFactory).get(DayDetailViewModel.class);

        setDaysFragment();
    }

    private void setDaysFragment() {
        DaysFragment daysFragment = new DaysFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, daysFragment);
        transaction.commit();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //mDaysViewModel.insertOrUpdate(new Day(year,  month,  dayOfMonth));
        mDayDetailViewModel.initDay(year, month, dayOfMonth);
        switchToDetailsFragment();
    }

    private void switchToDetailsFragment() {
        DayDetailFragment detailFragment = new DayDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
