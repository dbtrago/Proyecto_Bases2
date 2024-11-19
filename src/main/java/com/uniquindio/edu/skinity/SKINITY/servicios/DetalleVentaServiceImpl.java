package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Cliente;
import com.uniquindio.edu.skinity.SKINITY.modelo.DetalleVenta;
import com.uniquindio.edu.skinity.SKINITY.modelo.Inventario;
import com.uniquindio.edu.skinity.SKINITY.modelo.Producto;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IDetalleVentaDao;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DetalleVentaServiceImpl implements DetalleVentaService{

    @Autowired
    private IDetalleVentaDao detalleVentaDao;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private InventarioService inventarioService;
    
    @Autowired
    private VendedorService vendedorService;
    
    @Autowired
    private EnvioService envioService;

    @Override
    public List<DetalleVenta> listarDetalleVenta() {
        return (List<DetalleVenta>) detalleVentaDao.findAll();
    }

    @Override
    public void guardar(DetalleVenta detalleVenta) {
        detalleVentaDao.save(detalleVenta);
    }

    @Override
    public void eliminar(DetalleVenta detalleVenta) {
        detalleVentaDao.delete(detalleVenta);
    }

    @Override
    public DetalleVenta encontrarDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaDao.findById(detalleVenta.getDetalleventaId()).orElse(null);
    }    

    @Override
    public boolean agregarDetalleVenta(Vendedor vendedor, DetalleVenta detalleVenta, Cliente cliente, Producto producto) {
        
        Inventario inventario = inventarioService.buscarPorProducto(producto);
        if (inventario.getCantidad() < detalleVenta.getCantidad()) {
            log.info("No se puede generar este detalle venta");
            return false;
        }
        if(cliente != null){
            clienteService.guardar(cliente);
            
            detalleVenta.setClienteId(cliente);
            detalleVenta.setProductoId(producto);
            detalleVenta.setValortotal(detalleVenta.getCantidad() * producto.getPrecio());
            detalleVenta.setProductoId(producto);
            
            guardar(detalleVenta);
            
            if (inventarioService.actualizarCantidad(producto, detalleVenta.getCantidad())) {
                log.info("Se actualizo el inventario");
            }
            
            if(vendedorService.calcularGanancia(vendedor, detalleVenta.getCantidad() * producto.getPrecio())){
                log.info("Calculada la ganancia");
            }
            
            if(envioService.crearEnvioCompleto(detalleVenta)){
                log.info("Se crea con exito el envio de la venta");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<DetalleVenta> buscarDetalleVentaPorVendedor(Vendedor vendedor) {
        return detalleVentaDao.findByVendedorId(vendedor);
    }
    
}
