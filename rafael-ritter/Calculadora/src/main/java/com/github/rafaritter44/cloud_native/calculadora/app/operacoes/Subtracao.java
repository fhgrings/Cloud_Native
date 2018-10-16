package com.github.rafaritter44.cloud_native.calculadora.app.operacoes;

import java.util.Optional;

public class Subtracao implements Operacao {

    private double primeiroOperando;
    private double segundoOperando;

    public Subtracao(double primeiroOperando, double segundoOperando) {
        this.primeiroOperando = primeiroOperando;
        this.segundoOperando = segundoOperando;
    }

    public Optional<Double> calcular() {
        return Optional.ofNullable(primeiroOperando - segundoOperando);
    }

}