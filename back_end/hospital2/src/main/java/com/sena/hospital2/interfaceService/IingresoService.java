package com.sena.hospital2.interfaceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.hospital2.model.ingreso;


@Service
public interface IingresoService {
	public String save (ingreso ingreso);
	public List<ingreso>findAll();
	public List<ingreso> filtroIngreso(String filtro);
	public List<ingreso> filtroEstado(String id_paciente);
	public List<ingreso> filtroCama(String cama, String habitacion);
	public List<ingreso> filtroFechaIngreso(LocalDateTime fecha_ingreso);
	public Optional<ingreso> findOne(String id);
	public int delete(String id);
}
