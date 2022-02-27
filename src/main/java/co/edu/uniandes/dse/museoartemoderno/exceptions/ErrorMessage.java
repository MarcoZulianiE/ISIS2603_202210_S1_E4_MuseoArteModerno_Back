package co.edu.uniandes.dse.museoartemoderno.exceptions;

public final class ErrorMessage {
	public static final String ARTISTA_NOT_FOUND = "El *artista* con el id dado no fue encontrado";
	public static final String CIUDAD_NOT_FOUND = "La *ciudad* con el id dado no fue encontrado";
	public static final String MOVIMIENTO_ARTISTICO_NOT_FOUND = "El *movimiento artistico* con el id dado no fue encontrado";
	public static final String MUSEO_NOT_FOUND = "El *museo* con el id dado no fue encontrado";
	public static final String OBRA_NOT_FOUND = "La *obra* con el id dado no fue encontrado";
	public static final String PAIS_NOT_FOUND = "El *pais* con el id dado no fue encontrado";

	private ErrorMessage() {
		throw new IllegalStateException("Utility class");
	}
}