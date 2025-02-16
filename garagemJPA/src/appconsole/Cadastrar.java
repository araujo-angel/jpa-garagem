/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package appconsole;

import java.time.LocalDateTime;

import jakarta.persistence.EntityManager;
import modelo.Veiculo;
import modelo.Bilhete;

public class Cadastrar {
	private EntityManager manager;

	public Cadastrar() {
		try {
			manager = Util.conectarBanco();
			System.out.println("Cadastrando veículos e bilhetes...");
			
			Veiculo v1, v2, v3, v4, v5;
			v1 = new Veiculo("ABC1234");
			v2 = new Veiculo("DEF2345");
			v3 = new Veiculo("GHI3456");
			v4 = new Veiculo("XYZ9876");
			v5 = new Veiculo("UVW6543");
			
			Bilhete b1, b2, b3, b4, b5;
			b1 = new Bilhete(v1, LocalDateTime.of(2024, 11, 20, 8, 0, 0, 0));
			b2 = new Bilhete(v1, LocalDateTime.of(2025, 02, 20, 8, 0, 0, 0));
			b3 = new Bilhete(v2, LocalDateTime.of(2025, 02, 20, 8, 0, 0, 0));
			b4 = new Bilhete(v2, LocalDateTime.of(2024, 11, 20, 8, 0, 0, 0));
			b5 = new Bilhete(v3, LocalDateTime.of(2024, 11, 20, 8, 0, 0, 0));

			v1.adicionarBilhete(b1);
			v1.adicionarBilhete(b2);
			v2.adicionarBilhete(b3);
			v2.adicionarBilhete(b4);
			v3.adicionarBilhete(b5);
			
			manager.getTransaction().begin();
			manager.persist(v1);
			manager.getTransaction().commit();

			manager.getTransaction().begin();
			manager.persist(v2);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			manager.persist(v3);
			manager.getTransaction().commit();
			
			manager.getTransaction().begin();
			manager.persist(v4);
			manager.getTransaction().commit();
			
			
			//ATUALIZANDO PARA TESTAR COM UM BILHETE QUE TENHA HORA DE SAÍDA
			b4.setDataHoraFinal(LocalDateTime.of(2024, 11, 20, 9, 0, 0, 0));
			manager.getTransaction().begin();
			manager.merge(v2);
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("excecao=" + e.getMessage());
		}

		Util.fecharBanco();
		System.out.println("\nfim da aplica��o");
	}

	// =================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
	// =================================================

}
