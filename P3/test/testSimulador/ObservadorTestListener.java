package testSimulador;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import simulador.Observador;

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
