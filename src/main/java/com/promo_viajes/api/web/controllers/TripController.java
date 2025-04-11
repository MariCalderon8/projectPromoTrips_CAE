package com.promo_viajes.api.web.controllers;

import com.promo_viajes.api.domain.dto.TripDTO;
import com.promo_viajes.api.domain.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // Consultar todos los viajes
    @Operation(summary = "Obtener todas los viajes", description = "Retorna una lista de todos los viajes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<TripDTO>> getAllTrips() {
        Iterable<TripDTO> trips = tripService.findAll();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Consultar por ID
    @Operation(summary = "Obtener viaje por ID", description = "Retorna el viaje correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje encontrada"),
            @ApiResponse(responseCode = "400", description = "ID inválido o mal formado"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getViajeById(@PathVariable Long id) {
        Optional<TripDTO> trip = tripService.findById(id);
        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo viaje
    @Operation(summary = "Guardar nuevo viaje", description = "Guarda un nuevo viaje en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Viaje creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el viaje"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/save")
    public ResponseEntity<TripDTO> createViaje(@RequestBody TripDTO tripDTO) {
        TripDTO savedTrip = tripService.save(tripDTO);
        return new ResponseEntity<>(savedTrip, HttpStatus.CREATED);
    }

    //Actualizar viaje por Id
    @Operation(summary = "Actualizar viaje", description = "Actualiza los datos de un viaje existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el Viaje - datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable Long id, @RequestBody TripDTO tripDTO) {
        tripDTO.setId(id);
        TripDTO updateTrip = tripService.update(tripDTO);
        return ResponseEntity.ok(updateTrip);
    }

    // Eliminar Viaje por ID
    @Operation(summary = "Eliminar viaje", description = "Elimina el viaje correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Viaje no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long id) {
        boolean deleted = tripService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Viaje eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Viaje no encontrado");
        }
    }

    // Consultar los viajes por un precio especifico o menor
    @Operation(summary = "Obtener todas los viajes por precio", description = "Retorna una lista de todos los viajes registrados por un precio especifico o menor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetro de precio inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/filter-by-price")
    public ResponseEntity<Iterable<TripDTO>> getAllTripsByPrice(@RequestParam float price) {
        Iterable<TripDTO> trips = tripService.findTripsByPriceOrLower(price);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Consultar los viajes por un fecha especifica
    @Operation(summary = "Obtener todas los viajes por fecha", description = "Retorna una lista de todos los viajes registrados en una fecha especifica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajes obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetro de fecha inválido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/filter-by-date")
    public ResponseEntity<Iterable<TripDTO>> getAllTripsByDate(@RequestParam LocalDate date) {
        Iterable<TripDTO> trips = tripService.findTripsByDate(date);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Contar el número total de viajes
    @Operation(summary = "Contar viajes", description = "Retorna el número total de viajes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Número total de viajes"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/count")
    public ResponseEntity<Long> countViajes() {
        long count = tripService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
//
//    // Asociar un destino con un viaje
//    @Operation(summary = "Asociar un destino con un viaje", description = "Asociar un destino con un viaje en el sistema")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Destino agregado exitosamente"),
//            @ApiResponse(responseCode = "400", description = "Error al asociar el destino")
//    })
//    @PostMapping("/add-new-destination")
//    public ResponseEntity<String> createViaje(@RequestParam Long trip, @RequestParam Long destination) {
//        boolean created = tripService.addNewDestination(trip, destination);
//        if (created) {
//            return ResponseEntity.ok("Destino agregado exitosamente");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No se pudo agregar el destino");
//        }
//    }
}
