package es.upm.grise.prof.curso2025.integrador1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestCuentaBancaria {

    @Test
    public void testCuentaBancaria1() {
        Cliente cliente = Mockito.mock(Cliente.class);
        Operacion operacion = Mockito.mock(Operacion.class);

        CuentaBancaria cuentaBancaria = new CuentaBancaria
                ( "uno", 1, false);
        List<CuentaBancaria> cuentaBancariaList = new ArrayList<>();
        cuentaBancariaList.add(cuentaBancaria);
        when(cliente.getCuentasDescubierto()).thenReturn(cuentaBancariaList);

        when(operacion.getId()).thenReturn(1L);
        when(operacion.getImporte()).thenReturn(100d);

        assertEquals(1, cuentaBancaria.getSaldoActual());
        cuentaBancaria.addOperacion(operacion);
        assertEquals(101, cuentaBancaria.getSaldoActual());
    }

    @Test
    public void testCuentaBancaria2() {

        CuentaBancaria cuentaBancaria = new CuentaBancaria
                ( "uno", 1, false);
        OperacionNulaException exception = assertThrows(
                        OperacionNulaException.class,
                () -> cuentaBancaria.addOperacion(null),
                        "");
    }

    @Test
    public void testCuentaBancaria3() {

        CuentaBancaria cuentaBancaria = new CuentaBancaria
                ( "uno", 1, false);

        Operacion operacion = Mockito.mock(Operacion.class);
        when(operacion.getId()).thenReturn(1L);
        when(operacion.getImporte()).thenReturn(100d);
        cuentaBancaria.addOperacion(operacion);

        OperacionDuplicadaException exception = assertThrows(
                OperacionDuplicadaException.class,
                () -> cuentaBancaria.addOperacion(operacion),
                "");
    }

    @Test
    public void testCuentaBancaria4() {
        Cliente cliente = Mockito.mock(Cliente.class);
        Operacion operacion = Mockito.mock(Operacion.class);

        CuentaBancaria cuentaBancaria = new CuentaBancaria
                ( "uno", 1, false);
        List<CuentaBancaria> cuentaBancariaList = new ArrayList<>();
        cuentaBancariaList.add(cuentaBancaria);
        when(cliente.getCuentasDescubierto()).thenReturn(cuentaBancariaList);

        when(operacion.getId()).thenReturn(1L);
        when(operacion.getImporte()).thenReturn(-100d);

        assertEquals(1, cuentaBancaria.getSaldoActual());
        cuentaBancaria.addOperacion(operacion);

        saldoNegativoException exception = assertThrows(
                saldoNegativoException.class,
                () -> cuentaBancaria.getSaldoActual(),
                "");
    }
}
