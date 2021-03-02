package xadrez;

import placajogo.Tabuleiro;

public class XadrezPartida {
	
	private Tabuleiro tabuleiro;
	
	public XadrezPartida() {
		tabuleiro = new Tabuleiro(8, 8);
	}
	
	public XadrezPeca[][] getPecas() {
		XadrezPeca[][] matris = new XadrezPeca[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matris[i][j] = (XadrezPeca) tabuleiro.peca(i, j);
			}
		}
		return matris;
	}
	
	
}
