package bps.descriptum.utilities;

import android.content.Context;

import bps.descriptum.DayDataSource;
import bps.descriptum.persistence.DaysDatabase;
import bps.descriptum.persistence.LocalDayDataSource;
import bps.descriptum.rx.AppSchedulerProvider;
import bps.descriptum.viewmodel.DaysViewModelFactory;

//as in sunflower app
public class InjectorUtil {

    static DayDataSource provideDayDataSource(Context context){
        DaysDatabase database = DaysDatabase.getInstance(context);
        DayDataSource dayDataSource = new LocalDayDataSource(database.dayDao());
        return dayDataSource;
    }

    public static DaysViewModelFactory provideDaysViewModelFactory(Context context){
        DayDataSource dayDataSource = provideDayDataSource(context);
        return new DaysViewModelFactory(dayDataSource, AppSchedulerProvider.getInstance());
    }
}
