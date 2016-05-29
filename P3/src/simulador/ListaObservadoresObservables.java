package simulador;

import java.util.ArrayList;
import java.util.List;

public class ListaObservadoresObservables extends Observable {

	private List<Observador> observadores;
	
	public ListaObservadoresObservables(){
		observadores = new ArrayList<>();
	}
	
	public void incluir(Observador o){
		observadores.add(o);
	}
	
	public void eliminar(Observador o){
		observadores.remove(o);
	}
	
	public List<Observador> getObservadores(){
		return observadores;
	}
	
	public void notificarObservadores(){
		for(Observador o : observadores){
			o.actualizar();
		}
	}
}
