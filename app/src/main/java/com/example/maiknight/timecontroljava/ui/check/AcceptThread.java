package com.example.maiknight.timecontroljava.ui.check;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

class AcceptThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private final BluetoothAdapter mBluetoothAdapter;

    AcceptThread(BluetoothDevice device, BluetoothAdapter mBluetoothAdapter) {
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
        BluetoothSocket tmp = null;
        this.mBluetoothAdapter = mBluetoothAdapter;
        mmDevice = device;


        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

            tmp = device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        } catch (IOException e) {
        }
        mmSocket = tmp;
    }

    public void run() {
        // Cancel discovery because it will slow down the connection
        mBluetoothAdapter.cancelDiscovery();

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) {
            }
            return;
        }

        // Do work to manage the connection (in a separate thread)
        ConnectedThread connectedThread = new ConnectedThread(mmSocket);
        String op = "1";
        byte[] msgBuffer = op.getBytes();
        connectedThread.write(msgBuffer);
        connectedThread.run();
    }

    /**
     * Will cancel an in-progress connection, and close the socket
     */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
        }
    }
}