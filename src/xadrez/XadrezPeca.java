package xadrez;

import placajogo.Peca;
import placajogo.Posicao;
import placajogo.Tabuleiro;

public abstract class XadrezPeca extends Peca {
	
	private Cor cor;
	private int contagemMovimentos;

	public XadrezPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContagemMovimentos() {
		return contagemMovimentos;
	}
	
	public void incrementandoContagemMovimento() {
		contagemMovimentos++;
	}
	
	public void decrementandoContagemMovimento() {
		contagemMovimentos--;
	}
	
	public XadrezPosicao getXadrezPosicao() {
		return XadrezPosicao.paraPosicao(posicao);
	}
	
	protected boolean haPecaOponente(Posicao posicao) {
		XadrezPeca peca = (XadrezPeca) getTabuleiro().peca(posicao);
		return peca != null && peca.getCor() != cor;
	}
	

}
