package com.pasteleria.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * REPOSITORIO: Es el puente entre Java y MySQL.
 * Al extender de JpaRepository, ya tenemos listos los métodos: 
 * guardar, eliminar, buscar por ID, etc., sin escribir código SQL.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}