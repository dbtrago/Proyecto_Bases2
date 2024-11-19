package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Referido;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IReferidoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferidoServiceImpl implements ReferidoService{

    @Autowired
    private IReferidoDao referidoDao;

    @Override
    public List<Referido> listarReferido() {
        return (List<Referido>) referidoDao.findAll();
    }

    @Override
    public void guardar(Referido referido) {
        referidoDao.save(referido);
    }

    @Override
    public void eliminar(Referido referido) {
        referidoDao.delete(referido);
    }

    @Override
    public Referido encontrarReferido(Referido referido) {
        return referidoDao.findById(referido.getReferidoId()).orElse(null);
    }

    @Override
    public List<Referido> buscarPorReferidoPrincipal(Vendedor vendedor) {
        return referidoDao.findByVendedorPrincipalId(vendedor);
    }

    @Override
    public Referido buscarPorVendedorReferido(Vendedor vendedor) {
        return referidoDao.findByVendedorReferidoId(vendedor);
    }

    
}
