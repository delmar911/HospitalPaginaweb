package com.sena.hospital2;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import com.sena.hospital2.controller.medicoController;
import com.sena.hospital2.controller.pacienteController;
import com.sena.hospital2.interfaceService.ImedicoService;
import com.sena.hospital2.interfaceService.IpacienteService;
import com.sena.hospital2.model.medico;
import com.sena.hospital2.model.paciente;


@SpringBootTest
class Hospital2ApplicationTests {

	@Test
	void contextLoads() {
	}

	//
	@Test
	public void PruebaEjemploTest(){//se crea el metodo
		int num1 = 8;//se definen variables
		int num2 = 5;
		int resultadoEjemplo = 13;
		assertEquals(resultadoEjemplo, num1+num2);
	}
	//									medico
	@Autowired
	private medicoController medicoController;

	@MockBean
	private ImedicoService medicoService;
 // test para registrar medico
	@Test
	@Rollback
	public void testMedicoGuardar(){

		//Test de Guardar Médico

		medico medico = new medico();
		medico.setTipo_documento("CC");
		medico.setNumero_documento("1077722773");
		medico.setPrimer_nombre("Cristian");
		medico.setPrimer_apellido("Narváez");
		medico.setCelular("3118176651");
		medico.setCorreo_electronico( "cristianfns11@gmail.com");
		medico.setDireccion("calle 21");
		medico.setEstado("Activo");
		ResponseEntity<Object> response = medicoController.save(medico);

		//Valor Esperado
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Rollback
	
	public void testInsertaMedicoIncompleto(){
		medico medico = new medico(); 
		medico.setTipo_documento("CC");
		medico.setNumero_documento("107650022");
		medico.setPrimer_nombre("");
		medico.setSegundo_nombre("");
		medico.setPrimer_apellido("Artunduaga");
		medico.setSegundo_apellido("Artunduaga");
		medico.setCelular("3112345234");
		medico.setCorreo_electronico("mariana@gmail.com");
		medico.setDireccion("cra 41 #12");
		medico.setEstado("Activo");

		ResponseEntity<Object> response = medicoController.save(medico); //llamada al controlador para guardar los datos del medico
		//valor esperado
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals("El primer nombre es obligatorio",response.getBody());
	}
	// test de guardar medico duplicado
	@Test
	public void testMedicoRepetidoGuardar() {
		
		medico medico = new medico();
		medico.setNumero_documento("1234567890");
		medico.setPrimer_nombre("Juan");
		medico.setPrimer_apellido("Perez");
		medico.setCelular("123456789");
		medico.setCorreo_electronico("juan@example.com");

		List<medico> listamedicos=new ArrayList<>();
		listamedicos.add(medico);
		//simula una base datos
		when(medicoService.filtroCedulaMedico(anyString())).thenReturn(listamedicos);
		

		ResponseEntity<Object> response = medicoController.save(medico);
		//valor esperado
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	//					paciente}
	@Autowired
	private pacienteController pacienteController; 
	
	@MockBean
	private IpacienteService pacienteService;
 // test para registrar medico
	@Test
	@Rollback
	public void guardarPaciente(){

		//Test de Guardar Médico

		paciente paciente = new paciente();
		paciente.setTipo_documento("CC");
		paciente.setNumero_documento("1077722773");
		paciente.setPrimer_nombre("yeison");
		paciente.setSegundo_nombre("camilo");
		paciente.setPrimer_apellido("Narváez");
		paciente.setCelular("3118176651");
		paciente.setCorreo_electronico( "cristianfns11@gmail.com");
		paciente.setDireccion("calle 21");
		paciente.setNombrePersonaContacto("Leidy");
		paciente.setCelularPersonaContacto("3145202950");
		paciente.setEstado("Activo");

		ResponseEntity<Object> response = pacienteController.save(paciente);

		//Valor Esperado
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	@Rollback
	
	public void resgistarPacienteIncompleto(){
		paciente paciente = new paciente(); 
		paciente.setTipo_documento("CC");
		paciente.setNumero_documento("1076500583");
		paciente.setPrimer_nombre("yolanda");
		paciente.setSegundo_nombre("");
		paciente.setPrimer_apellido("");
		paciente.setSegundo_apellido("Betez");
		paciente.setCelular("3112345234");
		paciente.setCorreo_electronico("mariana@gmail.com");
		paciente.setDireccion("cra 41 #12");
		paciente.setNombrePersonaContacto("Jorge");
		paciente.setCelularPersonaContacto("3145202950");
		paciente.setEstado("Activo");

		ResponseEntity<Object> response = pacienteController.save(paciente); //llamada al controlador para guardar los datos del medico
		//valor esperado
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
		assertEquals("El primer apellido es obligatorio",response.getBody());
	}

//guardar paciente duplicado
@Test
	public void pacienteDuplicado() {
		
		paciente paciente = new paciente();
		paciente.setNumero_documento("1234567890");
		paciente.setPrimer_nombre("Juan");
		paciente.setPrimer_apellido("Perez");
		paciente.setCelular("123456789");
		paciente.setCorreo_electronico("juan@example.com");

		List<paciente> listapacientes=new ArrayList<>();
		listapacientes.add(paciente);
		//simula una base datos
		when(pacienteService.filtroCedulaPaciente(anyString())).thenReturn(listapacientes);
		

		ResponseEntity<Object> response = pacienteController.save(paciente);
		//valor esperado
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
