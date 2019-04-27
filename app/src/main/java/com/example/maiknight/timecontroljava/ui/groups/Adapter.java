package com.example.maiknight.timecontroljava.ui.groups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maiknight.timecontroljava.Database.entities.Group;
import com.example.maiknight.timecontroljava.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Group> groups;
    private Context context;
    private OnGroupCLickLIstener onGroupCLickLIstener;

    public Adapter(List<Group> groups, Context context, OnGroupCLickLIstener onGroupCLickLIstener) {
        this.groups = groups;
        this.context = context;
        this.onGroupCLickLIstener = onGroupCLickLIstener;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_group,
                parent,
                false
        );
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = groups.get(position);

        holder.tvGroup.setText(group.getName());
        holder.tvStart.setText(group.getStartHour());
        holder.tvEnd.setText(group.getEndHour());
        if (onGroupCLickLIstener != null) {
            holder.itemRoot.setOnClickListener(
                    view -> onGroupCLickLIstener.onGroupCLickLIstener(group)
            );
        }
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView itemRoot;
        private TextView tvGroup;
        private TextView tvStart;
        private TextView tvEnd;

        public ViewHolder(View itemView) {
            super(itemView);
            itemRoot = itemView.findViewById(R.id.item_root);
            tvGroup = itemView.findViewById(R.id.tv_group);
            tvStart = itemView.findViewById(R.id.tv_start);
            tvEnd = itemView.findViewById(R.id.tv_end);
        }
    }


    public interface OnGroupCLickLIstener {
        void onGroupCLickLIstener(Group group);
    }
}
