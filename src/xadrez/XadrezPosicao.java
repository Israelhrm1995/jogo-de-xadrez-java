package xadrez;

import placajogo.Posicao;

public class XadrezPosicao {
	
	private char coluna;
	private char linha;
	
	public XadrezPosicao(char coluna, char linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezExecao("Erro ao criar uma posicao no xadres os valores permitidos sao de a1 a h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public char getLinha() {
		return linha;
	}
	
	protected Posicao paraPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static XadrezPosicao paraPosicao(Posicao posicao) {
		return new XadrezPosicao((char)('a' - posicao.getColuna()), (char) (8 - posicao.getLinha()));
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}

}
