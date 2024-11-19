package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Estado;
import java.util.List;

public interface EstadoService {
    public List<Estado> listarEstado();
    
    public void guardar(Estado estado);
    
    public void eliminar(Estado estado);
    
    public Estado encontrarEstado(Estado estado);
    
    public Estado encontrarPorDescripcion(String descripcion);
    
}
