package TorneoMagic.DTOs;

public record LocalDTO(
    Long id,
    String nombre,
    String direccion,
    Integer capacidad,
    Long comunaId
) {
}
