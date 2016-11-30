package com.example.luke.btquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.View;
import android.bluetooth.BluetoothDevice;
import java.util.Set;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public class FacultyConnect extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;


    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    //TODO: add textViewResourceId as third parameter to show list of BT devices
    ArrayAdapter<CharSequence> mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

    // Register the BroadcastReceiver
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);


    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_connect);

        // Enable Bluetooth
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }

        // Register the BroadcastReceiver
        //TODO: Don't forget to unregister during onDestroy
        registerReceiver(mReceiver, filter);

        //TODO: Once device is connected call mBluetoothAdapter.cancelDiscovery()


    }




}
