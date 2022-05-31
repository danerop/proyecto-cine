package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Suscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
// de spring
public class ConexionBaseDeDatosTest extends SpringTest{

    @Test
    @Transactional @Rollback
    public void pruebaConexion(){
        assertThat(session().isConnected()).isTrue();
    }


    @Test
    @Transactional @Rollback
    public void crearUsuario(){
    	Suscripcion sub = new Suscripcion();
    	sub.setTipo("premium");
    	
        Usuario usuario = new Usuario();
        usuario.setEmail("seba@gmail.com");
        usuario.setPassword("1234");
        
        session().save(sub);
        
        assertNotNull(sub.getId());
        
        usuario.setSuscripcion(sub);
        session().save(usuario);
        
        assertNotNull(usuario.getId());

        assertNotNull(usuario.getSuscripcion().getId());
    }


}
