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
}
