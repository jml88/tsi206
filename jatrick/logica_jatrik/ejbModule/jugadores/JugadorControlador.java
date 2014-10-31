package jugadores;

import equipos.Equipo;
import fabricas.HomeFactory;
import interfaces.IJugadorControlador;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named
public class JugadorControlador implements IJugadorControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public int crearJugador() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Jugador findJugador(int codJugador) {
		return em.find(Jugador.class, codJugador);
	}
	
	@Override
	public Set<Jugador> generarJugadores(int cant, Equipo equipo){
		Set<Jugador> jugadores = new HashSet<Jugador>();
		
		Random random = new Random();
		for (int i = 0; i < cant; i++){

			//TODO: Hacer bien despues!!
			Jugador j = new Jugador();
			j.setNombre("Fulano" + String.valueOf(i));
			j.setApellido1("Detal" + String.valueOf(i));
			j.setApellido2("Paracual" + String.valueOf(i));
			
			int edad = random.nextInt(3) + 17;
			j.setEdad(edad);
			
			int velocidad = random.nextInt(4)+1;
			int tecnica = random.nextInt(4)+1;
			int ataque = random.nextInt(4)+1;
			int defensa = random.nextInt(4)+1;
			int porteria = random.nextInt(4)+1;
			
			j.setVelocidad(velocidad);
			j.setTecnica(tecnica);
			j.setAtaque(ataque);
			j.setDefensa(defensa);
			j.setPorteria(porteria);
			j.setEntrenamiento(null);
			
			j.setEquipo(equipo);
			
			jugadores.add(j);
		
		}
				
		return jugadores;
		
	}

}
