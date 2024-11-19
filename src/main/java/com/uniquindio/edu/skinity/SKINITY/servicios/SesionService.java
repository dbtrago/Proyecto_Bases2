package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Sesion;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import java.util.List;

public interface SesionService {
    
    public List<Sesion> listarSesion();
    
    public void guardar(Sesion sesion);
    
    public void eliminar(Sesion sesion);
    
    public Sesion encontrarSesion(Sesion sesion);
    
    public Sesion buscarPorUsuario(String usuario);
    
    public boolean construirSesion(Vendedor vendedor);
}
