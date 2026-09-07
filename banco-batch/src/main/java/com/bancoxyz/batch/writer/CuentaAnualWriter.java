package com.bancoxyz.batch.writer;

import com.bancoxyz.batch.config.BatchDataConfig.CuentaAnualProcesada;
import com.bancoxyz.batch.model.Cuenta;
import com.bancoxyz.batch.model.CuentaAnual;
import com.bancoxyz.batch.model.Transaccion;
import com.bancoxyz.batch.repository.CuentaRepository;
import com.bancoxyz.batch.repository.EstadoCuentaRepository;
import com.bancoxyz.batch.repository.TransaccionCuentaRepository;

import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CuentaAnualWriter
        implements ItemWriter<CuentaAnualProcesada> {

    private final EstadoCuentaRepository estadoCuentaRepository;
    private final CuentaRepository cuentaRepository;
    private final TransaccionCuentaRepository transaccionCuentaRepository;

    public CuentaAnualWriter(
            EstadoCuentaRepository estadoCuentaRepository,
            CuentaRepository cuentaRepository,
            TransaccionCuentaRepository transaccionCuentaRepository) {

        this.estadoCuentaRepository =
                estadoCuentaRepository;

        this.cuentaRepository =
                cuentaRepository;

        this.transaccionCuentaRepository =
                transaccionCuentaRepository;
    }

    @Override
    public void write(
            Chunk<? extends CuentaAnualProcesada> chunk) {

        for (CuentaAnualProcesada item : chunk.getItems()) {

            // ====================================================
            // 1. CREAR CUENTA SI NO EXISTE
            // ====================================================

            if (!cuentaRepository.existsById(item.cuentaId())) {

                Cuenta cuenta = new Cuenta();

                cuenta.setCuentaId(
                        item.cuentaId()
                );

                /*
                 * El archivo cuentas_anuales.csv no proporciona
                 * el tipo de cuenta.
                 *
                 * Por lo tanto, no inventamos este dato.
                 */
                cuenta.setTipo(null);

                cuentaRepository.save(cuenta);
            }

            // ====================================================
            // 2. GUARDAR TRANSACCIÓN INDIVIDUAL
            // ====================================================

            Transaccion transaccion =
                    new Transaccion();

            transaccion.setCuentaId(
                    item.cuentaId()
            );

            transaccion.setFecha(
                    item.fecha()
            );

            transaccion.setMonto(
                    item.monto()
            );

            transaccion.setTipo(
                    item.tipo()
            );

            transaccionCuentaRepository.save(
                    transaccion
            );

            // ====================================================
            // 3. ACTUALIZAR ESTADO ANUAL CONSOLIDADO
            // ====================================================

            List<CuentaAnual> existentes =
                    estadoCuentaRepository.findByCuentaIdAndAnio(
                            item.cuentaId(),
                            item.anio()
                    );

            CuentaAnual cuentaAnual;

            if (!existentes.isEmpty()) {

                cuentaAnual = existentes.get(0);

                BigDecimal totalDepositos =
                        cuentaAnual.getTotalDepositos() != null
                                ? cuentaAnual.getTotalDepositos()
                                : BigDecimal.ZERO;

                BigDecimal totalRetiros =
                        cuentaAnual.getTotalRetiros() != null
                                ? cuentaAnual.getTotalRetiros()
                                : BigDecimal.ZERO;

                BigDecimal saldoMovimiento =
                        cuentaAnual.getSaldoMovimiento() != null
                                ? cuentaAnual.getSaldoMovimiento()
                                : BigDecimal.ZERO;

                Integer cantidadOperaciones =
                        cuentaAnual.getCantidadOperaciones() != null
                                ? cuentaAnual.getCantidadOperaciones()
                                : 0;

                cuentaAnual.setTotalDepositos(
                        totalDepositos.add(
                                item.totalDepositos()
                        )
                );

                cuentaAnual.setTotalRetiros(
                        totalRetiros.add(
                                item.totalRetiros()
                        )
                );

                cuentaAnual.setSaldoMovimiento(
                        saldoMovimiento.add(
                                item.saldoMovimiento()
                        )
                );

                cuentaAnual.setCantidadOperaciones(
                        cantidadOperaciones
                                + item.cantidadOperaciones()
                );

                cuentaAnual.setObservacion(
                        "Estado de cuenta anual consolidado"
                );

            } else {

                cuentaAnual =
                        new CuentaAnual();

                cuentaAnual.setCuentaId(
                        item.cuentaId()
                );

                cuentaAnual.setAnio(
                        item.anio()
                );

                cuentaAnual.setTotalDepositos(
                        item.totalDepositos()
                );

                cuentaAnual.setTotalRetiros(
                        item.totalRetiros()
                );

                cuentaAnual.setSaldoMovimiento(
                        item.saldoMovimiento()
                );

                cuentaAnual.setCantidadOperaciones(
                        item.cantidadOperaciones()
                );

                cuentaAnual.setObservacion(
                        "Estado de cuenta anual consolidado"
                );
            }

            estadoCuentaRepository.save(
                    cuentaAnual
            );
        }
    }
}