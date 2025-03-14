/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package appconsole;


import java.time.LocalDateTime;
import java.util.List;

import modelo.Bilhete;
import modelo.Veiculo;
import regras_negocio.Fachada;

public class Consultar {

	public Consultar() {
		try {			
			Fachada.inicializar();
			System.out.println("\n---quais os bilhetes não foram finalizados (com valor igual a 0) ?");
			for (Bilhete b : Fachada.listarBilhetesAtivos()) {				
				System.out.println(b);
			}

			System.out.println("\n---qual os veículos com bilhete pago na data 20/11/2024");
			// Definir a data desejada no formato LocalDateTime
			LocalDateTime dataSaida = LocalDateTime.of(2024, 11, 20, 0, 0);
			for (Veiculo v: Fachada.listarVeiculosPorDataSaida(dataSaida)) {
			    System.out.println(v);				
			}			
			
			System.out.println("\n---quais os veiculos contendo mais de 1 bilhete");
			for (Veiculo v: Fachada.listarVeiculosMaisDeNBilhetes(1)) {
				System.out.println(v);
			}
			
		} catch (Exception e) {
			System.out.println("excecao=" + e.getMessage());
		    e.printStackTrace();
		} finally {
			Fachada.finalizar();
			System.out.println("\nfim da aplicacao");
		}
	}

	// =================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
