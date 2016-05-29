package simulador;
public class Simulacion extends ListaObservadoresObservables implements Runnable {
	
	private final int INTERVALO = 100; 
	private boolean running = true;
	private int t;
	
	public Simulacion(PanelEtiquetas panelE, PanelBotones panelB){
		this.incluir(panelE);
		this.incluir(panelB);
		t = 0;
	}
	
	public void terminate(){
		running = false;
	}
	
	public void run() {
		while(running){
			try{ 
				Thread.sleep(INTERVALO);
				t += INTERVALO;
			}
			catch(java.lang.InterruptedException e){
				e.printStackTrace();
				running = false;
			}
			
			this.notificarObservadores();
		}
	}
	
	public int getTiempoTranscurrido(){
		return t;
	}
	
	public PanelEtiquetas getPanelEtiquetas(){
		return (PanelEtiquetas) this.getObservadores().get(0);
	}
	
	public PanelBotones getPanelBotones(){
		return (PanelBotones) this.getObservadores().get(1);
	}
}