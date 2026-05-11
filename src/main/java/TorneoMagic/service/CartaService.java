package TorneoMagic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TorneoMagic.DTO.CartaDTO;
import TorneoMagic.model.Carta;
import TorneoMagic.repository.CartaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaService {

    @Autowired
    private CartaRepository cartaRepository;

    public CartaDTO guardarCarta(CartaDTO cartaDTO) {
        Carta carta = new Carta();
        carta.setNombre(cartaDTO.getNombre());
        
        Carta cartaGuardada = cartaRepository.save(carta);
        
        return convertirADTO(cartaGuardada);
    }

    public List<CartaDTO> obtenerTodas() {
        return cartaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private CartaDTO convertirADTO(Carta carta) {
        CartaDTO dto = new CartaDTO();
        dto.setId(carta.getId());
        dto.setNombre(carta.getNombre());
        dto.setDescripcion(carta.getDescripcion());
        dto.setRareza(carta.getRareza());
        dto.setCosto(carta.getCosto());
        
        return dto;
    }
    //prueba
}


