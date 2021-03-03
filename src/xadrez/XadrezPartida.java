package xadrez;

import java.util.ArrayList;
import java.util.List;

import placajogo.Peca;
import placajogo.Posicao;
import placajogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class XadrezPartida {
	
	private static int turno;
	private static Cor jogadorCorrente;
	private static Tabuleiro tabuleiro;
	
	private static List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private static List<Peca> pecascapturadas = new ArrayList<>();
	
	public XadrezPartida() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorCorrente = Cor.BRANCO;
		iniciarPartida();
	}
	
	public int getTurno() {
		return turno;
	}

	public Cor getJogadorCorrente() {
		return jogadorCorrente;
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
	
	public static boolean[][] movimentosPossiveis(XadrezPosicao inicialPosicao){
		Posicao posicao = inicialPosicao.paraPosicao();
		validarInicialPosicao(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
		}
	
	public static XadrezPeca executarXadrezMovimento(XadrezPosicao inicialposicao, XadrezPosicao destinoposicao) {
		Posicao inicial = inicialposicao.paraPosicao();
		Posicao destino = destinoposicao.paraPosicao();
		validarInicialPosicao(inicial);
		validarPosicaoDestino(inicial, destino);
		Peca pegandoPeca = fazerMovimento(inicial, destino);
		proximoTurno();
		return (XadrezPeca) pegandoPeca;
	}
	
	private static void validarInicialPosicao(Posicao posicao) {
		if(!tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezExecao("Nao exite peca na posicao inicial Cavalo !");
		}
		if(jogadorCorrente != ((XadrezPeca)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExecao("A peca escolhida nao e sua ); !");
		}
		if (!tabuleiro.peca(posicao).haAlgumMovimentoPossivel()) {
			throw new XadrezExecao("Nao exite movimento possivel para essa peca :/ !");
		}
	}
	
	private static void validarPosicaoDestino(Posicao inicial, Posicao destino) {
		if (!tabuleiro.peca(inicial).movimentoPossivel(destino)){
			throw new XadrezExecao(":( A peca escolhida nao pode mover para a posicao de destino !");
		}
	}
	
	private static void proximoTurno() {
		turno++;
		jogadorCorrente = (jogadorCorrente == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private static Peca fazerMovimento(Posicao inicial, Posicao destino) {
		Peca p = tabuleiro.removerPeca(inicial);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarpeca(p, destino);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecascapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void colocarNovaPeca(char coluna, int linha, XadrezPeca peca) {
		tabuleiro.lugarpeca(peca, new XadrezPosicao(coluna, (char) linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
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
