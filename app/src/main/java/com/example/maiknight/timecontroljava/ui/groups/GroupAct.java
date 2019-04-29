package com.example.maiknight.timecontroljava.ui.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maiknight.timecontroljava.Database.entities.Group;
import com.example.maiknight.timecontroljava.Interactors.Interactor;
import com.example.maiknight.timecontroljava.R;
import com.example.maiknight.timecontroljava.databinding.ActGroupsBinding;
import com.example.maiknight.timecontroljava.databinding.CardGroupBinding;

import java.util.List;

public class GroupAct extends AppCompatActivity {
    private ActGroupsBinding vBind;
    private Interactor interactor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vBind = DataBindingUtil.setContentView(this, R.layout.act_groups);
        interactor = Interactor.getInstance();
        interactor.getUserGroups((long) 1, this::setUpRecyclerView);
        interactor.getCurrentGroup((long) 1, this::seedCurrentGroup);
    }

    private void setUpRecyclerView(List<Group> groupList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        vBind.rvDayGroups.setLayoutManager(layoutManager);
        vBind.rvDayGroups.setItemAnimator(new DefaultItemAnimator());

        Adapter adapter = new Adapter(groupList, this, group -> {

        });
        vBind.rvDayGroups.setAdapter(adapter);
    }

    private void seedCurrentGroup(Group group) {
        View cGroup = LayoutInflater.from(this).inflate(
                R.layout.card_group,
                vBind.llCurrentGroup,
                false
        );
        CardGroupBinding vGroupBinding = CardGroupBinding.bind(cGroup);

        vGroupBinding.tvStart.setVisibility(View.VISIBLE);
        vGroupBinding.tvEnd.setVisibility(View.VISIBLE);

        if (group == null) {
            vGroupBinding.tvStart.setVisibility(View.INVISIBLE);
            vGroupBinding.tvEnd.setVisibility(View.INVISIBLE);
            vGroupBinding.tvGroup.setText("No hay grupo");

        } else {
            vGroupBinding.tvStart.setText(group.getStartHour());
            vGroupBinding.tvEnd.setText(group.getEndHour());
            vGroupBinding.tvGroup.setText(group.getName());
        }
        vBind.llCurrentGroup.addView(cGroup);
    }
}
