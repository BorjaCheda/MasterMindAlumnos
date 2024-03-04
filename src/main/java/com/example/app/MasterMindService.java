package com.example.app;

import java.util.Random;

import com.example.app.model.FormInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.app.model.EstadoJuego;
import com.example.app.model.Intento;
import com.example.app.model.MasterMind;

@Service
@Scope("session")
public class MasterMindService {

    public MasterMind masterMind;

    public void nuevoJuego(FormInfo formInfo) {
        masterMind = new MasterMind();
        do {
            masterMind.setNumeroSecreto(cadenaAlAzar(Integer.parseInt(formInfo.getTAM_NUMERO())));
        } while (cadenaConDuplicados(masterMind.getNumeroSecreto()));
        masterMind.getListaIntentos().clear();
        masterMind.setEstadoJuego(EstadoJuego.JUGANDO);
        System.out.println("=====> Num secreto: " + masterMind.getNumeroSecreto()); // solo para testing
    }

    public void comprobarIntento(String intento, FormInfo formInfo) {
        if (masterMind.getEstadoJuego() != EstadoJuego.JUGANDO)
            return;
        if (cadenaConDuplicados(intento) || intento.length() != Integer.parseInt(formInfo.getTAM_NUMERO()))
            return;

        int bienColocados = 0, malColocados = 0;
        String numeroSecreto = masterMind.getNumeroSecreto();
        for (int cont = 0; cont < intento.length(); cont++) {
            char letra = intento.charAt(cont);
            if (letra == numeroSecreto.charAt(cont))
                bienColocados++;
            else if (numeroSecreto.indexOf(letra) != -1)
                malColocados++;
        }
        Integer numeroVecesIntentadas = masterMind.getNumeroVecesIntentadas();
        masterMind.getListaIntentos().add(new Intento(intento, bienColocados, malColocados));
        if (bienColocados == Integer.parseInt(formInfo.getTAM_NUMERO()))
            masterMind.setEstadoJuego(EstadoJuego.GANO);
        if (bienColocados != Integer.parseInt(formInfo.getTAM_NUMERO()) && (Integer.parseInt(formInfo.getMAX_INTENTOS()) < numeroVecesIntentadas))
            masterMind.setEstadoJuego(EstadoJuego.PERDIO);
    }

    public boolean cadenaConDuplicados(String cad) {
        for (int i = 0; i < cad.length(); i++) {
            char c = cad.charAt(i);
            if (cad.indexOf(c, i + 1) != -1)
                return true;
        }
        return false;
    }

    public String cadenaAlAzar(int tamanho) {
        Random random = new Random();
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            int x = random.nextInt(10);
            cad.append(x);
        }
        return cad.toString();
    }
}
