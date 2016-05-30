package testSimulador;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import simulador.Observador;

/*
Para probarlo se tiene que unir todos lso observadores de la palicacion, panel de etiquetas y panel de botones 
en una misma clase observadora.

Ahora una instancia de esa clase observadora hay que utilizarla dentro de un listener que nos permita asociarle
observadores, de tal manera que cada vez que se acciona algo en ese listener se llama a notificar a esos observadores.

Ejemplo arranco, reinicio, freno pongo el coche a 0 y paro el motor.
Lo que haremos será comprobar que todos los observadores han sido notificados de todos esos eventos a través
del falso listener. Es decir comprobaremos dos listas. una por cada obsverador.

Comprobamos la lista de eventos que hemos disparado (string) con las listas de esos observadores (lista de strging tb)
*/

public class ObservadorTestListener implements TestListener, Observador {
	
	private List<String> eventList;
	private boolean err;
	
	@BeforeClass
	public static void init(){
		System.out.println("# ObservadorTestListener");
	}
	
	@Before
	public void testInit(){
		eventList = new ArrayList<String>();
		err = false;
	}
	
	@Override
	public void addError(Test test, Throwable e) {
		eventList.add("error");
		err = true;
		System.out.print("\tnot ok\n");
	}

	@Override
	public void addFailure(Test test, AssertionFailedError e) {
		eventList.add("failure");
		System.out.print("\tnot ok\n");
		err = true;
		throw e;		
	}

	@Override
	public void endTest(Test test) {
		eventList = null;
		
		if(!err) System.out.print("\tok\n");
	}

	@Override
	public void startTest(Test test) {
		eventList.add("start");
	}

	@Override
	public void actualizar() {
		eventList.add("actualizar");
	}
}
