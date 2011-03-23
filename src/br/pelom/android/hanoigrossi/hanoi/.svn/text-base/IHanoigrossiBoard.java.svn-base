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
	
	/**
	 * O objetivo do jogo foi concluido
	 * @return true se sim ou false se nao
	 */
	public boolean isbjetivoConcluido();
	
	/**
	 * Retorna se a joga realizada e um passo correto. 
	 * @return numero da haste com os passo correto ou -1 se o passo
	 * nao for correto
	 */
	public int verificarPassoCorreto();
	
	/**
	 * recupera o numero de movimentos minimos para concluir o jogo
	 * @return numero de movimentos minimos
	 */
	public int getMinMoves();
	
	/**
	 * Recupera o numero de movimentos do jogador
	 * @return numero de movimentos
	 */
	public int getMoveCount();
	
	/**
	 * Recupera os discos da haste.
	 * @param haste - index da haste a ser recuperada os discos
	 * @return discos da haste
	 */
	public Disco[] getDisco(int haste);
	
	/**
	 * Selecionar ou deselecionar os discos da haste.
	 * @param haste - haste a ser selecionada os discos
	 * @param select - true para selecionar os discos false se nao
	 */
	public void selecionarDisco(int haste, boolean select);
	
	/**
	 * Notifica que um disco foi selecionado.
	 * @param disco - disco selecionado
	 */
	public void discoSilencionado(Disco disco);
}
