package appconsole;

import modelo.Bilhete;
import modelo.Veiculo;
import regras_negocio.Fachada;

public class Listar {
	
	public Listar() {
		
		try {
			Fachada.inicializar();
			
			// Listar Veiculos
			System.out.println("\nListagem de veiculos");
			for(Veiculo v: Fachada.listarVeiculos())
				System.out.println(v);
		
			// Listar Bilhetes
			System.out.println("\nListagem de bilhetes");
			for(Bilhete b: Fachada.listarBilhetes())
				System.out.println(b);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
			System.out.println("Fim do programa");
		}
		
	}
	
	public static void main(String[] args) {
		new Listar();
	}
}
