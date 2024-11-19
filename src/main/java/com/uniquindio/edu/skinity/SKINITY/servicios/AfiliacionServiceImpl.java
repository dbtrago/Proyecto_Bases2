package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Afiliacion;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IAfiliacionDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AfiliacionServiceImpl implements AfiliacionService{

    @Autowired
    private IAfiliacionDao afiliacionDao;
    
    @Override
    public List<Afiliacion> listarAfiliacion() {
        return (List<Afiliacion>) afiliacionDao.findAll();
    }

    @Override
    public void guardar(Afiliacion afiliacion) {
        afiliacionDao.save(afiliacion);
    }

    @Override
    public void eliminar(Afiliacion afiliacion) {
        afiliacionDao.delete(afiliacion);
    }

    @Override
    public Afiliacion encontrarAfiliacion(Afiliacion afiliacion) {
        return afiliacionDao.findById(afiliacion.getAfiliacionId()).orElse(null);
    }

    @Override
    public Afiliacion buscarPorVendedor(Vendedor vendedor) {
        return afiliacionDao.findByVendedorId(vendedor);
    }
}
