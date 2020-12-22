package com.antonio.projects.parking.models;

import javax.persistence.CascadeType;
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

import com.antonio.projects.parking.enums.TipoVehiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "spots")
public class Spot extends BaseAbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_vehiculo")
	@Enumerated(value = EnumType.STRING)
	private TipoVehiculo tipo;

	private int nivel;
	private int posicion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehiculo_id")
	@JsonIgnore
	private Vehiculo vehiculo;

	public Spot() {
	}

	public Spot(TipoVehiculo tipo, int nivel, int posicion) {
		this.tipo = tipo;
		this.nivel = nivel;
		this.posicion = posicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spot [id=").append(id).append(", tipo=").append(tipo).append(", nivel=").append(nivel)
				.append(", posicion=").append(posicion).append(", vehiculo=").append(vehiculo).append("]");
		return builder.toString();
	}

}
