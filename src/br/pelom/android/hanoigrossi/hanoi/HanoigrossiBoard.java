package br.pelom.android.hanoigrossi.hanoi;

import java.util.Iterator;
import java.util.Stack;

import br.pelom.android.hanoigrossi.model.Disco;
import br.pelom.android.hanoigrossi.utils.Utils;

/**
 * @author pelom
 */
public class HanoigrossiBoard implements IHanoigrossiBoard {
	/** Representacao das hastes **/
	private Stack<Disco>[] hastes;
	/** Numero de hastes **/
	private static final int PEGS = 3;
	/** Tamanho dos discos **/
	static final int DISC_SIZES[][] = { 
		{68,18}, {76,16}, {84,14}, {92,13}, {100,12},
		{108,12}, {112,11}, {116,10}, {120,9}, {124,9}
	};

	/** Numero de movimentos **/
	private int moveCount;
	/** Numero de movimentos minimos **/
	private int minMoves;
	/** Ouvinte das acao do jogo **/
	private IHanoiAcao acao;
	/** Todos os criscos **/
	private Disco[] discos;

	/**
	 * Construtor da classe.
	 * 
	 * @param discs numero de disco
	 * @param peg1 primeira
	 * @param min
	 */
	public HanoigrossiBoard(int discs, int peg1, int min) {
		super();

		//criar hastes.
		hastes = new Stack[PEGS];
		for (int i = 0; i < PEGS; i++) {
			hastes[i] = new Stack<Disco>();
		}

		//criar os discos 
		discos = new Disco[discs];
		for (int i = discs-1; i >= 0; i--) {
			Disco disco = new Disco(i+1);
			disco.setDiscWidth(DISC_SIZES[discs - min][0] -(DISC_SIZES[discs - min][1] * (discs-1-i)));

			discos[i] = disco;
		}

		// Coloca todos os discos na primeira haste
		for (int i = discs-1; i >= 0; i--) {
			hastes[peg1].push(discos[i]);
		}

		//calcular movimentos minimos
		moveCount = 0;
		minMoves = Utils.moviMinimo(discs);
	}

	@Override
	public Disco getPegTop(int haste) {
		// a haste nao esta vazia?
		if(!isHasteVazia(haste)) {
			return hastes[haste].peek();
		}

		return null;
	}
	
	/**
	 * Recuperar o tamanho do disco.
	 */
	@Override
	public int getDiscWidth(int disco) {
		return discos[disco-1].getDiscWidth();
	}

	/**
	 * 
	 */
	@Override
	public boolean isStartPeg(int haste) {
		if ((haste >= 0) && 
				(!isHasteVazia(haste) && hastes[haste].peek().getNum() >= 0)) {
			return true;

		}
		return false;
	}

	@Override
	public Disco getTopDisc(int haste) {
		if(!isHasteVazia(haste)) {
			return hastes[haste].pop();
		}

		return null;
	}

	@Override
	public boolean moveDisc(Disco disco, int hasteOrigem, int hasteDestino) {
		boolean movi  = false;

		if (hasteOrigem >= 0 && hasteDestino >= 0 && disco != null) {
			// Para haste diferente que esta vazia ou tem disco maior
			if (hasteOrigem != hasteDestino && 
					(isHasteVazia(hasteDestino) || hastes[hasteDestino].peek().getNum() > disco.getNum())) {

				//colocar o disco na haste de destino.
				hastes[hasteDestino].push(disco);
				//soma movimento
				moveCount++;
				
				movi = true;

			} else {
				//nao ouve movimentacao coloque o disco no lugar novamente.
				hastes[hasteOrigem].push(disco);

			}
		}

		//existe ouvinte?
		if(acao != null) {
			//notifique
			acao.actionMoverDisco(movi, minMoves, moveCount);
		}

		return movi;
	}

	/**
	 * 
	 */
	@Override
	public int getDiscoSize() {
		return discos.length;
	}

	/**
	 * 
	 */
	@Override
	public void setHanoiAcao(IHanoiAcao acao) {
		this.acao = acao;
	}

	/**
	 * 
	 */
	public boolean isbjetivoConcluido() {
		if((hastes[PEGS-1].empty() || hastes[PEGS-2].empty()) && hastes[PEGS-3].empty()) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna se a joga realizada e um passo correto. 
	 * @return numero da haste com os passo correto ou -1 se o passo
	 * nao for correto
	 */
	public int verificarPassoCorreto() {
		//a segunda ou a terceira haste esta vazia?
		if((hastes[PEGS-1].empty() || hastes[PEGS-2].empty()) && !hastes[PEGS-3].empty()) {
			//haste nao esta vazia?
			if(!hastes[PEGS-1].empty() && hastes[PEGS-1].size() > 2) {
				 if(isDiscoSequencia(hastes[PEGS-1])) {
					 return PEGS-1;
				 }
				
			} else if(!hastes[PEGS-2].empty() && hastes[PEGS-2].size() > 2) {
				if(isDiscoSequencia(hastes[PEGS-2])) {
					return PEGS-2;
				}
			
			}
		}
		
		return -1;
	}
	
	/**
	 * Verifica se os disco na haste estao na seguencia correta.
	 * @return true se estivere false se nao
	 */
	private boolean isDiscoSequencia(Stack<Disco> haste) {
		final Iterator<Disco> it = haste.iterator();
		
		//numero do disco referencia.
		int i = haste.size()-1;
		
		while (it.hasNext()) {
			Disco disc = it.next();
			//o disco menor e diferente do disco referencia?
			if(i != disc.getNum()-1) {
				return false;
			}
			
			i--;
		}
		
		return true;
	}
	
	/**
	 * 
	 */
	public int getMoveCount() {
		return moveCount;
	}

	/**
	 * 
	 */
	public int getMinMoves() {
		return minMoves;
	}

	/**
	 * 
	 */
	@Override
	public Disco[] getDisco(int haste) {
		//criar array de discos.
		final Disco[] arrayDisc = new Disco[hastes[haste].size()];

		//haste nao esta vazia?
		if(!isHasteVazia(haste)) {
			final Iterator<Disco> it = hastes[haste].iterator();
			int i = 0;
			while (it.hasNext()) {
				Disco disc = it.next();
				arrayDisc[i++] = disc;
			}
		}

		return arrayDisc;
	}

	/**
	 * 
	 * @param haste
	 * @return
	 */
	private boolean isHasteVazia(int haste) {
		return hastes[haste].empty();
	}

	@Override
	public void selecionarDisco(int haste, boolean select) {
		Disco[] discos = getDisco(haste);
		
		int size = discos.length;
		for (int i = 0; i < size; i++) {
			discos[i].setSelecionado(select);
		}
	}
}