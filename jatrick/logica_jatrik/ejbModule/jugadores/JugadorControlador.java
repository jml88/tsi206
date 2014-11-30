package jugadores;

import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;
import fabricas.HomeFactory;
import interfaces.IJugadorControlador;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
		
		
		List<String> nombres = null;
        List<String> apellidos = null;
		
		try {
			nombres = Files.readAllLines(FileSystems.getDefault().getPath("/home/juan/git/tsi206/jatrick/logica_jatrik/ejbModule/META-INF/archivos/", "nombres"));
			apellidos = Files.readAllLines(FileSystems.getDefault().getPath("/home/juan/git/tsi206/jatrick/logica_jatrik/ejbModule/META-INF/archivos/", "apellidos"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Random random = new Random();
		for (int i = 0; i < cant; i++){

			//TODO: Hacer bien despues!!
			Jugador j = new Jugador();			
			
			int max_nombres = nombres.size();
			int max_apellidos = apellidos.size();
			
			String nombre = nombres.get(random.nextInt(max_nombres -1));
			String apellido1 = apellidos.get(random.nextInt(max_apellidos -1));
			String apellido2 = apellidos.get(random.nextInt(max_apellidos -1));
			
//			j.setNombre("Fulano" + String.valueOf(i));
//			j.setApellido1("Detal" + String.valueOf(i));
//			j.setApellido2("Paracual" + String.valueOf(i));
			
			j.setNombre(nombre.substring(0,1).toUpperCase(Locale.US) + nombre.substring(1).toLowerCase(Locale.US));
			j.setApellido1(apellido1.substring(0,1).toUpperCase(Locale.US) + apellido1.substring(1).toLowerCase(Locale.US));
			j.setApellido2(apellido2.substring(0,1).toUpperCase(Locale.US) + apellido2.substring(1).toLowerCase(Locale.US));
			
			int edad = random.nextInt(3) + 17;
			j.setEdad(edad);
			
			int velocidad = random.nextInt(98)+1;
			int tecnica = random.nextInt(98)+1;
			int ataque = random.nextInt(98)+1;
			int defensa = random.nextInt(98)+1;
			int porteria = random.nextInt(98)+1;
			
			j.setVelocidad(velocidad);
			j.setTecnica(tecnica);
			j.setAtaque(ataque);
			j.setDefensa(defensa);
			j.setPorteria(porteria);
			j.setEntrenamiento(null);
			
			j.setEquipo(equipo);
			
			j.setSalario(j.calcularSueldoJugador());
			
			jugadores.add(j);
		
		}
				
		return jugadores;
		
	}

	@Override
	public List<Jugador> listarJugador(int idEquipo) throws NoExisteEquipoExcepcion {
		Equipo e = hf.getEquipoControlador().findEquipo(idEquipo);
		if(e == null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: " + idEquipo);
		}
		return em.createQuery("Select j From Jugador j Where j.equipo.codigo = :idEquipo")
				.setParameter("idEquipo", idEquipo)
				.getResultList();
	}

}
