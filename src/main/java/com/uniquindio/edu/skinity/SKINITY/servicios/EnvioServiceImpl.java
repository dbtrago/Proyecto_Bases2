package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.DetalleVenta;
import com.uniquindio.edu.skinity.SKINITY.modelo.Envio;
import com.uniquindio.edu.skinity.SKINITY.modelo.Estado;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IEnvioDao;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnvioServiceImpl implements EnvioService{

    @Autowired
    private IEnvioDao envioDao;
    
    @Autowired
    private EstadoService estadoService;

    @Override
    public List<Envio> listarEnvio() {
        return (List<Envio>) envioDao.findAll();
    }

    @Override
    public void guardar(Envio envio) {
        envioDao.save(envio);
    }

    @Override
    public void eliminar(Envio envio) {
        envioDao.delete(envio);
    }

    @Override
    public Envio encontrarEnvio(Envio envio) {
        return envioDao.findById(envio.getEnvioId()).orElse(null);
    }

    @Override
    public boolean crearEnvioCompleto(DetalleVenta detalleVenta) {
        
        Envio envio = new Envio();
        
        String descripcion = "El envio se genera a la fecha de creacion instantanea, sin inconvenientes";
        Date fecha = new Date();
        Estado estado= estadoService.encontrarPorDescripcion("CREACION");
        
        envio.setDescripcion(descripcion);
        envio.setFecha(fecha.toString());
        envio.setEstadoId(estado);
        envio.setDetalleventaId(detalleVenta);
        
        guardar(envio);
        
        return true;
    }

    @Override
    public Envio buscarPorDetalleVenta(DetalleVenta detalleVenta) {
        return envioDao.findByDetalleventaId(detalleVenta);
    }

 

    
    
    
}
