package com.sena.hospitalWeb.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.sena.hospitalWeb.model.medico;


public interface Imedico extends CrudRepository<medico,String> {

}