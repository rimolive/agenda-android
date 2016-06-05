package br.com.oralura.openshift.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.oralura.openshift.agenda.R;
import br.com.oralura.openshift.agenda.dao.AlunoDAO;

/**
 * Created by rmartine on 6/4/16.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");
        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);
        if(dao.ehAluno(telefone)) {
            Toast.makeText(context, "Chegou um SMS de aluno!", Toast.LENGTH_SHORT).show();
            MediaPlayer m = MediaPlayer.create(context, R.raw.msg);
        }
    }
}
