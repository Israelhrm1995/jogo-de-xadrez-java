package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.XadrezExecao;
import xadrez.XadrezPartida;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		XadrezPartida xadrezPartida = new XadrezPartida();
		
		while(true) {
			try {
				UI.limparTela();
				UI.imprimirPartida(xadrezPartida);
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
		
	}

}
