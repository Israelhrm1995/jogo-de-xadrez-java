package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.XadrezExecao;
import xadrez.XadrezPartida;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		XadrezPartida xadrezPartida = new XadrezPartida();
		List<XadrezPeca> capturada = new ArrayList<>();
		
		while(!xadrezPartida.getCheckMate()) {
			try {
				UI.limparTela();
				UI.imprimirPartida(xadrezPartida, capturada);
				System.out.println();
				System.out.print("Origem: ");
				XadrezPosicao origem = UI.lerXadrezPosicao(sc);
				boolean[][] movimentosPossiveis = XadrezPartida.movimentosPossiveis(origem);
				UI.limparTela();
				UI.printTabuleiro(xadrezPartida.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.print("Destino: ");
				XadrezPosicao destino = UI.lerXadrezPosicao(sc);
				
				XadrezPeca capturarPeca = XadrezPartida.executarXadrezMovimento(origem, destino);
				
				if(capturarPeca != null) {
					capturada.add(capturarPeca);
				}
				
				if (xadrezPartida.getPromocao() != null) {
					System.out.print("Digite o tipo da peca para ser promovida (B/C/R/Q): ");
					String tipo = sc.nextLine().toUpperCase();
					while(!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("R") && !tipo.equals("Q")) {
						System.out.print("Tipo anterior digitado e invalido! Digite o tipo da peca para ser promovida (B/C/R/Q): ");
						tipo = sc.nextLine().toUpperCase();
					}
					xadrezPartida.substituirPecaPromovida(tipo);
				}
				
			}
			catch(XadrezExecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		
		UI.limparTela();
		UI.imprimirPartida(xadrezPartida, capturada);
		
	}

}
