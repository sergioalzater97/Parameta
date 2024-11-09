package com.example.parameta.models;

import lombok.Data;
import java.util.Date;

/**
 *
 * @author checho
 */

@Data
public class Empleado {
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private Date fechaVinculacion;
    private String cargo;
    private Double salario;
}
