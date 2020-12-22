package com.antonio.projects.parking.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.antonio.projects.parking.enums.TipoVehiculo;
import com.antonio.projects.parking.models.Spot;
import com.antonio.projects.parking.models.Vehiculo;
import com.antonio.projects.parking.repository.SpotRepository;
import com.antonio.projects.parking.repository.VehiculoRepository;
import com.antonio.projects.parking.service.IParkingLotService;
import com.antonio.projects.parking.service.ParkingLot;

@Service
public class ParkingLotServiceImpl implements IParkingLotService {

	private SpotRepository spotRepository;
	private VehiculoRepository vehiculoRepository;
	private ParkingLot parkingLot;

	@Autowired
	public ParkingLotServiceImpl(SpotRepository spotRepository, VehiculoRepository vehiculoRepository,
			ParkingLot parkingLot) {
		this.spotRepository = spotRepository;
		this.vehiculoRepository = vehiculoRepository;
		this.parkingLot = parkingLot;
	}

	@Override
	public Spot park(Vehiculo vehiculo) {
		// Validaciones del Vehiculo
		if (StringUtils.isEmpty(vehiculo.getRegNro())) {
			throw new RuntimeException("Numero de Registro esta vacio");
		}

		if (StringUtils.isEmpty(vehiculo.getColor())) {
			throw new RuntimeException("El color esta vacio");
		}

		if (vehiculo.getTipo() == null) {
			throw new RuntimeException("Tipo de Vehiculo Inválido");
		}

		Vehiculo getVehiculo = vehiculoRepository.findTop1ByRegNroOrderByInTimeDesc(vehiculo.getRegNro());
		if (getVehiculo == null || vehiculo.getOutTime() != null) {
			vehiculo.setInTime(new Date());
			return parkingLot.park(vehiculo);
		}
		return spotRepository.findByVehiculo(getVehiculo);
	}

	@Override
	public Vehiculo unPark(String regNro) {
		if (StringUtils.isEmpty(regNro)) {
			throw new RuntimeException("Numero de Registro está vacío");
		}

		Vehiculo vehiculo = vehiculoRepository.findTop1ByRegNroOrderByInTimeDesc(regNro);
		if (vehiculo == null) {
			throw new RuntimeException("Vehiculo nunca estacionado");
		}

		if (vehiculo.getSpot().getVehiculo() == null
				|| (!vehiculo.getSpot().getVehiculo().getRegNro().equals(regNro))) {
			throw new RuntimeException("Vehiculo no estacionado");
		}

		vehiculo.setOutTime(new Date());
		parkingLot.unPark(vehiculo);
		return vehiculo;
	}

	@Override
	public Vehiculo getVehiculo(String regNro) {
		return vehiculoRepository.findTop1ByRegNroOrderByInTimeDesc(regNro);
	}

	@Override
	public Page<Vehiculo> getVehiculosByTipo(TipoVehiculo tipo, PageRequest pageRequest) {
		return spotRepository.findByVehiculoTipo(tipo, pageRequest).map(Spot::getVehiculo);
	}

	@Override
	public Page<Vehiculo> getVehiculoByColor(String color, PageRequest pageRequest) {
		return spotRepository.findByVehiculoColor(color, pageRequest).map(Spot::getVehiculo);
	}

	@Override
	public Page<Vehiculo> getVehiculosByDuracion(Date startTime, Date endTime, PageRequest pageRequest) {
		return vehiculoRepository.getByDuration(startTime, endTime, pageRequest);
	}

}
