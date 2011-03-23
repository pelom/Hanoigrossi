/**
 * 
 */
package br.pelom.android.hanoigrossi.hanoi;

import br.pelom.android.hanoigrossi.model.Disco;


/**
 * @author pelom
 */
public interface IHanoigrossiBoard {

	Disco getPegTop(int haste);

	int getDiscWidth(int disco);

	boolean isStartPeg(int i);

	Disco getTopDisc(int haste);
	
	boolean moveDisc(Disco disco, int hasteOrigem, int hasteDestino);
	
	public int getDiscoSize();
	
	public void setHanoiAcao(IHanoiAcao acao);
	
	boolean isbjetivoConcluido();
	
	/**
	 * Retorna se a joga realizada e um passo correto. 
	 * @return numero da haste com os passo correto ou -1 se o passo
	 * nao for correto
	 */
	public int verificarPassoCorreto();
	
	int getMinMoves();
	
	int getMoveCount();
	
	Disco[] getDisco(int haste);
	
	void selecionarDisco(int haste, boolean select);
}
