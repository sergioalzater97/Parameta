package com.example.parameta.models;

import java.time.Period;
import lombok.Data;

/**
 *
 * @author checho
 */

@Data
public class EmpleadoResponse {

    private Empleado empleado;
    private String edad;
    private String tiempoVinculacion;
    private String confirmacion;

    public EmpleadoResponse(Empleado empleado, Period edad, Period tiempoVinculacion, String confirmacion) {
        this.empleado = empleado;
        this.edad = String.format("%d años, %d meses, %d días", edad.getYears(), edad.getMonths(), edad.getDays());
        this.tiempoVinculacion = String.format("%d años, %d meses, %d días", tiempoVinculacion.getYears(), tiempoVinculacion.getMonths(), tiempoVinculacion.getDays());
        this.confirmacion = confirmacion;
    }
}
