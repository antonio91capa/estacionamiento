package com.antonio.projects.parking.controllers;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.projects.parking.enums.TipoVehiculo;
import com.antonio.projects.parking.models.Vehiculo;
import com.antonio.projects.parking.service.IParkingLotService;

@RestController
@RequestMapping("/parking")
public class ParkingLotController {

	private IParkingLotService parkingLotService;

	@Autowired
	public ParkingLotController(IParkingLotService parkingLotService) {
		this.parkingLotService = parkingLotService;
	}

	// Controlador: Obtener el vehiculo por su numero de registro
	@GetMapping("/vehiculo/get/{regno:.+}")
	public ResponseEntity<?> getVehiculoByNoReg(@PathVariable("regno") String noReg) {
		try {
			return new ResponseEntity<>(parkingLotService.getVehiculo(noReg), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Controlador: Estacinar un vehiculo
	@PostMapping("/vehiculo/park")
	public ResponseEntity<?> parkVehiculo(@RequestBody Vehiculo vehiculo) {
		try {
			return new ResponseEntity<>(parkingLotService.park(vehiculo), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Controlador: Des-estacionar el vehiculo
	@DeleteMapping("/vehiculo/unpark/{regno:.+}")
	public ResponseEntity<?> unparkVehiculo(@PathVariable("regno") String noReg) {
		try {
			return new ResponseEntity<>(parkingLotService.unPark(noReg), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Controlador: Obtener todos los vehiculos
	@GetMapping("/vehiculos")
	public ResponseEntity<?> getAllVehiculos(@RequestParam(value = "color", required = false) String color,
			@RequestParam(value = "tipo", required = false) TipoVehiculo tipo,
			@RequestParam(value = "hora_entrada", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date horaEntrada,
			@RequestParam(value = "hora_salida", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date horaSalida,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		PageRequest pageRequest = PageRequest.of(page.orElse(10), size.orElse(10));

		try {
			if (StringUtils.isNotBlank(color)) {
				return new ResponseEntity<>(parkingLotService.getVehiculoByColor(color, pageRequest), HttpStatus.FOUND);
			} else if (tipo != null) {
				return new ResponseEntity<>(parkingLotService.getVehiculosByTipo(tipo, pageRequest), HttpStatus.FOUND);
			}

			return new ResponseEntity<>(parkingLotService.getVehiculosByDuracion(horaEntrada, horaSalida, pageRequest),
					HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}

}
