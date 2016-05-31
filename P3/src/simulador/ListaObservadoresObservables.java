package simulador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListaObservadoresObservables extends Observable {

	private List<Observador> observadores;
	public static Map<Observador, List<Object>> eventosProducidos;
	
	public ListaObservadoresObservables(){
		observadores = new ArrayList<>();
	}
	
	public void incluir(Observador o){
		observadores.add(o);
		eventosProducidos.put(o, new ArrayList<Object>());
	}
	
	public void eliminar(Observador o){
		observadores.remove(o);
		eventosProducidos.remove(o);
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
