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
import android.widget.ListView;
import android.widget.Toast;

public class FacultyConnect extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter mBluetoothAdapter;
    ListView mListView;
    ArrayAdapter<String> mArrayAdapter;
    Set<BluetoothDevice> pairedDevices;
    IntentFilter filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_connect);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mListView = (ListView) findViewById(R.id.listOfDevices);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        // Enable Bluetooth
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        list();

        find();

        mListView.setAdapter(mArrayAdapter);

    } // end onCreate




    public void list(){

        // get paired devices
        pairedDevices = mBluetoothAdapter.getBondedDevices();

        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    public void find(){

        if (mBluetoothAdapter.isDiscovering()) {
            Toast.makeText(getApplicationContext(),"Bluetooth is discovering devices",
                    Toast.LENGTH_LONG).show();
            mBluetoothAdapter.cancelDiscovery();
        }
        else {
            mArrayAdapter.clear();
            mBluetoothAdapter.startDiscovery();
            // Register the BroadcastReceiver
            //TODO: Don't forget to unregister during onDestroy
            registerReceiver(mReceiver, filter);
            //TODO: Once device is connected call mBluetoothAdapter.cancelDiscovery()
        }
    }

    // Create a BroadcastReceiver for ACTION_FOUND
    final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                mArrayAdapter.notifyDataSetChanged();
            }
        }
    };


}

