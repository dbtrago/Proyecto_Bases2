package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.TipoProducto;
import java.util.List;

public interface TipoProductoService {
    public List<TipoProducto> listarTipoProducto();
    
    public void guardar(TipoProducto tipoProducto);
    
    public void eliminar(TipoProducto tipoProducto);
    
    public TipoProducto encontrarTipoProducto(TipoProducto tipoProducto);
    
}
