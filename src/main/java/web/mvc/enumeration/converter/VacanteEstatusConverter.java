package web.mvc.enumeration.converter;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import web.mvc.enumeration.VacanteEstatus;

@Converter( autoApply = true )
public class VacanteEstatusConverter implements AttributeConverter<VacanteEstatus, String> {

	//Hash map - Tabla de hash para almacenar datos tipo key -> value
	private final static Map<VacanteEstatus, String> RELATIONS = new HashMap<>();
	
	static {
		RELATIONS.put(VacanteEstatus.APROBADA, "Aprobada");
		RELATIONS.put(VacanteEstatus.NO_APROBADA, "No Aprobada");
		RELATIONS.put(VacanteEstatus.PENDIENTE, "Pendiente");
	}
	
	/*
	 * Método sobrecargado que convierte los nombres del ENUM a las Cadenas configuradas
	 * Para cada registro
	 *    
	 */
	
	public VacanteEstatus convert(String source) {
        if (source == null) return null;

        // Normaliza para que coincida con el enum
        String normalized = source.trim().toUpperCase().replace(" ", "_");

        try {
            return VacanteEstatus.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Valor inválido para VacanteEstatus: " + source);
        }
    }
	
	@Override
	public String convertToDatabaseColumn(VacanteEstatus vacanteEstatus) {
		
		if (vacanteEstatus == null) return null;
		return RELATIONS.get(vacanteEstatus);
	}

	@Override
	public VacanteEstatus convertToEntityAttribute(String code) {
	
		if (code == null) {
            return null;
        }
		
		/*Retornar el Enum VacanteEstatus apartir del valor entrante code*/
        return RELATIONS.entrySet().stream()
          .filter(entry -> code.equals(entry.getValue() ) )
          .findFirst().map(Map.Entry::getKey)
          .orElseThrow(IllegalArgumentException::new);
	}
}
