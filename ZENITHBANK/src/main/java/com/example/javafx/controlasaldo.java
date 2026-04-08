package com.example.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class controlasaldo {
    private static controlasaldo instance;

    private final DoubleProperty saldo = new SimpleDoubleProperty(0.0);

    private controlasaldo() {}

    public static controlasaldo getInstance() {
        if (instance == null) {
            instance = new controlasaldo();
        }
        return instance;
    }

    public DoubleProperty saldoProperty() {
        return saldo;
    }

    public double getSaldo() {
        return saldo.get();
    }

    public void setSaldo(double nuevoSaldo) {
        this.saldo.set(nuevoSaldo);
    }

    public void depositar(double cantidad) {
        setSaldo(getSaldo() + cantidad);
    }

    public void retirar(double cantidad) {
        if (getSaldo() >= cantidad) {
            setSaldo(getSaldo() - cantidad);
        }
    }
}