package com.sena.hospital2.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sena.hospital2.model.ingreso;

@Repository

public interface Iingreso extends CrudRepository<ingreso,String>{
@Query("SELECT m FROM ingreso m WHERE "
			
			+ "m.id_ingreso LIKE %?1% OR "
			+ "m.habitacion LIKE %?1% OR "
			+ "m.cama LIKE %?1% OR "
			+ "m.estado LIKE %?1%")


	
	List<ingreso> filtroIngreso(String filtro);
}

