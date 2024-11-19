package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Afiliacion;
import com.uniquindio.edu.skinity.SKINITY.modelo.Referido;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IVendedorDao;
import com.uniquindio.edu.skinity.SKINITY.utilidades.Calculo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VendedorServiceImpl implements VendedorService{
    
    @Autowired
    private IVendedorDao vendedorDao;
    
    @Autowired
    private ReferidoService referidoService;
    
    @Autowired
    private AfiliacionService afiliacionService;
    
    @Override
    public List<Vendedor> listarVendedores() {
        return (List<Vendedor>) vendedorDao.findAll();
    }

    @Override
    public void guardar(Vendedor vendedor) {
        vendedorDao.save(vendedor);
    }

    @Override
    public void eliminar(Vendedor vendedor) {
        vendedorDao.delete(vendedor);
    }

    @Override
    public Vendedor encontrarVendedor(Vendedor vendedor) {
        return vendedorDao.findById(vendedor.getVendedorId()).orElse(null);
    }

    @Override
    public Vendedor actualizarVendedor(Vendedor vendedor) {
        //todo
        return null;
    }

    @Override
    public Vendedor buscarPorIdentificacion(String identificacion) {
        return vendedorDao.findByIdentificacion(identificacion);
    }

    @Override
    public boolean eliminarCompleto(Vendedor vendedor) {
        if (vendedor != null) {
            log.info("Entra a eliminar completamente un vendedor");
            List<Referido> referidos = referidoService.buscarPorReferidoPrincipal(vendedor);
            
            Vendedor vendedorPadre = buscarPorIdentificacion(vendedor.getIdentificacionReferido());
            
            
            for (Referido referido : referidos) {
                Referido referidoNuevo = new Referido();
                referidoNuevo.setVendedorPrincipalId(vendedorPadre);
                referidoNuevo.setVendedorReferidoId(referido.getVendedorReferidoId());
                referidoService.guardar(referidoNuevo);
                
                Vendedor vendedorActualizar = encontrarVendedor(referido.getVendedorReferidoId());
                vendedorActualizar.setIdentificacionReferido(vendedorPadre.getIdentificacion());
            }
            
            for (Referido referido : referidos) {
                referidoService.eliminar(referido);
            }
            
            Referido referidoEliminar = referidoService.buscarPorVendedorReferido(vendedor);
            System.err.println("referido a eliminar " + referidoEliminar.toString());
            
            referidoService.eliminar(referidoEliminar);
            
            Afiliacion afiliacionVendedor = afiliacionService.buscarPorVendedor(vendedor);
            
            afiliacionService.eliminar(afiliacionVendedor);
            
            eliminar(vendedor);
            
            return true;
        }
        log.error("Falla eliminar completamente un vendedor");
        return false;
    }

    @Override
    public Vendedor buscarPorVendedorId(Integer vendedor) {
        return vendedorDao.findByVendedorId(vendedor);
    }

    @Override
    public boolean calcularGanancia(Vendedor vendedor, Double valorVenta) {
        vendedor = encontrarVendedor(vendedor);
        
        Referido referidoPrincipal = referidoService.buscarPorVendedorReferido(vendedor);
        
        Vendedor vendedorPrincipal = referidoPrincipal.getVendedorPrincipalId();
        
        if (referidoPrincipal != null && vendedorPrincipal != null) {
            Double ganancia = vendedorPrincipal.getTotalVentas();
            vendedorPrincipal.setTotalVentas(ganancia + Calculo.calcularGananciaPorcentaje(valorVenta, vendedor.getNivelId().getNombre()));

            guardar(vendedorPrincipal);

            Double totalVentaVendedor = valorVenta - ganancia;
            vendedor.setTotalVentas(vendedor.getTotalVentas() + totalVentaVendedor);
            guardar(vendedor);
            return true;
        }
        return false;
    }
    
    
    
}
