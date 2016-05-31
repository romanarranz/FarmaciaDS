package testSimulador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import simulador.Interfaz;
import simulador.ListaObservadoresObservables;
import simulador.Observador;
import simulador.Simulacion;

public class ListaObservadoresObservablesTest extends Thread {
	
	private Interfaz i;
	private Simulacion s;
	private List<Observador> l;
	private ObservadorTestListener oT;
	private boolean err;
	
	@BeforeClass
	public static void init(){
		System.out.println("# ListaObservadoresObservablesTest");
	}
	
	@Before
	public void testInit(){
		oT = new ObservadorTestListener();
		i = new Interfaz();
		s = i.getSimulacion();
		l = s.getObservadores();
	    err = false;
	}
	
	@After
	public void destroy(){
		ListaObservadoresObservables.eventosProducidos = null;
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
		System.out.print("\ttestInsertaObservador...");
		try {
			// cuando se llama cualquier @Test se ejecuta @Before por lo tanto se habrá inicialzado
			// la interfaz y se habran añadido 2 observadores a la lista de observadores
			assertEquals(l.size(), 2);
			
			// comprobamos adicionalmente que pertenecen a la clase Observador
			for(Object o: l){
				assertTrue(o instanceof Observador);
			}
			
			// no obstante pruebo a añadir otro mas
			int size = l.size();
			s.incluir(oT);
			assertTrue(size < l.size());
		}
		catch(AssertionError e){
			System.out.print("\tnot ok\n");
			err = true;
			throw e;
		}
		
		if(!err) System.out.print("\tok\n");
	}
	
	@Test
	public void testNotificarObservadores(){
		System.out.print("\ttestNotificarObservadores...");
		try{
			List<Object> listaEventos = new ArrayList<Object>();
			s.incluir(oT);
			
			// arrancamos el motor
			MouseEvent me = new MouseEvent(new Label(), 0, 0, 0, 0, 0, 0, false);
			ActionEvent ae = new ActionEvent(me.getSource(), me.getID(), me.paramString());
			
			// encender
			i.getSimulacion().getPanelBotones().BotonEncenderActionPerformed(ae);
			
			// Acelerador: pulso boton
			i.getSimulacion().getPanelBotones().toggleAcelerador();
			i.getSimulacion().getPanelBotones().BotonAcelerarActionPerformed(ae);
			
			// Mantener velocidad
			i.getSimulacion().getPanelBotones().BotonMantenerActionPerformed(ae);			
			
			// Apagando el motor
			i.getSimulacion().getPanelBotones().BotonEncenderActionPerformed(ae);
			
			try{
				Thread.sleep(110);
			}
			catch(java.lang.InterruptedException e){
				e.printStackTrace();	
			}
			
			System.out.println();
			for (Map.Entry<Observador, List<Object>> entry : ListaObservadoresObservables.eventosProducidos.entrySet())				
			    System.out.println(entry.getKey().getClass().getSimpleName() + "\n\t" + entry.getValue().toString());
			
		}
		catch(AssertionError e){
			System.out.print("\tnot ok\n");
			err = true;
			throw e;
		}
		catch(Exception e){
			System.out.print("\tnot ok\n");
			err = true;
			e.printStackTrace();
			throw e;
		}
		
		if(!err) System.out.print("\tok\n");
	}
	
	@Test
	public void testEliminaObservador(){
		System.out.print("\ttestEliminaObservador...");
		try {
			
			// elimino un observador
			int size = l.size();
			l.remove(0);
			
			assertTrue(size > l.size());
			
			// elimino todos los observadores
			l.clear();
			assertEquals(l.size(), 0);
		}
		catch(AssertionError e){
			System.out.print("\tnot ok\n");
			err = true;
			throw e;
		}
		
		if(!err) System.out.print("\tok\n");
	}
}
