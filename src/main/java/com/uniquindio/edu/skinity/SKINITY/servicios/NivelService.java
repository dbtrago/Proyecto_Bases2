package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Nivel;
import java.util.List;

public interface NivelService {
    public List<Nivel> listarNivel();
    
    public void guardar(Nivel nivel);
    
    public void eliminar(Nivel nivel);
    
    public Nivel encontrarNivel(Nivel nivel);
    
    public Nivel encontrarNivelPorNombre(String nombre);
    
}
