package com.antonio.projects.parking.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.antonio.projects.parking.enums.TipoVehiculo;

@Entity
@Table(name = "vehiculos")
public class Vehiculo extends BaseAbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "numero_vehiculo", nullable = false)
	private String regNro;

	@NotNull
	@Column(nullable = false)
	private String color;

	@NotNull
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private TipoVehiculo tipo;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "hora_entrada", nullable = false)
	private Date inTime;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "hora_salida", nullable = false)
	private Date outTime;

	@OneToOne
	@JoinColumn(name = "spot_id", referencedColumnName = "id")
	private Spot spot;

	public Vehiculo() {
	}

	public Vehiculo(String regNro) {
		this.regNro = regNro;
	}

	public Vehiculo(String regNro, String color, TipoVehiculo tipo, Spot spot) {
		this.regNro = regNro;
		this.color = color;
		this.tipo = tipo;
		this.spot = spot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegNro() {
		return regNro;
	}

	public void setRegNro(String regNro) {
		this.regNro = regNro;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vehiculo [id=").append(id).append(", regNro=").append(regNro).append(", color=").append(color)
				.append(", tipo=").append(tipo).append(", inTime=").append(inTime).append(", outTime=").append(outTime)
				.append(", spot=").append(spot).append("]");
		return builder.toString();
	}

}
