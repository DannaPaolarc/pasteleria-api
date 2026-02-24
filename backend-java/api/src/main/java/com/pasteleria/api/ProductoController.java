package com.pasteleria.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * CONTROLADOR: Define las rutas (URLs) de nuestra API.
 */

@RestController // Indica que es un servicio REST
@RequestMapping("/api/productos") // La URL base será http://localhost:8080/api/productos
@SecurityRequirement(name = "basicAuth") // <--- AGREGA ESTA LÍNEA
public class ProductoController {

    @Autowired // Inyecta el repositorio automáticamente
    private ProductoRepository repository;

    // MÉTODO GET: Para listar todos los pasteles
    @GetMapping
    public List<Producto> listar() {
        return repository.findAll();
    }

    // MÉTODO POST: Para guardar un pastel nuevo
    @PostMapping
    public Producto guardar(@RequestBody Producto nuevo) {
        return repository.save(nuevo);
    }
    /**
     * MÉTODO DELETE: Borra un pastel por su ID.
     * En Swagger aparecerá como un botón ROJO.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    /**
     * MÉTODO PUT: Actualiza los datos de un pastel existente.
     * En Swagger aparecerá como un botón NARANJA.
     */
    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto datosActualizados) {
        return repository.findById(id)
                .map(producto -> {
                    producto.setNombre(datosActualizados.getNombre());
                    producto.setPrecio(datosActualizados.getPrecio());
                    producto.setStock(datosActualizados.getStock());
                    return repository.save(producto);
                }).orElseGet(() -> {
                    datosActualizados.setId(id);
                    return repository.save(datosActualizados);
                });
    }
}