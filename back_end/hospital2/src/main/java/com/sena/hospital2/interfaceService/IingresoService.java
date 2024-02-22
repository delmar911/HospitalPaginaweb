package com.sena.hospital2.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.hospital2.model.ingreso;


@Service
public interface IingresoService {
	public String save (ingreso ingreso);
	public List<ingreso>findAll();
	public Optional<ingreso> findOne(String id);
	public int delete(String id);
}
