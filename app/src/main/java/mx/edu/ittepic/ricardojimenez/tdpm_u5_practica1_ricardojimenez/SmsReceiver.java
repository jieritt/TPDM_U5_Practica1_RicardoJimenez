package mx.edu.ittepic.ricardojimenez.tdpm_u5_practica1_ricardojimenez;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    private SmsListener eventoSMS;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] sms = null;
            String origen;
            if(bundle!=null){
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    sms = new SmsMessage[pdus.length];
                    for (int i = 0; i < sms.length; i++){
                        String formato = myBundle.getString("format");
                        sms[i] = SmsMessage.createFromPdu(((byte[])pdus[i]),formato);
                        origen = sms[i].getOriginatingAddress();
                        String cuerpo = sms[i].getMessageBody();
                        Long hora = sms[i].getTimestampMillis();
                        Date fecha = new Date(hora);
                        eventoSMS.mensajeRecibido(new Mensaje(origen,fecha,cuerpo));
                    }
                }catch (Exception e){}
            }
        }
    }

    public void setEventoSMS(SmsListener smsListener){
        eventoSMS = smsListener;
    }

}
