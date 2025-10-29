package es.upm.grise.prof.curso2025.integrador1;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {

    String numeroCuenta;
    double saldoInicial;
    boolean admiteDescubierto;
    List<Operacion> operaciones;

    //
    // CUERPO DEL EXAMEN
    //

    public CuentaBancaria(String numeroCuenta, double saldoInicial, boolean admiteDescubierto) {
        this.numeroCuenta = numeroCuenta;
        this.saldoInicial = saldoInicial;
        this.operaciones = new ArrayList<>();
        //no especificado:
        this.admiteDescubierto = admiteDescubierto;
    }

    public void addOperacion(Operacion operacion) {
        if (operacion == null) throw new OperacionNulaException();
        for (Operacion op : operaciones){
            if (op.getId() == operacion.getId()) throw new OperacionDuplicadaException();
        }
        this.operaciones.add(operacion);
    }

    public double getSaldoActual() {
        double res = saldoInicial;
        for (Operacion op : operaciones){
            res+= op.getImporte();
        }
        if (!admiteDescubierto && res < 0) throw new saldoNegativoException();
        return Math.round(res * 100d)/100d;
    }

}
