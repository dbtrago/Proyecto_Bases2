package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Sesion;
import com.uniquindio.edu.skinity.SKINITY.modelo.TipoProducto;
import com.uniquindio.edu.skinity.SKINITY.repositorio.ISesionDao;
import com.uniquindio.edu.skinity.SKINITY.repositorio.ITipoProductoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProductoServiceImpl implements TipoProductoService{

    @Autowired
    private ITipoProductoDao tipoProductoDao;

    @Override
    public List<TipoProducto> listarTipoProducto() {
        return (List<TipoProducto>) tipoProductoDao.findAll();
    }

    @Override
    public void guardar(TipoProducto tipoProducto) {
        tipoProductoDao.save(tipoProducto);
    }

    @Override
    public void eliminar(TipoProducto tipoProducto) {
        tipoProductoDao.delete(tipoProducto);
    }

    @Override
    public TipoProducto encontrarTipoProducto(TipoProducto tipoProducto) {
        return tipoProductoDao.findById(tipoProducto.getTipoProductoId()).orElse(null);
    }
    
}
