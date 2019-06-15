package bps.descriptum.utilities;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BindingAdapters {
    @BindingAdapter("app:convertDate")
    public static void bindConvertDate(TextView textView, Date date){
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("EE dd MMM yyyy", Locale.getDefault());
        simpleDateFormat.setTimeZone(timeZone);
        textView.setText(simpleDateFormat.format(date));
    }
}
