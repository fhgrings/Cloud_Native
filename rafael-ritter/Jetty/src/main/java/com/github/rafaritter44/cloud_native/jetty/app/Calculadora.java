package com.github.rafaritter44.cloud_native.jetty.app;

import com.github.rafaritter44.cloud_native.jetty.app.operacoes.*;

import java.lang.reflect.Constructor;
import java.util.*;

public class Calculadora {

    private List<Operacao> operacoes;
    private Map<Character, Constructor> construtores;

    public Calculadora(){
        operacoes = new ArrayList<>();
        construtores = new HashMap<>();
        try {
            construtores.put('+', Adicao.class.getConstructor(double.class, double.class));
            construtores.put('-', Subtracao.class.getConstructor(double.class, double.class));
            construtores.put('*', Multiplicacao.class.getConstructor(double.class, double.class));
            construtores.put('/', Divisao.class.getConstructor(double.class, double.class));
            construtores.put('^', Potencia.class.getConstructor(double.class, double.class));
        } catch(NoSuchMethodException excecao) {
            excecao.printStackTrace();
        }
    }

    public Optional<Double> calcular(double primeiroOperando, char operador, double segundoOperando)
            throws ReflectiveOperationException {
        Operacao operacao = (Operacao) construtores.get(operador).newInstance(primeiroOperando, segundoOperando);
        operacoes.add(operacao);
        return operacao.calcular();
    }

    public List<Operacao> getHistorico() {
        return new ArrayList<>(operacoes);
    }

    public void limparHistorico() { operacoes = new ArrayList<>(); }

}
