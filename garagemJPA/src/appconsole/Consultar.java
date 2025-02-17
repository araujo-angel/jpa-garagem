/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package appconsole;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import modelo.Bilhete;
import modelo.Veiculo;

public class Consultar {
	protected EntityManager manager;

	public Consultar() {
		try {
			manager = Util.conectarBanco();
			
			TypedQuery<Bilhete> queryB ;
			TypedQuery<Veiculo> queryV ;
			List<Bilhete> bilhetes;
			List<Veiculo> veiculos;
			String jpql;
			
			System.out.println("\n---quais os bilhetes não foram finalizados (com valor igual a 0) ?");
			jpql = "select b from Bilhete b where b.valorPago = 0.0";
			queryB = manager.createQuery(jpql, Bilhete.class);
			bilhetes = queryB.getResultList();
			for (Bilhete b : bilhetes)
				System.out.println(b);

			System.out.println("\n---qual os veículos com bilhete pago na data 20/11/2024");
			// Definir a data desejada no formato LocalDateTime
			LocalDateTime dataDesejada = LocalDateTime.of(2024, 11, 20, 0, 0);
			// Escrever a consulta JPQL usando um parâmetro
			jpql = "SELECT v FROM Veiculo v JOIN v.bilhetes b WHERE b.dataHoraFinal BETWEEN :inicio AND :fim";
			// Criar a query e definir os parâmetros
			queryV = manager.createQuery(jpql, Veiculo.class);
			queryV.setParameter("inicio", dataDesejada);
			queryV.setParameter("fim", dataDesejada.plusDays(1).minusNanos(1)); // Final do dia 20/11/2024
			// Executar a consulta
			veiculos = queryV.getResultList();
			// Exibir os resultados
			for (Veiculo v : veiculos) {
			    System.out.println(v);
			}
			
			
			System.out.println("\n---quais os veiculos contendo mais de 1 bilhete");
			jpql = "select v from Veiculo v where size(v.bilhetes) > 1";
			queryV = manager.createQuery(jpql, Veiculo.class);
			veiculos = queryV.getResultList();
			for (Veiculo v : veiculos)
				System.out.println(v);
			
		} catch (Exception e) {
			System.out.println("excecao=" + e.getMessage());
		}
		Util.fecharBanco();
		System.out.println("\nfim da aplica��o");
	}

	// =================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
