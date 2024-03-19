package com.sena.hospital2.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sena.hospital2.model.ingreso;

@Repository

public interface Iingreso extends CrudRepository<ingreso,String>{
@Query("SELECT i FROM ingreso i JOIN "
			
			+ "i.medico m "
			+ " JOIN i.paciente p "


			+ " WHERE p.id_paciente LIKE %?1% "
			+ " OR m.id_medico LIKE %?1% "

			+ " OR p.tipo_documento LIKE %?1% "
			+ " OR m.tipo_documento LIKE %?1% "
			
			+ " OR p.numero_documento LIKE %?1% "
			+ " OR m.numero_documento LIKE %?1% "

			+ " OR p.primer_nombre LIKE %?1% "
			+ " OR m.primer_nombre LIKE %?1% "

			+ " OR p.segundo_nombre LIKE %?1% "
			+ " OR m.segundo_nombre LIKE %?1% "

			+ " OR p.primer_apellido LIKE %?1% "
			+ " OR m.primer_apellido LIKE %?1% "

			+ " OR p.segundo_apellido LIKE %?1% "
			+ " OR m.segundo_apellido LIKE %?1% "

			+ " OR p.celular LIKE %?1% "
			+ " OR m.celular LIKE %?1% "

			+ " OR p.direccion LIKE %?1% "
			+ " OR m.direccion LIKE %?1% "
			
			+ " OR p.correo_electronico LIKE %?1% "
			+ " OR m.correo_electronico LIKE %?1% "
			
			+ " OR p.nombre_persona_contacto LIKE %?1% "
			+ " OR p.celular_persona_contacto LIKE %?1% "
			
			+ " OR p.estado LIKE %?1% "
			+ " OR m.estado LIKE %?1% "
		)

		
	List<ingreso> filtroIngreso(String filtro);

	@Query ("SELECT i FROM ingreso i "

			+ "WHERE i.fecha_ingreso = ?1 "
			
			
			)
			List<ingreso> filtroFechaIngreso(LocalDateTime fecha_ingreso);

		//para no poder ingresar  una persona que ya existe en la base de datos
			@Query ("SELECT i FROM ingreso i JOIN i.paciente p "
					+"WHERE p.id_paciente=?1 AND i.estado='Activo' "
			
			
			)
			List<ingreso> filtroEstado(String id_paciente);

			@Query ("SELECT i FROM ingreso i "

			+ "WHERE i.cama = ?1 AND i.habitacion = ?2 AND i.estado='Activo' "

			)
			List<ingreso> filtroCama(String cama, String habitacion);
}

