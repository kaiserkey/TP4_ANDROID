package com.example.tp4_android.ui.llamada;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<Boolean> llamadaExitosa = new MutableLiveData<>();

    public void setContext(Context context) {
        this.context = context;
    }

    public MutableLiveData<Boolean> getLlamadaExitosa() {
        return llamadaExitosa;
    }

    public void llamar(String numero) {
        // Verifica que el contexto no sea nulo por si las dudas
        if (context == null) {
            llamadaExitosa.setValue(false);
            return;
        }

        // Verifica que el número no esté vacío =)
        if (numero == null || numero.trim().isEmpty()) {
            llamadaExitosa.setValue(false);
            return;
        }

        // Intent para realizar la llamada
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + numero));
        try {
            context.startActivity(intent);
            llamadaExitosa.setValue(true);
        } catch (SecurityException e) {
            llamadaExitosa.setValue(false);
            Toast.makeText(context, "No se pudo realizar la llamada", Toast.LENGTH_LONG).show();
        }
    }

    public void errorLlamada(Boolean exitosa) {
        if (exitosa){
            Toast.makeText(context, "Llamada Exitosa", Toast.LENGTH_LONG).show();
            return;
        }else {
            Toast.makeText(context, "Llamada Fallida, Numero Incorrecto", Toast.LENGTH_LONG).show();
            return;
        }
    }
}
