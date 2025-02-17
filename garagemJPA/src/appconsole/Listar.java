package appconsole;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Bilhete;
import modelo.Veiculo;

public class Listar {

	private EntityManager manager;
	
	public Listar() {
		manager = Util.conectarBanco();
		
		try {
			// Listar Veiculos
			System.out.println("\nListagem de veiculos");
			TypedQuery<Veiculo> consulta1 = manager.createQuery("select v from Veiculo v", Veiculo.class);
			List<Veiculo> resultados1 = consulta1.getResultList();
			for (Veiculo v : resultados1)
				System.out.println(v);
		
			// Listar Bilhetes
			System.out.println("\nListagem de bilhetes");
			TypedQuery<Bilhete> consulta2 = manager.createQuery("select b from Bilhete b", Bilhete.class);
			List<Bilhete> resultados2 = consulta2.getResultList();
			for (Bilhete b : resultados2)
				System.out.println(b);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Util.fecharBanco();
		System.out.println("Fim do programa");
	}
	
	public static void main(String[] args) {
		new Listar();
	}
}
