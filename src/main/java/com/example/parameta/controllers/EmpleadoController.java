package com.example.parameta.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.parameta.endpoint.EmpleadoEndpoint;
import com.example.parameta.gen.GuardarEmpleadoResponse;
import com.example.parameta.models.Empleado;
import com.example.parameta.models.EmpleadoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author checho
 */
@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoEndpoint empleadoEndpoint;

    @GetMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) throws ParseException {

        // Validaciones de datos vacíos y de formato
        if (!validarEmpleado(empleado)) {
            return ResponseEntity.badRequest().body("Datos del empleado incompletos o inválidos");
        }

        // Validación de edad (mayor de edad)
        if (!esMayorDeEdad(empleado.getFechaNacimiento())) {
            return ResponseEntity.badRequest().body("El empleado debe ser mayor de edad.");
        }

        try {
            // Llamada al servicio SOAP para almacenar el empleado
            GuardarEmpleadoResponse soapResponse = empleadoEndpoint.guardarEmpleado(empleado);

            // Calcular Edad y Tiempo de Vinculación después de la respuesta SOAP
            Period edad = calcularPeriodo(empleado.getFechaNacimiento());
            Period tiempoVinculacion = calcularPeriodo(empleado.getFechaVinculacion());

            // Generar la respuesta con la información adicional requerida
            EmpleadoResponse response = new EmpleadoResponse(empleado, edad, tiempoVinculacion,
                    soapResponse.getResultado());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al guardar el empleado: " + e.getMessage());
        }
    }

    private boolean validarEmpleado(Empleado empleado) {
        // Validación de datos vacios
        if (empleado.getNombres() == null || empleado.getNombres().isEmpty()) {
            return false;
        }

        if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
            return false;
        }

        if (empleado.getTipoDocumento() == null || empleado.getTipoDocumento().isEmpty()) {
            return false;
        }

        if (empleado.getNumeroDocumento() == null || empleado.getNumeroDocumento().isEmpty()) {
            return false;
        }

        if (empleado.getCargo() == null || empleado.getCargo().isEmpty()) {
            return false;
        }

        if (empleado.getSalario() == null || empleado.getSalario() <= 0) {
            return false;
        }

        return true;
    }

    private boolean esMayorDeEdad(Date fechaNacimiento) {
        Period edad = calcularPeriodo(fechaNacimiento);
        return edad.getYears() >= 18;
    }

    private Period calcularPeriodo(Date fecha) {
        LocalDate fechaLocal = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(fechaLocal, LocalDate.now());
    }

}
