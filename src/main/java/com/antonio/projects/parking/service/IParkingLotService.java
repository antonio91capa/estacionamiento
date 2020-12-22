package com.antonio.projects.parking.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.antonio.projects.parking.enums.TipoVehiculo;
import com.antonio.projects.parking.models.Spot;
import com.antonio.projects.parking.models.Vehiculo;

public interface IParkingLotService {
	Spot park(Vehiculo vehiculo);
	
	Vehiculo unPark(String regNro);
	Vehiculo getVehiculo(String regNro);
	
	Page<Vehiculo> getVehiculosByTipo(TipoVehiculo tipo, PageRequest pageRequest);
	Page<Vehiculo> getVehiculoByColor(String color, PageRequest pageRequest);
	Page<Vehiculo> getVehiculosByDuracion(Date startTime, Date endTime, PageRequest pageRequest);
}
