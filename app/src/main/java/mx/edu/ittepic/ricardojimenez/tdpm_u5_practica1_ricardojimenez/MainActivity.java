package mx.edu.ittepic.ricardojimenez.tdpm_u5_practica1_ricardojimenez;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SmsListener{

    private SmsReceiver smsReceiver;
    ListView mensajes;
    ArrayAdapter arrayAdapter;
    ArrayList<String> smsMessagesList = new ArrayList<String>();
    String destino,zodiaco;
    BaseDatos baseDatos;
    Thread espera;
    private BroadcastReceiver sendBroadcastReceiver;
    private BroadcastReceiver deliveryBroadcastReceiver;
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mensajes = findViewById(R.id.SMSList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessagesList);
        mensajes.setAdapter(arrayAdapter);
        baseDatos = new BaseDatos(this,"ZODIACOS",null,1);
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.RECEIVE_SMS"}, 22);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.SEND_SMS,Manifest.permission.READ_PHONE_STATE},
                0);

        espera = new Thread(){
            public void run(){
                while (true){
                    if(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.RECEIVE_SMS") == PackageManager.PERMISSION_GRANTED
                    ) {
                        smsReceiver = new SmsReceiver();
                        setRecievers();
                        break;
                    }

                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        espera.start();
    }

    private void enviarSMS(){
        try
        {

        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> enPartes = sms.divideMessage(zodiaco);
        sms.sendMultipartTextMessage(destino, null, enPartes, null, null);
            System.out.println("sadsdada  "+destino);
        }catch (Exception e){
            System.out.println(e.getMessage()+"  --jkjk");
        }
//        Log.i("Send SMS", "");
//        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//
//        smsIntent.setData(Uri.parse("smsto:"));
//        smsIntent.setType("vnd.android-dir/mms-sms");
//        smsIntent.putExtra("address"  , new String (destino));
//        smsIntent.putExtra("sms_body"  , zodiaco);
//
//        try {
//            startActivity(smsIntent);
//            finish();
//            Log.i("Finished sending SMS...", "");
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(MainActivity.this,
//                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
//        }
    }

    private void setRecievers(){
        registerReceiver(smsReceiver,new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
        smsReceiver.setEventoSMS(this);
    }
    @Override
    public void mensajeRecibido(Mensaje sms) {
        String smsMessage = sms.getOrigen()+"\n"+sms.getFecha().toString()+"\n"+sms.getCuerpo();
        destino = sms.getOrigen();
        String tmp[] = sms.getCuerpo().split(" ");
        if(tmp[0].equals("ZODIACO")){
            if(consultarZodiaco(tmp[1])){
                enviarSMS();
            }
        }
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();


    }

    private boolean consultarZodiaco(String idzodiaco){
        SQLiteDatabase sel = baseDatos.getReadableDatabase();
        idzodiaco = idzodiaco.toLowerCase();
        Cursor c = sel.rawQuery("SELECT * FROM ZODIACO WHERE idZodiaco = '" + idzodiaco+"'", null);
        if(c.moveToFirst()){
            zodiaco = idzodiaco.toUpperCase()+"\n"+c.getString(1);
            sel.close();
            return true;
        }
        sel.close();
        return false;
    }
    @Override
    protected void onDestroy()
    {
        unregisterReceiver(smsReceiver);
        super.onDestroy();
    }
}
