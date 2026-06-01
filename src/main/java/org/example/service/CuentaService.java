package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.Cuenta;
import org.example.model.CuentaAhorro;
import org.example.model.Movimiento;
import org.example.repository.CuentaRepository;
import org.example.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional
    public void realizarDeposito(Integer cuentaId, Double monto) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada" +  cuentaId));

        cuenta.depositar(monto);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento(cuenta, monto, "DEPOSITO", java.time.LocalDateTime.now());
        movimientoRepository.save(movimiento);

        System.out.println("Deposito realizado com sucesso en la base de datos");
    }

    @Transactional
    public void realizarRetiro(Integer cuentaId, Double monto) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con ID: " + cuentaId));

        // Tu método retirar ya tiene las validaciones de saldo y sobregiro
        cuenta.retirar(monto);

        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento(cuenta, monto, "RETIRO", java.time.LocalDateTime.now());
        movimientoRepository.save(movimiento);

        System.out.println("Retiro registrado correctamente en la base de datos.");
    }

    @Transactional
    public void realizarTransferencia(Integer cuentaOrigenId, Integer cuentaDestinoId, Double monto) {
        // 1. Buscamos ambas cuentas en la base de datos
        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de origen no encontrada con ID: " + cuentaOrigenId));

        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta de destino no encontrada con ID: " + cuentaDestinoId));

        // 2. Usamos el método que ya programaste en tu clase Cuenta
        cuentaOrigen.transferir(cuentaDestino, monto);

        // 3. Guardamos los nuevos saldos de ambas cuentas
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        // 4. Registramos los movimientos para el historial (uno de salida y uno de entrada)
        Movimiento movSalida = new Movimiento(cuentaOrigen, monto, "TRANSFERENCIA_ENVIADA", java.time.LocalDateTime.now());
        Movimiento movEntrada = new Movimiento(cuentaDestino, monto, "TRANSFERENCIA_RECIBIDA", java.time.LocalDateTime.now());

        movimientoRepository.save(movSalida);
        movimientoRepository.save(movEntrada);

        System.out.println("Transferencia de S/ " + monto + " registrada correctamente en la BD.");
    }
}
