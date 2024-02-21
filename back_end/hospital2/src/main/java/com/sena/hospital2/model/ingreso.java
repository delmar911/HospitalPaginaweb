package com.sena.hospital2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name="ingreso")
public class ingreso {
	//- id (UIID autogenerado)
	//- Habitación (Obligatorio).
	//- Cama (Obligatorio)
	//- Paciente (Obligatorio)
	//- Medico (Obligatorio)
	//- Fecha de ingreso (Obligatorio)
	//- Fecha de salida (Obligatorio)
	//- Estado (Obligatorio)
	
	
    @Id 
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_ingreso", nullable = false, length = 36)
	private String id_ingreso;
    
    @Column(name = "habitacion", nullable = false, length = 10)
   	private String habitacion;
    
    @Column(name = "cama", nullable = false, length = 10)
   	private String cama;
    
    @Column(name = "fecha_ingreso", nullable = false, length = 40)
   	private LocalDateTime fecha_ingreso;
    
    @Column(name = "fecha_salida", nullable = false, length = 40)
   	private LocalDateTime fecha_salida;
    
    @Column(name = "estado", nullable = false, length = 10)
   	private String estado;
    
    
    
    
    
    @ManyToOne
	@JoinColumn(name = "id_paciente")
	private paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private medico medico;
    

    
	
	
	
    public ingreso() {
		super();
	}

	public ingreso(String id_ingreso, String habitacion, String cama, 
					String id_paciente, String id_medico, LocalDateTime fecha_ingreso,
					LocalDateTime fecha_salida, String estado 
                  )
	{
		super();
		
		this.id_ingreso = id_ingreso;
		this.habitacion = habitacion;
		this.cama = cama;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_salida = fecha_salida;
		this.estado = estado;
		
	}

	public String getId_ingreso() {
		return id_ingreso;
	}

	public void setId_ingreso(String id_ingreso) {
		this.id_ingreso = id_ingreso;
	}

	public String getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public String getCama() {
		return cama;
	}

	public void setCama(String cama) {
		this.cama = cama;
	}

	public paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(paciente paciente) {
		this.paciente = paciente;
	}

	public medico getMedico() {
		return medico;
	}

	public void setMedico(medico medico) {
		this.medico = medico;
	}

	public LocalDateTime getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(LocalDateTime fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public LocalDateTime getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(LocalDateTime fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
}

