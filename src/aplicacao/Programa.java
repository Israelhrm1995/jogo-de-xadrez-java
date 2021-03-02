package aplicacao;

import java.util.Scanner;

import xadrez.XadrezPartida;
import xadrez.XadrezPeca;
import xadrez.XadrezPosicao;

public class Programa {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		XadrezPartida xadrezPartida = new XadrezPartida();
		
		while(true) {
			UI.printTabuleiro(xadrezPartida.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			XadrezPosicao origem = UI.lerXadrezPosicao(sc);
			System.out.println();
			System.out.print("Destino: ");
			XadrezPosicao destino = UI.lerXadrezPosicao(sc);
			
			XadrezPeca capturarPeca = XadrezPartida.executarXadrezMovimento(origem, destino);
		}
		
	}

}
