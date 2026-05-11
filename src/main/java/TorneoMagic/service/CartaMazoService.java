package TorneoMagic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TorneoMagic.DTO.CartaMazoDTO;
import TorneoMagic.model.Carta;
import TorneoMagic.model.CartaMazo;
import TorneoMagic.model.Mazo;
import TorneoMagic.repository.CartaMazoRepository;
import TorneoMagic.repository.CartaRepository;
import TorneoMagic.repository.MazoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartaMazoService {

    @Autowired
    private CartaMazoRepository cartaMazoRepository;

    @Autowired
    private CartaRepository cartaRepository;

    @Autowired
    private MazoRepository mazoRepository;

    public List<CartaMazoDTO> obtenerTodas() {
        return cartaMazoRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public String agregarCartaAMazo(Long mazoId, Long cartaId, Integer cantidad) {
        Mazo mazo = mazoRepository.findById(mazoId)
            .orElseThrow(() -> new RuntimeException("¡El Deck no existe!"));
        
        Carta carta = cartaRepository.findById(cartaId)
            .orElseThrow(() -> new RuntimeException("¡La carta no existe "));

        CartaMazo relacion = new CartaMazo();
        relacion.setMazo(mazo);
        relacion.setCarta(carta);
        relacion.setCantidad(cantidad);

        cartaMazoRepository.save(relacion);
        
        return "¡Combo listo! Has añadido " + cantidad + " copias de '" + carta.getNombre() + "' al mazo '" + mazo.getNombre() + "'.";
    }

    private CartaMazoDTO convertirADTO(CartaMazo cartaMazo) {
        CartaMazoDTO dto = new CartaMazoDTO();
        dto.setId(cartaMazo.getId());
        dto.setCantidad(cartaMazo.getCantidad());

        // Verificamos si tiene mazo asignado
        if (cartaMazo.getMazo() != null) {
            dto.setNombreMazo(cartaMazo.getMazo().getNombre());
        } else {
            dto.setNombreMazo("Deck perdido :(");
        }

        // Verificamos si tiene carta asignada
        if (cartaMazo.getCarta() != null) {
            dto.setNombreCarta(cartaMazo.getCarta().getNombre());
        } else {
            dto.setNombreCarta("Carta inexistente");
        }

        return dto;
    }
}



