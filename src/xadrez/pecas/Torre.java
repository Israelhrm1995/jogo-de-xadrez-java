package xadrez.pecas;

import placajogo.Tabuleiro;
import xadrez.Cor;
import xadrez.XadrezPeca;

public class Torre extends XadrezPeca {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}

}
