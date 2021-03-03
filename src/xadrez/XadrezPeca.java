package xadrez;

import placajogo.Peca;
import placajogo.Posicao;
import placajogo.Tabuleiro;

public abstract class XadrezPeca extends Peca {
	
	private Cor cor;

	public XadrezPeca(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean haPecaOponente(Posicao posicao) {
		XadrezPeca peca = (XadrezPeca) getTabuleiro().peca(posicao);
		return peca != null && peca.getCor() != cor;
	}
	

}
