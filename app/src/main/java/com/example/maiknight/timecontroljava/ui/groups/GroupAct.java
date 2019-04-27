package com.example.maiknight.timecontroljava.ui.groups;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.maiknight.timecontroljava.R;
import com.example.maiknight.timecontroljava.databinding.ActGroupsBinding;

public class GroupAct extends AppCompatActivity {
    private ActGroupsBinding vBind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vBind = DataBindingUtil.setContentView(this, R.layout.act_groups);

    }
}
