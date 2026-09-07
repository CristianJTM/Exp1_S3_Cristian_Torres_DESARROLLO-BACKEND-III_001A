package com.bancoxyz.core.services;

import com.bancoxyz.core.dtos.InteresDTO;
import com.bancoxyz.core.entities.InteresEntity;
import com.bancoxyz.core.repositories.InteresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteresService {

    private final InteresRepository interesRepository;

    public InteresDTO buscarPorCuenta(Long cuentaId) {

        InteresEntity interes = interesRepository.findById(cuentaId)
                .orElse(null);

        if (interes == null) {
            return null;
        }

        return convertirADTO(interes);
    }

    private InteresDTO convertirADTO(InteresEntity interes) {

        InteresDTO dto = new InteresDTO();

        dto.setCuentaId(interes.getCuentaId());
        dto.setNombre(interes.getNombre());
        dto.setSaldo(interes.getSaldo());
        dto.setEdad(interes.getEdad());
        dto.setTipo(interes.getTipo());

        return dto;
    }
}