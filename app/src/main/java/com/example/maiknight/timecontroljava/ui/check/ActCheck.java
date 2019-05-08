package com.example.maiknight.timecontroljava.ui.check;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.maiknight.timecontroljava.R;
import com.example.maiknight.timecontroljava.databinding.ActCheckBinding;

import java.util.Set;

public class ActCheck extends AppCompatActivity {
    public static final int ACTION_REQUEST_ENABLE = 101;
    public static final String BT_DEVICE_HC05 = "98:D3:31:F5:94:1D";

    private ActCheckBinding vBind;
    private boolean chekin = false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            // If there are paired devices
            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                AcceptThread acceptThread = null;
                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(BT_DEVICE_HC05);
                acceptThread = new AcceptThread(device, mBluetoothAdapter);
                acceptThread.run(this::onSuccessBt, this::printCheck);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vBind = DataBindingUtil.setContentView(this, R.layout.act_check);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, ACTION_REQUEST_ENABLE);
        }
        setUpSw();
    }

    private void onSuccessBt() {
        vBind.group.setVisibility(View.VISIBLE);
        vBind.pbLoading.setVisibility(View.GONE);
    }

    private void setUpSw() {
        vBind.swCheck.setOnCheckedChangeListener((v, status) -> chekin = status);
    }

    private void printCheck(String rfid) {
        if (chekin) {

        }
    }
}
