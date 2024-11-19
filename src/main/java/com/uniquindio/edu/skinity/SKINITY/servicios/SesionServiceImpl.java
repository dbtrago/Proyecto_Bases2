package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Afiliacion;
import com.uniquindio.edu.skinity.SKINITY.modelo.Estado;
import com.uniquindio.edu.skinity.SKINITY.modelo.Nivel;
import com.uniquindio.edu.skinity.SKINITY.modelo.Referido;
import com.uniquindio.edu.skinity.SKINITY.modelo.Sesion;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import com.uniquindio.edu.skinity.SKINITY.repositorio.ISesionDao;
import com.uniquindio.edu.skinity.SKINITY.utilidades.Constantes;
import com.uniquindio.edu.skinity.SKINITY.utilidades.EncriptarContrasena;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SesionServiceImpl implements SesionService{

    @Autowired
    private ISesionDao sesionDao;
    
    @Autowired
    private VendedorService vendedorService;
    
    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private AfiliacionService afiliacionService;
    
    @Autowired
    private ReferidoService referidoService;
    
    @Override
    public List<Sesion> listarSesion() {
        return (List<Sesion>) sesionDao.findAll();
    }

    @Override
    public void guardar(Sesion vendedor) {
        sesionDao.save(vendedor);
    }

    @Override
    public void eliminar(Sesion vendedor) {
        sesionDao.delete(vendedor);
    }

    @Override
    public Sesion encontrarSesion(Sesion vendedor) {
        return sesionDao.findById(vendedor.getSesionId()).orElse(null);
    }

    @Override
    public Sesion buscarPorUsuario(String usuario) {
        return sesionDao.findByUsuario(usuario);
    }

    @Override
    public boolean construirSesion(Vendedor vendedor) {
        if (buscarPorUsuario(vendedor.getIdentificacion()) == null) {
            vendedorService.guardar(vendedor);
            
            Sesion sesion = new Sesion();
            
            Vendedor vendedorReferido = vendedorService.buscarPorIdentificacion(vendedor.getIdentificacionReferido());
            
            if (vendedorReferido != null) {
                log.info("Entra a crear nuevo referido");
                Referido nuevoReferido = new Referido();
                nuevoReferido.setVendedorPrincipalId(vendedorReferido);
                nuevoReferido.setVendedorReferidoId(vendedor);
                
                referidoService.guardar(nuevoReferido);
            }
                        
            sesion.setVendedor(vendedor);
            sesion.setContrasena(EncriptarContrasena.encriptarPassword(vendedor.getIdentificacion()));
            sesion.setUsuario(vendedor.getIdentificacion());
            sesion.setRol(Constantes.ROLE_USER);
            
            Estado estado = estadoService.encontrarPorDescripcion("ACTIVO");
            
            Date date = new Date();
            
            if (estado != null) {
                log.info("Entra a guardar afiliacion y sesion");
                Afiliacion afiliacion = new Afiliacion();
                afiliacion.setEstadoId(estado);
                afiliacion.setObservacion("Cliente nuevo");
                afiliacion.setFechaInicial(date.toString());
                afiliacion.setVendedorId(vendedor);
                
                afiliacionService.guardar(afiliacion);
                guardar(sesion);
                return true;
            }
            else 
                return false;
        }
        return false;
    }
    
}
