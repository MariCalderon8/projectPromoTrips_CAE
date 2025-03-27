package com.promo_viajes.api.web.controllers;

import com.promo_viajes.api.domain.dto.DestinationDTO;
import com.promo_viajes.api.domain.service.DestinationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    // Consultar todos los registros
    @Operation(summary = "Obtener todas los destinos", description = "Retorna una lista de todos los destinos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de destinos obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<DestinationDTO>> getAllDestinations() {
        Iterable<DestinationDTO> destinations = destinationService.findAll();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    // Consultar por ID
    @Operation(summary = "Obtener destino por ID", description = "Retorna el destino correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino encontrado"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DestinationDTO> getDestinationnById(@PathVariable Long id) {
        Optional<DestinationDTO> destination = destinationService.findById(id);
        return destination.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo destino
    @Operation(summary = "Guardar nuevo destino", description = "Guarda un nuevo destino en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Destino creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el destino")
    })
    @PostMapping("/save")
    public ResponseEntity<DestinationDTO> createDestination(@RequestBody DestinationDTO destinationDTO) {
        DestinationDTO savedDestination = destinationService.save(destinationDTO);
        return new ResponseEntity<>(savedDestination, HttpStatus.CREATED);
    }

    //Actualizar destino por Id
    @Operation(summary = "Actualizar destino", description = "Actualiza los datos de un destino existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar el Destino"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<DestinationDTO> updateDestination(@PathVariable Long id, @RequestBody DestinationDTO destinationDTO) {
        destinationDTO.setId(id);
        DestinationDTO updatedDestination = destinationService.update(destinationDTO);
        return ResponseEntity.ok(updatedDestination);
    }

    // Eliminar Destino por ID
    @Operation(summary = "Eliminar destino", description = "Elimina el destino correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Destino eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Destino no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long id) {
        boolean deleted = destinationService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Destino eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Destino no encontrado");
        }
    }

    // Contar el número total de destinos
    @Operation(summary = "Contar destinos", description = "Retorna el número total de destinos registrados")
    @ApiResponse(responseCode = "200", description = "Número total de destinos")
    @GetMapping("/count")
    public ResponseEntity<Long> countDestinations() {
        long count = destinationService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
