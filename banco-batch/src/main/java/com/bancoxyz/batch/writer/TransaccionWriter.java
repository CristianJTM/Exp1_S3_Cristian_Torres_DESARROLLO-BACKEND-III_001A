package com.bancoxyz.batch.writer;

import com.bancoxyz.batch.config.BatchDataConfig;
import com.bancoxyz.batch.model.TransaccionProcesada;
import com.bancoxyz.batch.repository.TransaccionProcesadaRepository;


import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class TransaccionWriter
        implements ItemWriter<BatchDataConfig.TransaccionProcesada> {

    private final TransaccionProcesadaRepository transaccionProcesadaRepository;

    public TransaccionWriter(
            TransaccionProcesadaRepository transaccionProcesadaRepository) {

        this.transaccionProcesadaRepository = transaccionProcesadaRepository;
    }

    @Override
    public void write(
            Chunk<? extends BatchDataConfig.TransaccionProcesada> chunk) {

        for (BatchDataConfig.TransaccionProcesada item : chunk.getItems()) {

            TransaccionProcesada transaccionProcesada = new TransaccionProcesada();

            transaccionProcesada.setId(item.id());
            transaccionProcesada.setFecha(item.fecha());
            transaccionProcesada.setMonto(item.monto());
            transaccionProcesada.setTipo(item.tipo());
            transaccionProcesada.setObservacion(item.observacion());

            transaccionProcesadaRepository.save(transaccionProcesada);
        }
    }
}
