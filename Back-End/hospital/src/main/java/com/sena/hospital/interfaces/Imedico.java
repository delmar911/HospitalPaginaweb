package com.sena.hospital.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.sena.hospital.model.medico;

public interface Imedico extends CrudRepository<medico,String> {

}
