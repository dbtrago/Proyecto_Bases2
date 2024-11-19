package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Sesion;
import com.uniquindio.edu.skinity.SKINITY.repositorio.ISesionDao;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    @Autowired
    private ISesionDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Sesion usuario = usuarioDao.findByUsuario(username);
        
        if(usuario == null){
            log.error("No se encuentra el usuario");
            throw new UsernameNotFoundException(username);
        }
        log.info("ROL " + usuario.getRol());
        var roles = new ArrayList<GrantedAuthority>();
        
        roles.add(new SimpleGrantedAuthority(usuario.getRol()));
        
        return new User(usuario.getUsuario(), usuario.getContrasena(), roles);
    }
    
}
