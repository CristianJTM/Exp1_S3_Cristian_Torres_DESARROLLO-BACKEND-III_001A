package com.bancoxyz.batch.writer;

import com.bancoxyz.batch.config.BatchDataConfig;
import com.bancoxyz.batch.model.InteresProcesado;
import com.bancoxyz.batch.repository.InteresProcesadoRepository;


import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class InteresWriter
        implements ItemWriter<BatchDataConfig.InteresProcesado> {

    private final InteresProcesadoRepository interesProcesadoRepository;

    public InteresWriter(
            InteresProcesadoRepository interesProcesadoRepository) {

        this.interesProcesadoRepository = interesProcesadoRepository;
    }

    @Override
    public void write(
            Chunk<? extends BatchDataConfig.InteresProcesado> chunk) {

        for (BatchDataConfig.InteresProcesado item : chunk.getItems()) {

            InteresProcesado interesProcesado = new InteresProcesado();

            interesProcesado.setCuentaId(item.cuentaId());
            interesProcesado.setNombre(item.nombre());
            interesProcesado.setSaldoInicial(item.saldoInicial());
            interesProcesado.setEdad(item.edad());
            interesProcesado.setTipo(item.tipo());
            interesProcesado.setTasa(item.tasa());
            interesProcesado.setInteres(item.interes());
            interesProcesado.setSaldoFinal(item.saldoFinal());

            interesProcesadoRepository.save(interesProcesado);
        }
    }
}
