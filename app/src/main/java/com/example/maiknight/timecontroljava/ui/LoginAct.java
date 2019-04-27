package com.example.maiknight.timecontroljava.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.maiknight.timecontroljava.R;
import com.example.maiknight.timecontroljava.databinding.ActLoginBinding;
import com.example.maiknight.timecontroljava.ui.groups.GroupAct;


public class LoginAct extends AppCompatActivity {
    private ActLoginBinding vBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_login);
        vBind = DataBindingUtil.setContentView(this, R.layout.act_login);
        vBind.btnLogin.setOnClickListener(V -> {
            startActivity(new Intent(this, GroupAct.class));
            finish();
        });

    }
}
