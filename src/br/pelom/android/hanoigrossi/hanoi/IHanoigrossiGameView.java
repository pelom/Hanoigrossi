package br.pelom.android.hanoigrossi.hanoi;

import br.pelom.android.hanoigrossi.model.Fase;

/**
 * @author pelom
 */
public interface IHanoigrossiGameView {

	/**
	 * Metodo executado quando um fase e concluida e uma nova 
	 * fase precisa ser carregada.
	 * 
	 * @param board nova configuracao dos objetos
	 * @param fase nova fase do jogo
	 */
	public void actionNovaFase(IHanoigrossiBoard board, Fase fase);
	
	/**
	 * Metodo chamada quando o disco tenta se movido.
	 * 
	 * @param sucesso - true se o disco foi movido com sucesso false se nao
	 * @param mim - numero de movimentos minimos.
	 * @param move - numero de movimentos ja realizados
	 */	
	public void actionMoverDisco(boolean sucesso, int min, int move);
	
	/**
	 * Metodo executando quando o objeto do jogo for concluido.
	 * 
	 * @param - true se o minimo de movimentos foi concluido.
	 * @param - fase concluida
	 */
	public void actionObjetivoConcluido(boolean min, Fase concluida);

	/**
	 * Metodo executado quando o jogo nao e concluido pois o numero
	 * minimo de movimento foi exedido
	 * 
	 * @param mim - numero de movimentos minimos.
	 * @param move - numero de movimentos ja realizados
	 */
	public void actionGameOver(int min, int move);
}