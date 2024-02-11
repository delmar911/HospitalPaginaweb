package com.sena.hospitalWeb.interfaces;

import org.springframework.data.repository.CrudRepository;
import com.sena.hospitalWeb.model.paciente;


public interface Ipaciente extends CrudRepository<paciente,String> {

}
