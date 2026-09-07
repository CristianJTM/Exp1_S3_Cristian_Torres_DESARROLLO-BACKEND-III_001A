package com.bancoxyz.core.services;

import com.bancoxyz.core.dtos.TransaccionDTO;
import com.bancoxyz.core.model.Transaccion;
import com.bancoxyz.core.exceptions.TransaccionNoEncontradaException;
import com.bancoxyz.core.repositories.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionDTO buscarPorId(Long id) {

        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() ->
                        new TransaccionNoEncontradaException(
                                "No se encontró la transacción con ID: " + id
                        )
                );

        return convertirADTO(transaccion);
    }

    public List<TransaccionDTO> buscarPorCuenta(Long cuentaId) {

        return transaccionRepository.findByCuentaId(cuentaId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private TransaccionDTO convertirADTO(Transaccion transaccion) {

        TransaccionDTO dto = new TransaccionDTO();

        dto.setId(transaccion.getId());
        dto.setCuentaId(transaccion.getCuentaId());
        dto.setFecha(transaccion.getFecha());
        dto.setMonto(transaccion.getMonto());
        dto.setTipo(transaccion.getTipo());

        return dto;
    }
}