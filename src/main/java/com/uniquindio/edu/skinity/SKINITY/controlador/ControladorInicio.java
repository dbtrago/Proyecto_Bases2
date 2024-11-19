package com.uniquindio.edu.skinity.SKINITY.controlador;

import com.uniquindio.edu.skinity.SKINITY.modelo.Cliente;
import com.uniquindio.edu.skinity.SKINITY.modelo.DetalleVenta;
import com.uniquindio.edu.skinity.SKINITY.modelo.Envio;
import com.uniquindio.edu.skinity.SKINITY.modelo.Estado;
import com.uniquindio.edu.skinity.SKINITY.modelo.Inventario;
import com.uniquindio.edu.skinity.SKINITY.modelo.Nivel;
import com.uniquindio.edu.skinity.SKINITY.modelo.Producto;
import com.uniquindio.edu.skinity.SKINITY.modelo.Sesion;
import com.uniquindio.edu.skinity.SKINITY.modelo.TipoProducto;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import com.uniquindio.edu.skinity.SKINITY.servicios.DetalleVentaService;
import com.uniquindio.edu.skinity.SKINITY.servicios.EnvioService;
import com.uniquindio.edu.skinity.SKINITY.servicios.EstadoService;
import com.uniquindio.edu.skinity.SKINITY.servicios.InventarioService;
import com.uniquindio.edu.skinity.SKINITY.servicios.NivelService;
import com.uniquindio.edu.skinity.SKINITY.servicios.ProductoService;
import com.uniquindio.edu.skinity.SKINITY.servicios.SesionService;
import com.uniquindio.edu.skinity.SKINITY.servicios.TipoProductoService;
import com.uniquindio.edu.skinity.SKINITY.servicios.VendedorService;
import com.uniquindio.edu.skinity.SKINITY.utilidades.Constantes;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
    
@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private SesionService sesionService;
    
    @Autowired
    private VendedorService vendedorService;
    
    @Autowired
    private InventarioService inventarioService;
    
    @Autowired
    private TipoProductoService tipoProductoService;
    
    @Autowired
    private NivelService nivelService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private DetalleVentaService detalleVentaService;
    
    @Autowired
    private EnvioService envioService;
    
    @Autowired
    private EstadoService estadoService;
    
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        
        Sesion sesion = null;
        
        if(user != null){
            sesion = sesionService.buscarPorUsuario(user.getUsername());
        }
        
        if (sesion != null) {
            switch (sesion.getRol()) {
                case Constantes.ROLE_ADMIN -> {
                    var vendedores = vendedorService.listarVendedores();
                    var inventarios = inventarioService.listarInventario();
                    model.addAttribute("vendedores", vendedores);
                    model.addAttribute("inventarios", inventarios);
                    return "index";
                }
                case Constantes.ROLE_USER ->{
                    var detalleventas = detalleVentaService.buscarDetalleVentaPorVendedor(sesion.getVendedor());
                    model.addAttribute("vendedor", sesion.getVendedor());
                    model.addAttribute("detalleventas", detalleventas);
                    return "indexVendedor";
                }
                default -> throw new AssertionError();
            }
        }else
            return "/errores/errorInicioSesion";
    }
    

    @GetMapping("/errores/403")
    public String accessDenied() {
        return "errores/403"; // Nombre de la plantilla de Thymeleaf (403.html)
    }
    
    @GetMapping("/cargarInformacionSesion")
    public String cargarInformacionSesion(Model model) {
        var niveles = nivelService.listarNivel();
        if (niveles != null) {
            model.addAttribute("niveles", niveles);
            return "crearSesion";
        }
        return "redirect:/errores/errorObtenerInformacion";
    }   
    
    @GetMapping("/observarProducto/{inventarioId}")
    public String observarProducto(Inventario inventario, Model model) {
        inventario = inventarioService.encontrarInventario(inventario);
        
        
        if (inventario != null) {
            model.addAttribute("inventario", inventario);
            return "gestionarInventario";
        }
        
        return "redirect:/errores/errorObtenerInformacion";
        
    }
    
    @PostMapping("/guardarInventario")
    public String guardarInventario(@Validated Inventario inventario, Errors errores) {
        if (errores.hasErrors()) {
            return "redirect:/errores/errorProceso";
        }
        inventarioService.guardar(inventario);

        return "redirect:/";
    }
    
    @GetMapping("/cargarInformacionProducto")
    public String cargarInformacionProducto(Model model) {
        List<TipoProducto> tipoProductos = tipoProductoService.listarTipoProducto();

        model.addAttribute("tipoProductos", tipoProductos);

        return "crearProducto";
    }
    
    @PostMapping("/crearProducto")
    public String crearProducto(@Validated Producto producto, @Validated Inventario inventario, Errors errores) {
        return (inventarioService.agregarProducto(producto, inventario)) ? "redirect:/" : "/errores/errorCreacion";
    }
    
    @PostMapping("/crearNuevaSesion")
    public String crearSesionVendedor(@Validated Vendedor vendedor, Errors errores) {
        return (sesionService.construirSesion(vendedor)) ? "redirect:/" : "/errores/errorCreacion";
    }
    
    @GetMapping("/eliminarVendedor/{vendedorId}")
    public String eliminarVendedor(Vendedor vendedor,Model model) {
        vendedor = vendedorService.encontrarVendedor(vendedor);
        if (vendedorService.eliminarCompleto(vendedor)) {
            Sesion sesionVendedor = sesionService.buscarPorUsuario(vendedor.getIdentificacion());
            sesionService.eliminar(sesionVendedor);
            return "redirect:/";
        }
        return "/errores/errorCreacion";
    }
    
    @GetMapping("/cargarInformacionVenta/{vendedorId}")
    public String cargarInformacionVenta(Vendedor vendedor,Model model) {
        List<Producto> productos = productoService.listarProducto();
        model.addAttribute("productos", productos);
        model.addAttribute("vendedor", vendedor);
        
        return "crearVenta";
    }
    
    @PostMapping("/crearVenta/{vendedorId}")
    public String crearVenta(@Validated Vendedor vendedor, @Validated DetalleVenta detalleVenta,@Validated Cliente cliente,@Validated Producto producto, Errors errores) {
        
        vendedor = vendedorService.encontrarVendedor(vendedor);
        producto = productoService.encontrarProducto(producto);
        
        return (detalleVentaService.agregarDetalleVenta(vendedor, detalleVenta,cliente,producto)) ? "redirect:/" : "/errores/errorCreacion";
    }
    
    @GetMapping("/consultarEnvio/{detalleventaId}")
    public String observarEnvio(DetalleVenta detalleVenta, Model model) {
        detalleVenta = detalleVentaService.encontrarDetalleVenta(detalleVenta);
        List<Estado> estados = estadoService.listarEstado();
        
        if (detalleVenta != null) {
            model.addAttribute("detalleventa", detalleVenta);
            model.addAttribute("estados", estados);
            model.addAttribute("envio", envioService.buscarPorDetalleVenta(detalleVenta));
            return "gestionarEnvio";
        }
        
        return "redirect:/errores/errorObtenerInformacion";
    }
    
    @PostMapping("/guardarEnvio")
    public String guardarEnvio(@Validated Envio envio, Errors errores) {
        if (errores.hasErrors()) {
            return "redirect:/errores/errorProceso";
        }
        envioService.guardar(envio);

        return "redirect:/";
    }
    
    @GetMapping("/observarVendedor/{vendedorId}")
    public String observarVendedor(Vendedor vendedor, Model model) {
        vendedor = vendedorService.encontrarVendedor(vendedor);
        List<Nivel> niveles = nivelService.listarNivel();
        
        if (vendedor != null) {
            model.addAttribute("niveles", niveles);
            model.addAttribute("vendedor", vendedor);
            return "gestionarVendedor";
        }
        
        return "redirect:/errores/errorObtenerInformacion";
    }
    
    @PostMapping("/guardarVendedor")
    public String guardarVendedor(@Validated Vendedor vendedor, Errors errores) {
        
        if (errores.hasErrors()) {
            return "redirect:/errores/errorProceso";
        }
        vendedorService.guardar(vendedor);

        return "redirect:/";
    }
    
}


