package com.example.maiknight.timecontroljava.ui.check;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.maiknight.timecontroljava.R;
import com.example.maiknight.timecontroljava.api.Codes;
import com.example.maiknight.timecontroljava.databinding.ActCheckBinding;

import java.util.Arrays;
import java.util.Set;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ActCheck extends AppCompatActivity {
    public static final int ACTION_REQUEST_ENABLE = 101;
    public static final String BT_DEVICE_HC05 = "98:D3:31:F5:94:1D";
    public Handler mHandler;
    private ActCheckBinding vBind;
    private boolean chekin = false;
    private int count = 0;
    private StringBuilder dataRecibed = new StringBuilder();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            connectBtw();
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
        } else {
            connectBtw();
        }
    }

    @SuppressLint({"CheckResult", "HandlerLeak"})
    private void connectBtw() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            setHupHandler();
            AcceptThread acceptThread = null;
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(BT_DEVICE_HC05);
            acceptThread = new AcceptThread(device, mBluetoothAdapter, mHandler);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Espere mientras se configura ")
                    .setTitle("Espere");
            AlertDialog dialog = builder.create();
            dialog.show();

            AcceptThread finalAcceptThread = acceptThread;

            Single
                    .fromCallable(() -> finalAcceptThread.run(this::printCheck))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(success -> {
                        if (success) {
                            dialog.hide();
                            onSuccessBt();
                            setUpSw();
                        }
                    });
        }
    }


    private void onSuccessBt() {
        vBind.group.setVisibility(View.VISIBLE);
        vBind.pbLoading.setVisibility(View.GONE);
    }

    private void setUpSw() {
        vBind.swCheck.setOnCheckedChangeListener((v, status) -> chekin = status);
    }

    private void printCheck(String rfid) {
//        if (chekin) {
        if (rfid != null) {
            dataRecibed.append(rfid);
            if (dataRecibed.length() > 2) {
                String[] array = dataRecibed.toString().split("\\}");

                Log.e("CONTEO", String.valueOf(array.length));
                count++;
            }
        }
//        }
    }

    @SuppressLint("HandlerLeak")
    private void setHupHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == Codes.TRASNFER_DATA) {
                    printCheck((String) msg.obj);
                }
            }
        };
    }

}
