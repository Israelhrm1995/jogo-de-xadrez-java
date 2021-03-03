package xadrez.pecas;

import placajogo.Posicao;
import placajogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPartida;
import xadrez.XadrezPeca;

public class Peao extends XadrezPeca {
	
	private XadrezPartida xadrezPartida;

	public Peao(Tabuleiro tabuleiro, Cor cor, XadrezPartida xadrezPartida) {
		super(tabuleiro, cor);
		this.xadrezPartida = xadrezPartida;
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		if(getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() -1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() -1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().haUmaPeca(p2)  && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -1, posicao.getColuna() -1);
			if (getTabuleiro().posicaoExistente(p) && haPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -1, posicao.getColuna() +1);
			if (getTabuleiro().posicaoExistente(p) && haPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// #movimento especial en passant peça branca
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if(getTabuleiro().posicaoExistente(esquerda) && haPecaOponente(esquerda) && getTabuleiro().peca(esquerda) == xadrezPartida.getJogadaEnPassant()) {
					mat[esquerda.getLinha() -1][esquerda.getColuna()] = true;
				}
				Posicao direira = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if(getTabuleiro().posicaoExistente(direira) && haPecaOponente(direira) && getTabuleiro().peca(direira) == xadrezPartida.getJogadaEnPassant()) {
					mat[direira.getLinha() -1][direira.getColuna()] = true;
				}
			}
			
		}
		else {
			p.setValores(posicao.getLinha() +1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() +1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p) && getTabuleiro().posicaoExistente(p2) && !getTabuleiro().haUmaPeca(p2)  && getContagemMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +1, posicao.getColuna() -1);
			if (getTabuleiro().posicaoExistente(p) && haPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +1, posicao.getColuna() +1);
			if (getTabuleiro().posicaoExistente(p) && haPecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// #movimento especial en passant peça preta
			if(posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() -1);
				if(getTabuleiro().posicaoExistente(esquerda) && haPecaOponente(esquerda) && getTabuleiro().peca(esquerda) == xadrezPartida.getJogadaEnPassant()) {
					mat[esquerda.getLinha() +1][esquerda.getColuna()] = true;
				}
				Posicao direira = new Posicao(posicao.getLinha(), posicao.getColuna() +1);
				if(getTabuleiro().posicaoExistente(direira) && haPecaOponente(direira) && getTabuleiro().peca(direira) == xadrezPartida.getJogadaEnPassant()) {
					mat[direira.getLinha() +1][direira.getColuna()] = true;
				}
			}
			
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

	

}
