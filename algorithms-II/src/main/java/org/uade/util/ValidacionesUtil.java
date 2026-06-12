package org.uade.util;

public class ValidacionesUtil {

    // Formato: 3 letras y 3 números (AAA111) O 2 letras, 3 números, 2 letras (AA111AA)
    public static final String REGEX_PATENTE = "^([A-Z]{3}\\d{3}|[A-Z]{2}\\d{3}[A-Z]{2})$";

    // Formato: Exactamente 3 letras (ej: BUE, ROS, CBA)
    public static final String REGEX_CODIGO_TERMINAL = "^[A-Z]{3}$";

    // Formato: Letras, espacios y acentos. Entre 3 y 50 caracteres.
    public static final String REGEX_DESCRIPCION = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{3,50}$";

    // Formato: dd/mm/yyyy (Valida el formato visual, el LocalDate valida si el día existe)
    public static final String REGEX_FECHA = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$";
}