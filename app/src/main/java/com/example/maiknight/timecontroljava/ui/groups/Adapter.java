package com.example.maiknight.timecontroljava.ui.groups;

import android.view.View;
import android.widget.TextView;

import com.example.maiknight.timecontroljava.Database.entities.Group;
import com.example.maiknight.timecontroljava.R;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends ReflectiveTypeAdapterFactory.Adapter {

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
            tvEnd= itemView.findViewById(R.id.tv_end);
        }
    }


    public interface OnOrderClickListener {
        void onOrderClickListener(Group group);
    }
}
