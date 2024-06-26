package com.sena.hospital2.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.hospital2.model.paciente;


@Service
public interface IpacienteService {
	public String save (paciente paciente);
	public List<paciente>findAll();
	public List<paciente> filtroPaciente(String filtro);
	public List<paciente> filtroCedulaPaciente(String numero_documento);
	public Optional<paciente> findOne(String id);
	public int delete(String id);

}
