package xadrez;

import placajogo.Posicao;
import placajogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class XadrezPartida {
	
	private Tabuleiro tabuleiro;
	
	public XadrezPartida() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciarPartida();
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
	
	private void iniciarPartida() {
		tabuleiro.lugarpeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 1));
		tabuleiro.lugarpeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(0, 4));
		tabuleiro.lugarpeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
	}
	
	
}
