package com.antonio.projects.parking.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.antonio.projects.parking.enums.TipoVehiculo;
import com.antonio.projects.parking.models.Spot;
import com.antonio.projects.parking.models.Vehiculo;
import com.antonio.projects.parking.repository.SpotRepository;
import com.antonio.projects.parking.repository.VehiculoRepository;
import com.antonio.projects.parking.utils.JsonUtils;

@Component
public class ParkingLot {

	private SpotRepository spotRepository;
	private VehiculoRepository vehiculoRepository;

	@Value("${parkingLot}")
	private String parkingLot;

	@Autowired
	public ParkingLot(SpotRepository spotRepository, VehiculoRepository vehiculoRepository) {
		this.spotRepository = spotRepository;
		this.vehiculoRepository = vehiculoRepository;
	}

	private Comparator<Spot> c = (a, b) -> {
		if (a.getNivel() > b.getNivel())
			return 1;
		if (a.getNivel() == b.getNivel() && a.getPosicion() > b.getPosicion())
			return 1;
		return -1;
	};

	private Queue<Spot> smallSpots = new PriorityQueue<>(c);
	private Queue<Spot> mediumSpots = new PriorityQueue<>(c);
	private Queue<Spot> largeSpots = new PriorityQueue<>(c);

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		List<Map<String, Integer>> listaParkingLots = JsonUtils.serialize(parkingLot, List.class);
		System.out.println("Lista parking: "+listaParkingLots);

		for (int i = 0; i < listaParkingLots.size(); i++) {
			System.out.println("Parking Lot: "+listaParkingLots.get(i));
			Map<String, Integer> map = listaParkingLots.get(i);
			int nivel = map.get("nivel");
			int small = map.get("small");
			int medium = map.get("medium");
			int large = map.get("large");

			for (int k = 0; k < small; k++)
				addToQueue(smallSpots, nivel, k, TipoVehiculo.SMALL);
			for (int k = 0; k < medium; k++)
				addToQueue(mediumSpots, nivel, k, TipoVehiculo.MEDIUM);
			for (int k = 0; k < large; k++)
				addToQueue(largeSpots, nivel, k, TipoVehiculo.LARGE);
		}
	}

	// Funcion que agrega en el Parking Spot el Tipo de Vehiculo
	void addToQueue(Spot spot) {
		switch (spot.getTipo()) {
		case SMALL:
			smallSpots.add(spot);
			break;
		case MEDIUM:
			mediumSpots.add(spot);
			break;
		case LARGE:
			largeSpots.add(spot);
			break;
		}
	}

	// Funcion que agrega en el Parking Spot el nivel donde se encuentra el spot, la
	// posicion y el tipo de vehiculo a parquear
	void addToQueue(Queue<Spot> queue, int nivel, int posicion, TipoVehiculo tipo) {
		Spot spot = spotRepository.findByNivelAndPosicion(nivel, posicion);
		if (spot != null && spot.getVehiculo() != null) {
			return;
		}

		queue.add(new Spot(tipo, nivel, posicion));
	}

	// Funcion que obtiene y elimina el spot donde se encuentra el tipo de vehiculo
	private Spot getSpot(TipoVehiculo tipo) {
		switch (tipo) {
		case SMALL:
			synchronized (smallSpots) {
				return smallSpots.poll();
			}
		case MEDIUM:
			synchronized (mediumSpots) {
				return mediumSpots.poll();
			}
		case LARGE:
			synchronized (largeSpots) {
				return largeSpots.poll();
			}
		}
		return null;
	}

	// Funcion que realiza el retardo de tiempo para parquear un vehiculo, con el
	// uso de Hilos(Threads)
	public void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	// Funcion que parquea un vehiculo en el spot y lo almacena en la base de datos
	public Spot park(Vehiculo vehiculo) {
		Spot spot = null;
		int retryCount = 0;

		while (spot == null && retryCount < 3) {
			spot = getSpot(vehiculo.getTipo());
			if (spot == null) {
				delay(200);
				retryCount++;
			}
		}

		if (spot == null) {
			throw new RuntimeException("No hay spots vacios.");
		}

		vehiculo = vehiculoRepository.save(vehiculo);
		spot.setVehiculo(vehiculo);
		vehiculo.setSpot(spot);

		return spotRepository.save(spot);
	}

	// Funcion que desparquea un vehiculo del Spot guardandolo en la base de datos
	// como nulo, y agregandolo a la cola el spot del vehiculo como desocupado
	public Vehiculo unPark(Vehiculo vehiculo) {
		Spot spot = vehiculo.getSpot();
		spot.setVehiculo(null);
		spotRepository.save(spot);
		addToQueue(spot);

		return vehiculo;
	}

}
