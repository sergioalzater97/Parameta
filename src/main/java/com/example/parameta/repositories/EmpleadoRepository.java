package com.example.parameta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.parameta.entities.EmpleadoEntity;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
}