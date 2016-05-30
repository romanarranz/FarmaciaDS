package testSimulador;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestResult;
import simulador.Interfaz;
import simulador.Observador;
import simulador.Simulacion;

public class ListaObservadoresObservablesTest {
	
	private Interfaz i;
	private Simulacion s;
	private List<Observador> l;
	private ObservadorTestListener oT;
	private TestResult result;
	private boolean err;
	
	@BeforeClass
	public static void init(){
		System.out.println("# ListaObservadoresObservablesTest");
	}
	
	@Before
	public void testInit(){
		i = new Interfaz();
		s = i.getSimulacion();
		l = s.getObservadores();
		result = new TestResult();
	    result.addListener(oT);
	    err = false;
	}
	
	@Test
	public void testInicializacion(){
		System.out.print("\ttestInicializacion...");
		try {
			assertNotNull(l);
			assertTrue(l.get(0) instanceof Observador);	
		}
		catch(AssertionError e){
			System.out.print("\tnot ok\n");
			err = true;
			throw e;
		}
		
		if(!err) System.out.print("\tok\n");
	}
	
	@Test
	public void testInsertaObservador(){
		
	}
	
	@Test
	public void testNotificarObservadores(){
		// añadir a la lista de observadores los objetos que se espera que tendra la lista de eventos:
		// 1- objeto correspondiente al inicio del test
		// 2- otro correspondiente a la llamada de actualizar
		
		// COMPARAR esta lista con la que tiene el escuchador ficticio... (ObservadorTestListener)
	}
	
	@Test
	public void testEliminaObservador(){
		
	}
}
