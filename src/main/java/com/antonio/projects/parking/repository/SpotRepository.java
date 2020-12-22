package com.antonio.projects.parking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antonio.projects.parking.enums.TipoVehiculo;
import com.antonio.projects.parking.models.Spot;
import com.antonio.projects.parking.models.Vehiculo;

@Repository
public interface SpotRepository extends JpaRepository<Spot, String> {
	Spot findByVehiculo(Vehiculo vehiculo);
	Spot findByVehiculoRegNro(String regNro);
	Spot findByNivelAndPosicion(int nivel, int posicion);

	Page<Spot> findByVehiculoTipo(TipoVehiculo tipo, Pageable pageable);
	Page<Spot> findByVehiculoColor(String color, Pageable pageable);
}
