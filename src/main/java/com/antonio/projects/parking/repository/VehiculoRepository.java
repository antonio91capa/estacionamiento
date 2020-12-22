package com.antonio.projects.parking.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antonio.projects.parking.models.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
	Page<Vehiculo> findAllByTipo(String tipo, Pageable pageable);
	Page<Vehiculo> findAllByColor(String color, Pageable pageable);

	Vehiculo findByRegNro(String regNro);

	boolean existsByRegNro(String regNro);

	@Query(value = "select * from vehiculos where hora_entrada >= :inTime and (hora_salida <= :outTime || hora_salida is null)", nativeQuery = true)
	Page<Vehiculo> getByDuration(@Param("inTime") Date inTime, @Param("outTime") Date outTime, Pageable pageable);

	Vehiculo findTop1ByRegNroOrderByInTimeDesc(String regNro);
	//Vehiculo findTop1ByRegNroOrderByVersionDesc(String regNro);

}
