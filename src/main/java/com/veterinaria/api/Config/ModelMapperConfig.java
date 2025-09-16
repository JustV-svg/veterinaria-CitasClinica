package com.veterinaria.api.Config;

import com.veterinaria.api.DTOs.HistorialDTO;
import com.veterinaria.api.Entidades.Historial;
import com.veterinaria.api.Repositorios.CitaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
        @Bean
        public ModelMapper modelMapper(CitaRepositorio citaRepository) {
            ModelMapper mapper = new ModelMapper();

            // DTO → Entidad
            mapper.createTypeMap(HistorialDTO.class, Historial.class)
                    .setPostConverter(context -> {
                        HistorialDTO source = context.getSource();
                        Historial destino = context.getDestination();

                        if (source.getCitaId() != null) {
                            destino.setCita(citaRepository.findById(source.getCitaId())
                                    .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada")));
                        }
                        return destino;
                    });

            // Entidad → DTO
            mapper.createTypeMap(Historial.class, HistorialDTO.class)
                    .addMappings(m -> m.map(src -> src.getCita().getId(), HistorialDTO::setCitaId));

            return mapper;
        }
}
