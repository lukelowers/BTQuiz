package com.example.luke.btquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import java.io.IOException;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.util.UUID;




public class Quiz extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    Intent discoverableIntent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // enable discoverability
        discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    }

    //server device
    private class AcceptThread extends Thread {

        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {

            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // uuid is the app's UUID string, also used by the client code
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("BTQuiz", uuid);
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    manageConnectedSocket(socket);
                    try {
                        mmServerSocket.close();
                    } catch (IOException e) {
                        Log.e("ERROR", "Was not in API example. " +
                                "Added catch block bc of syntax error -> " +
                                "unhandled exception: java.io.ioexception");
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        //TODO: call cancel when done with BluetoothSocket to clean up
        // Will cancel the listening socket, and cause the thread to finish
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }



}
