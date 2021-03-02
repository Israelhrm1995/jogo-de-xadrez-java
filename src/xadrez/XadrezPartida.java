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
	
	private void colocarNovaPeca(char coluna, int linha, XadrezPeca peca) {
		tabuleiro.lugarpeca(peca, new XadrezPosicao(coluna, (char) linha).paraPosicao());
	}
	
	private void iniciarPartida() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
		
	}
	
	
}
