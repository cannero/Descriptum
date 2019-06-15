package bps.descriptum.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import bps.descriptum.databinding.RecyclerviewDayItemBinding;
import bps.descriptum.persistence.Day;

public class DayListAdapter extends ListAdapter<Day, DayListAdapter.DayListItemViewHolder> {
    class DayListItemViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerviewDayItemBinding binding;

        private DayListItemViewHolder(RecyclerviewDayItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Day item){
            binding.setDay(item);
            binding.executePendingBindings();
        }
    }

    public DayListAdapter() {
        super(DayListAdapter.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DayListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayListItemViewHolder(RecyclerviewDayItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DayListItemViewHolder holder, int position) {
        Day current = getItem(position);
        holder.bind(current);
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
