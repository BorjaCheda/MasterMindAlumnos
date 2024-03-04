package com.example.app.model;

import lombok.Data;

import java.util.ArrayList;
@Data
public class MasterMind {
    private String numeroSecreto;
    private ArrayList<Intento> listaIntentos;
    private EstadoJuego estadoJuego;
    private Integer numeroVecesIntentadas =0;
    public MasterMind() {
        listaIntentos = new ArrayList<>();
    }

    public String getNumeroSecreto() {
        return numeroSecreto;
    }

    public void setNumeroSecreto(String numeroSecreto) {
        this.numeroSecreto = numeroSecreto;
    }

    public ArrayList<Intento> getListaIntentos() {
        return listaIntentos;
    }

    public void setListaIntentos(ArrayList<Intento> listaIntentos) {
        this.listaIntentos = listaIntentos;
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public Integer getNumeroVecesIntentadas() {
        return numeroVecesIntentadas++;
    }

    public void setNumeroVecesIntentadas(Integer numeroVecesIntentadas) {
        this.numeroVecesIntentadas = numeroVecesIntentadas;
    }

}
