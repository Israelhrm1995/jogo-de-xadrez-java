package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import placajogo.Peca;
import placajogo.Posicao;
import placajogo.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class XadrezPartida {
	
	private static int turno;
	private static Cor jogadorCorrente;
	private static Tabuleiro tabuleiro;
	private static boolean check;
	private static boolean checkMate;
	
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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if(testarCheck(jogadorCorrente)) {
			desfazerMovimento(inicial, destino, pegandoPeca);
			throw new XadrezExecao("Voce nao pode se colocar em Check !");
		}
		
		check = (testarCheck(oponente(jogadorCorrente))) ? true : false;
		
		if(testarCheckMate(oponente(jogadorCorrente))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
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
	
	private static Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private static XadrezPeca Rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if(p instanceof Rei) {
				return (XadrezPeca)p;
			}
		}
		throw new IllegalStateException("O rei da cor" + cor + "nao exite");
	}
	
	private static boolean testarCheck(Cor cor) {
		Posicao posicaoDoRei = Rei(cor).getXadrezPosicao().paraPosicao();
		List<Peca> pecasDoOponete = pecasNoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasDoOponete) {
			boolean[][] mat = p.movimentosPossiveis();
			if(mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean testarCheckMate(Cor cor) {
		if (!testarCheck(cor)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((XadrezPeca)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for(int i=0; i<tabuleiro.getLinhas(); i++) {
				for(int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((XadrezPeca)p).getXadrezPosicao().paraPosicao();
						Posicao destino = new Posicao(i, j);
						Peca capturarPeca = fazerMovimento(origem, destino);
						boolean testarCheck = testarCheck(cor);
						desfazerMovimento(origem, destino, capturarPeca);
						if(!testarCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private static Peca fazerMovimento(Posicao inicial, Posicao destino) {
		XadrezPeca p = (XadrezPeca)tabuleiro.removerPeca(inicial);
		p.incrementandoContagemMovimento();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarpeca(p, destino);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecascapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private static void desfazerMovimento(Posicao inicial, Posicao destino, Peca pecaCapturada) {
		XadrezPeca p = (XadrezPeca)tabuleiro.removerPeca(destino);
		p.decrementandoContagemMovimento();
		tabuleiro.lugarpeca(p, inicial);
		
		if(pecaCapturada != null) {
			tabuleiro.lugarpeca(pecaCapturada, destino);
			pecascapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
		
	}
	
	private void colocarNovaPeca(char coluna, int linha, XadrezPeca peca) {
		tabuleiro.lugarpeca(peca, new XadrezPosicao(coluna, (char) linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void iniciarPartida() {
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));
		
		colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));
		
		
	}
	
	
}
