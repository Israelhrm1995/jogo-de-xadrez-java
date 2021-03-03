package xadrez.pecas;

import placajogo.Posicao;
import placajogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPartida;
import xadrez.XadrezPeca;

public class Rei extends XadrezPeca{
	
	private XadrezPartida xadrezPartida;

	public Rei(Tabuleiro tabuleiro, Cor cor, XadrezPartida xadrezPartida) {
		super(tabuleiro, cor);
		this.xadrezPartida = xadrezPartida;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		XadrezPeca p = (XadrezPeca)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testarTorreParaRock(Posicao posicao) {
		XadrezPeca p = (XadrezPeca)getTabuleiro().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimentos() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// acima
		p.setValores(posicao.getLinha() -1,	 posicao.getColuna());
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		p.setValores(posicao.getLinha() +1,	 posicao.getColuna());
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		p.setValores(posicao.getLinha(),	 posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		p.setValores(posicao.getLinha(),	 posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Noroeste
		p.setValores(posicao.getLinha() - 1,	 posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Nordeste
		p.setValores(posicao.getLinha() - 1,	 posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sudoeste
		p.setValores(posicao.getLinha() + 1,	 posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Sudeste
		p.setValores(posicao.getLinha() + 1,	 posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// #movimento especial do ROCK
		
		if (getContagemMovimentos() == 0 && !xadrezPartida.getCheck()) {
			// #movimento especial ROCK pequeno 
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() +3);
			if(testarTorreParaRock(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
		}
		
		if (getContagemMovimentos() == 0 && !xadrezPartida.getCheck()) {
			// #movimento especial ROCK grande 
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() -4);
			if(testarTorreParaRock(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if(getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return mat;
	}

}
