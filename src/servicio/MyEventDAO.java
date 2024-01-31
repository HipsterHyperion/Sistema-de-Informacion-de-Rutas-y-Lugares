/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicio;

import java.time.LocalDateTime;
import java.util.Optional;
import modelo.entidad.MyEvent;

/**
 *
 * @author Martín
 */
public interface MyEventDAO {
    
    
    Optional<MyEvent> findById(LocalDateTime timestamp);
    MyEvent save(MyEvent miEvento);
    Iterable<MyEvent> findAll();
    void deleteById(LocalDateTime timestamp);
}
