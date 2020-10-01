package com.adcreations.distancealert;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import java.util.ArrayList;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView statusTextView;
    Vibrator v;

    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private int androidVersion;
    Button searchButton,offbut;
    ArrayList<String>bluetoothdevices=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    BluetoothAdapter bluetoothAdapter;
    private final BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.i("Action",action);

if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
{
    statusTextView.setText("Analysing Threat");
    searchButton.setEnabled(true);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            bluetoothdevices.clear();
            searchButton.performClick();

        }
    }, 3000);


}
else  if(BluetoothDevice.ACTION_FOUND.equals(action))
{
    BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

    String name=device.getName();
    String Address=device.getAddress();
    String rssi=Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE));
    int a =Math.abs( Integer.parseInt(rssi));
  double accuracy = (double) Math.pow(10,(double)(a-69)/(10*2));

    if(a<58.5 && accuracy<0.9)
    {
        String dis=String.valueOf(accuracy);
        String title="ALERT FROM!";
        String subject="You are "+dis+"m away from threat Please maintain social distancing";
        String body="WHO Guidelines";
        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder(getApplicationContext()).setContentTitle(title).setContentText(body).setContentTitle(subject).setSound(alarmSound).setSmallIcon(R.drawable.ic_launcher_foreground).build();
       notify.flags |=Notification.FLAG_AUTO_CANCEL;
       notif.notify(0,notify);

    }
    Log.i("Device Found","Name:"+name+"Address:"+Address+"RSSI:"+rssi+"Distance:"+accuracy);
    String deviceString=" ";
    if(name==null || name.equals(""))
    {
        deviceString=Address + "- RSSI" + rssi + "dBm"+"Distance:"+accuracy;

    }
    else
    {
        deviceString=name + "- RSSI" + rssi + "dBm"+"Distance:"+accuracy;;
    }
    if(!bluetoothdevices.contains(name))
    {
        bluetoothdevices.add(deviceString);

    }
    arrayAdapter.notifyDataSetChanged();
}
        }
    };
    public void searchClicked(View view)
    {
        statusTextView.setText("Analysing Threat");
        searchButton.setEnabled(false);
        searchButton.setAlpha(0f);
        bluetoothdevices.clear();
        bluetoothAdapter.startDiscovery();
    }
   
    private void requestPermissions()
    {
        androidVersion = Build.VERSION.SDK_INT;
        if (androidVersion >= 23){
            int REQUEST_ID=0;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                    }, REQUEST_ID);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        statusTextView=findViewById(R.id.statustextview);
         requestPermissions();
        searchButton=findViewById(R.id.searchbutton);
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,bluetoothdevices);
        listView.setAdapter(arrayAdapter);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        IntentFilter  intentFilter=new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
       intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver,intentFilter);


    }


}