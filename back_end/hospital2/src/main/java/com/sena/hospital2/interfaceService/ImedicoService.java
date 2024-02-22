package com.sena.hospital2.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.hospital2.model.medico;

@Service
public interface ImedicoService {
	public String save (medico medico);
	public List<medico>findAll();
	public Optional<medico> findOne(String id);
	public int delete(String id);
}
