package bps.descriptum.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import bps.descriptum.R;
import bps.descriptum.perstistence.Day;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.DayListItemViewHolder> {
    class DayListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateTextView;

        private DayListItemViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.textview_day_item);
        }
    }

    private final LayoutInflater mInflater;
    private List<Day> mDays;

    public DayListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DayListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_day_item, parent, false);
        return new DayListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListItemViewHolder holder, int position) {
        if (mDays != null) {
            Day current = mDays.get(position);
            TimeZone timeZone = TimeZone.getTimeZone("UTC");
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("EE MMM dd yyyy", Locale.getDefault());
            simpleDateFormat.setTimeZone(timeZone);
            holder.dateTextView.setText(simpleDateFormat.format(current.getDate()));
        } else {
            holder.dateTextView.setText(R.string.no_days);
        }
    }

    @Override
    public int getItemCount() {
        if (mDays != null) {
            return mDays.size();
        }
        return 0;
    }

    public void setDays(List<Day> days) {
        mDays = days;
        notifyDataSetChanged();
    }
}
