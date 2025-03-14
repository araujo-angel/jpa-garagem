/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package appconsole;

import java.time.LocalDateTime;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		System.out.println("Comecando...");
		try {
			Fachada.inicializar();
			
			System.out.println("Cadastrando veiculos...");
			Fachada.criarVeiculo("ABC1234");
			Fachada.criarVeiculo("DEF2345");
			Fachada.criarVeiculo("GHI3456");
			Fachada.criarVeiculo("XYZ9876");
			Fachada.criarVeiculo("UVW6543");

			System.out.println("Cadastrando bilhetes...");
			Fachada.criarBilhete("ABC1234", LocalDateTime.of(2024, 11, 20, 8, 0, 0, 0));
			Fachada.registrarSaida("ABC1234", LocalDateTime.now());
			Fachada.criarBilhete("ABC1234", LocalDateTime.of(2024, 11, 21, 8, 0, 0, 0));
			Fachada.criarBilhete("XYZ9876", LocalDateTime.of(2025, 02, 20, 8, 0, 0, 0));
			Fachada.criarBilhete("DEF2345", LocalDateTime.of(2025, 02, 20, 7, 0, 0, 0));
			Fachada.criarBilhete("UVW6543", LocalDateTime.of(2024, 11, 20, 8, 0, 0, 0));
			Fachada.criarBilhete("GHI3456", LocalDateTime.of(2024, 11, 19, 9, 0, 0, 0));
			Fachada.registrarSaida("GHI3456", LocalDateTime.of(2024, 11, 20, 0, 0));
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
		new Cadastrar();
	}
	// =================================================

}
