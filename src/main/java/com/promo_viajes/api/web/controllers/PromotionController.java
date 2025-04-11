package com.promo_viajes.api.web.controllers;

import com.promo_viajes.api.domain.dto.PromotionDTO;
import com.promo_viajes.api.domain.service.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    // Consultar todos los registros
    @Operation(summary = "Obtener todas las promociones", description = "Retorna una lista de todas las promociones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de promociones obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<PromotionDTO>> getAllPromotions() {
        Iterable<PromotionDTO> promotions = promotionService.findAll();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    // Consultar por ID
    @Operation(summary = "Obtener promociones por ID", description = "Retorna la promocion correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promocion encontrada"),
            @ApiResponse(responseCode = "404", description = "Promocion no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> getPromotionById(@PathVariable Long id) {
        Optional<PromotionDTO> promotion = promotionService.findById(id);
        return promotion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo promocion
    @Operation(summary = "Guardar nueva promocion", description = "Guarda una nueva promocion en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Promocion creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la promocion"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/save")
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        PromotionDTO savedPromotion = promotionService.save(promotionDTO);
        return new ResponseEntity<>(savedPromotion, HttpStatus.CREATED);
    }

    //Actualizar promocion por Id
    @Operation(summary = "Actualizar promocion", description = "Actualiza los datos de una promocion existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promocion actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al actualizar la promocion"),
            @ApiResponse(responseCode = "404", description = "Promocion no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<PromotionDTO> updatePromotion(@PathVariable Long id, @RequestBody PromotionDTO promotionDTO) {
        promotionDTO.setId(id);
        PromotionDTO updatedPromotion = promotionService.update(promotionDTO);
        return ResponseEntity.ok(updatedPromotion);
    }

    // Eliminar Promocion por ID
    @Operation(summary = "Eliminar promocion", description = "Elimina la promocion correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promocion eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Promocion no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePromocion(@PathVariable Long id) {
        boolean deleted = promotionService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Promocion eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Promocion no encontrado");
        }
    }

    // Consultar todas las promociones activas
    @Operation(summary = "Obtener todas las promociones activas", description = "Retorna una lista de todas las promociones que actualmente se encuentran activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de promociones obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/promotions-actives")
    public ResponseEntity<Iterable<PromotionDTO>> getAllActivePromotions() {
        Iterable<PromotionDTO> promotions = promotionService.findAllActivesPromotions();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    // Consultar todas las promociones de un viaje especifico
    @Operation(summary = "Obtener todas las promociones de un viaje", description = "Retorna una lista de todas las promociones de un viaje determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de promociones obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/promotions-by-trip/{tripId}")
    public ResponseEntity<Iterable<PromotionDTO>> getAllPromotionsByTrip(@PathVariable Long tripId) {
        Iterable<PromotionDTO> promotions = promotionService.findAllPromotionsByTrip(tripId);
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    // Contar el número total de promociones
    @Operation(summary = "Contar promociones", description = "Retorna el número total de promociones registrados")
    @ApiResponse(responseCode = "200", description = "Número total de promociones")
    @GetMapping("/count")
    public ResponseEntity<Long> countPromotions() {
        long count = promotionService.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
