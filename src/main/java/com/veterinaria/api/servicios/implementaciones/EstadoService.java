package com.veterinaria.api.servicios.implementaciones;

import com.veterinaria.api.Repositorios.IEstadoRepository;
import com.veterinaria.api.dtos.estado.EstadoGuardar;
import com.veterinaria.api.dtos.estado.EstadoModificar;
import com.veterinaria.api.dtos.estado.EstadoSalida;
import com.veterinaria.api.modelos.Estado;
import com.veterinaria.api.servicios.interfaces.IEstadoService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService implements IEstadoService{

    private static final Logger log = LoggerFactory.getLogger(EstadoService.class);
    @Autowired
    private IEstadoRepository estadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EstadoSalida> obtenerTodos() {
        List<Estado> estados = estadoRepository.findAll();
        return estados.stream()
                .map(estado -> modelMapper.map(estado,EstadoSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<EstadoSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Estado> page = estadoRepository.findAll(pageable);

        List<EstadoSalida> estadoDto = page.stream()
                .map(estado -> modelMapper.map(estado, EstadoSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(estadoDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public EstadoSalida obtenerPorId(Integer id) {
        return modelMapper.map(estadoRepository.findById(id).get(), EstadoSalida.class);
    }

    @Override
    public EstadoSalida crear(EstadoGuardar estadoGuardar) {
        Estado estado =estadoRepository.save(modelMapper.map(estadoGuardar, Estado.class));
        return modelMapper.map(estado, EstadoSalida.class);
    }

    @Override
    public EstadoSalida editar(EstadoModificar estadoModificar) {
        Estado estado =estadoRepository.save(modelMapper.map(estadoModificar, Estado.class));
        return modelMapper.map(estado, EstadoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        estadoRepository.deleteById(id);

    }
}





