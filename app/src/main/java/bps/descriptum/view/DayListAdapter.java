package bps.descriptum.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import bps.descriptum.R;
import bps.descriptum.perstistence.Day;

public class DayListAdapter extends ListAdapter<Day, DayListAdapter.DayListItemViewHolder> {
    class DayListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateTextView;

        private DayListItemViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.textview_day_item);
        }
    }

    public DayListAdapter() {
        super(DayListAdapter.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DayListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_day_item, parent, false);
        return new DayListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListItemViewHolder holder, int position) {
        Day current = getItem(position);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("EE MMM dd yyyy", Locale.getDefault());
        simpleDateFormat.setTimeZone(timeZone);
        holder.dateTextView.setText(simpleDateFormat.format(current.getDate()));
    }

    public static final DiffUtil.ItemCallback<Day> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Day>() {
                @Override
                public boolean areItemsTheSame(@NonNull Day oldDay, @NonNull Day newDay) {
                    return oldDay.getDate() == newDay.getDate();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Day oldDay, @NonNull Day newDay) {
                    return oldDay.equals(newDay);
                }
            };
}
