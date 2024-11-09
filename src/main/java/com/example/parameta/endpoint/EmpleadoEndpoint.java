package com.example.parameta.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.parameta.entities.EmpleadoEntity;
import com.example.parameta.gen.GuardarEmpleadoResponse;
import com.example.parameta.models.Empleado;
import com.example.parameta.repositories.EmpleadoRepository;

@Endpoint
public class EmpleadoEndpoint {

    private static final String NAMESPACE_URI = "http://parameta.com/empleado";

    @Autowired
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoEndpoint(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GuardarEmpleadoRequest")
    @ResponsePayload
    public GuardarEmpleadoResponse guardarEmpleado(@RequestPayload Empleado request) {
        // Convertir la solicitud a una entidad de EmpleadoEntity
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setNombres(request.getNombres());
        empleado.setApellidos(request.getApellidos());
        empleado.setTipoDocumento(request.getTipoDocumento());
        empleado.setNumeroDocumento(request.getNumeroDocumento());
        empleado.setFechaNacimiento(request.getFechaNacimiento());
        empleado.setFechaVinculacion(request.getFechaVinculacion());
        empleado.setCargo(request.getCargo());
        empleado.setSalario(request.getSalario());

        // Guardar en la base de datos
        empleadoRepository.save(empleado);

        // Preparar la respuesta
        GuardarEmpleadoResponse response = new GuardarEmpleadoResponse();
        response.setResultado("Empleado guardado exitosamente");

        return response;
    }
}
