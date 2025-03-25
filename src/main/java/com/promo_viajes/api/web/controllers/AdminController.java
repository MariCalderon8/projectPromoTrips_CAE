
package com.promo_viajes.api.web.controllers;

import com.promo_viajes.api.domain.dto.AdminDTO;
import com.promo_viajes.api.domain.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    // Consultar todos los registros
    @Operation(summary = "Obtener todas los admin", description = "Retorna una lista de todos los administradores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de admins obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<AdminDTO>> getAllAdmins() {
        Iterable<AdminDTO> admins = adminService.findAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
    
    // Consultar por ID
    @Operation(summary = "Obtener administrador por ID", description = "Retorna el admin correspondiente al ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador encontrada"),
            @ApiResponse(responseCode = "404", description = "Administrador no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        Optional<AdminDTO> admin = adminService.findById(id);
        return admin.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo admin
    @Operation(summary = "Guardar nuevo admin", description = "Guarda un nuevo admin en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear el admin")
    })
    @PostMapping("/save")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO savedAdmin = adminService.save(adminDTO);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }
}
