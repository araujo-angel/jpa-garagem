package appconsole;
/**********************************
 * IFPB - SI
 * Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.time.LocalDateTime;

import regras_negocio.Fachada;

public class Alterar {

	public Alterar(){
		Fachada.inicializar();
		
		// Alteração 1: Alterar placa do veículo
		try {
			//Fachada.alterarPlacaVeiculo("PFV1234","PFV4567");
			//System.out.println("Placa alterada com sucesso");
		}
		catch (Exception e) {
			System.out.println("Erro na alteração da placa: " + e.getMessage());
			e.printStackTrace(); // Exibe o stack trace para diagnóstico
		}
		
		// Alteração 2: Alterar bilhete (caso tenha sido necessário)
		try {
			// Certifique-se de que o código de barras existe e a data é válida.
			Fachada.alterarBilhete("12032025505", LocalDateTime.of(2025, 03, 12, 19, 0, 0, 0));
			System.out.println("Bilhete alterado com sucesso");
		}
		catch (Exception e) {
			System.out.println("Erro na alteração do bilhete: " + e.getMessage());
			e.printStackTrace(); // Exibe o stack trace para diagnóstico
		}
		
		Fachada.finalizar();
		System.out.println("Fim do programa");
	}

	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}
