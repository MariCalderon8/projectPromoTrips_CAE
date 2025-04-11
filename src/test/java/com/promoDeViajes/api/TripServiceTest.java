package com.promoDeViajes.api;

import com.promo_viajes.api.domain.dto.TripDTO;
import com.promo_viajes.api.domain.repository.TripRepository;
import com.promo_viajes.api.domain.service.TripService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

//    @Test
//    public void testFindAll_ReturnsListOfTripDTO() {
//        // Arrange: crear una lista de TripDTO de ejemplo
//        TripDTO trip1 = new TripDTO(1l,"Viaje Santa Marta", "Medellin", 5l, 200, 4, LocalDate.of(2025,7,10), "Descripcion 1");
//        TripDTO trip2 = new TripDTO(2l,"Viaje Bogota", "Medellin", 5l, 200, 4, LocalDate.of(2025,7,10), "Descripcion 2");
//        List<TripDTO> expectedList = Arrays.asList(trip1, trip2);
//
//        // Configurar el repositorio para retornar la lista esperada
//        Mockito.when(tripRepository.findAll()).thenReturn(expectedList);
//
//        // Act: invocar el método findAll()
//        Iterable<TripDTO> result = tripService.findAll();
//
//        // Assert: verificar que la lista retornada sea igual a la esperada
//        assertNotNull(result, "El resultado no debe ser nulo");
//        List<TripDTO> resultList = (List<TripDTO>) result;
//        assertEquals(expectedList.size(), resultList.size(), "El tamaño de la lista debe coincidir");
//        assertEquals(expectedList, resultList, "La lista de TripDTO debe ser igual a la esperada");
//
//        // Verificar que el método findAll del repositorio se haya invocado una vez
//        verify(tripRepository, times(1)).findAll();
//    }
}
