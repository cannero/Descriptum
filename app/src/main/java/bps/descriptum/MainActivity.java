package bps.descriptum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import bps.descriptum.perstistence.Day;
import bps.descriptum.utilities.InjectorUtil;
import bps.descriptum.viewmodel.DaysViewModel;
import bps.descriptum.viewmodel.DaysViewModelFactory;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private DaysViewModel mDaysViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaysViewModelFactory daysViewModelFactory = InjectorUtil.provideDaysViewModelFactory(this);
        mDaysViewModel = ViewModelProviders.of(this, daysViewModelFactory).get(DaysViewModel.class);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mDaysViewModel.insertOrUpdate(new Day(year,  month,  dayOfMonth));
    }
}
