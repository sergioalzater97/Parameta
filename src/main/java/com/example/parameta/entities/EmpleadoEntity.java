package com.example.parameta.entities;

/**
 *
 * @author chech
 */
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "empleados")
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaNacimiento;
    private Date fechaVinculacion;
    private String cargo;
    private Double salario;
}
