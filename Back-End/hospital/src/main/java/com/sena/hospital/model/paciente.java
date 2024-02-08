package com.sena.hospital.model;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity(name = "paciente")
public class paciente {
	
    @Id 
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_paciente", nullable = false, length = 36)
	private String id_paciente;

    @Column(name = "tipo_documento", nullable = false, length = 2)
	private String tipo_documento;

    @Column(name = "numero_documento", nullable = false, length = 40)
	private String numero_documento;

    @Column (name = "primer_nombre", nullable = false, length = 30)
	private String primer_nombre;
	
	@Column(name = "segundo_nombre", nullable = true, length = 100)
	private String segundo_nombre;
	
	@Column(name = "primer_apellido", nullable = true, length = 100)
	private String primer_apellido;
	
	@Column(name = "segundo_apellido", nullable = true, length = 100)
	private String segundo_apellido;
	
	@Column(name = "celular", nullable = false, length = 50)
	private String celular;

	@Column(name = "correo_electronico", nullable = false, length = 255)
	private String correo_electronico;

    @Column(name = "nombre_persona_contacto", nullable = false, length = 100)
	private String nombre_persona_contacto;

    @Column(name = "celular_persona_contacto", nullable = false, length = 100)
	private String celular_persona_contacto;

    @Column(name = "estado", nullable = false, length = 20)
	private String estado;



    	
	public paciente() {
		super();
	}

	public paciente(String id_paciente, String tipo_documento, String numero_documento, 
					String primer_nombre, String segundo_nombre, String primer_apellido,
					String segundo_apellido, String celular, String correo_electronico, 
                    String nombre_persona_contacto, String celular_persona_contacto, String estado)
	{
		super();
		
		this.id_paciente = id_paciente;
		this.tipo_documento = tipo_documento;
		this.numero_documento = numero_documento;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
		this.celular = celular;
		this.correo_electronico = correo_electronico;
        this.nombre_persona_contacto = nombre_persona_contacto;
        this.celular_persona_contacto = celular_persona_contacto;
        this.estado = estado;
	}

	public String getId() {
		return id_paciente;
	}

	public void setId(String id_paciente) {
		this.id_paciente = id_paciente;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	
	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}

	public String getSegundo_nombre() {
		return segundo_nombre;
	}

	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	} 
    public String getNombrePersonaContacto() {
		return nombre_persona_contacto;
	}

	public void setNombrePersonaContacto(String nombre_persona_contacto) {
		this.nombre_persona_contacto = nombre_persona_contacto;
	} 
    public String getCelularPersonaContacto() {
		return celular_persona_contacto;
	}

	public void setCelularPersonaContacto(String celular_persona_contacto) {
		this.celular_persona_contacto = celular_persona_contacto;
	}  
    public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	} 

}