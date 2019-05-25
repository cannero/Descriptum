package bps.descriptum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import bps.descriptum.utilities.InjectorUtil;
import bps.descriptum.view.DayListAdapter;
import bps.descriptum.viewmodel.DaysViewModel;
import bps.descriptum.viewmodel.DaysViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private DaysViewModel mDaysViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView daysView = findViewById(R.id.daysview);
        final DayListAdapter adapter = new DayListAdapter(this);
        daysView.setAdapter(adapter);
        daysView.setLayoutManager(new LinearLayoutManager(this));

        DaysViewModelFactory daysViewModelFactory = InjectorUtil.provideDaysViewModelFactory(this);
        mDaysViewModel = ViewModelProviders.of(this, daysViewModelFactory).get(DaysViewModel.class);
        mDaysViewModel.getAllDays().observe(this, days -> adapter.setDays(days));
    }
}
