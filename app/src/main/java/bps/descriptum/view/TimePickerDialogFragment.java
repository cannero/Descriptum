package bps.descriptum.view;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

public class TimePickerDialogFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public interface OnTimeSetListener {
        void onTimeSet(int hour, int minute);
    }

    private static final String HOUR_KEY = "hour";
    private static final String MINUTE_KEY = "minute";

    public static TimePickerDialogFragment newInstance(int hour, int minute){
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        Bundle args = new Bundle();
        args.putInt(HOUR_KEY, hour);
        args.putInt(MINUTE_KEY, minute);
        timePickerDialogFragment.setArguments(args);
        return timePickerDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        int hour = getArguments().getInt(HOUR_KEY);
        int minute = getArguments().getInt(MINUTE_KEY);

        return new TimePickerDialog(getContext(), this, hour, minute,
                false);
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        OnTimeSetListener listener = (OnTimeSetListener) getTargetFragment();
        listener.onTimeSet(hour, minute);
    }
}
