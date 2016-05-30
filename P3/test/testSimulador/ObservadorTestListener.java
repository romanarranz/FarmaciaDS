package testSimulador;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;

public class ObservadorTestListener implements TestListener {
	
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFailure(Test test, AssertionFailedError e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endTest(Test test) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startTest(Test test) {
		// TODO Auto-generated method stub
		
	}

}
