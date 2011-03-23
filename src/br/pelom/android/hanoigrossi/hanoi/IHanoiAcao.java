/**
 * 
 */
package br.pelom.android.hanoigrossi.hanoi;

/**
 * @author pelom
 */
public interface IHanoiAcao {

	/**
	 * Metodo chamada quando o disco tenta se movido.
	 * 
	 * @param sucesso - true se o disco foi movido com sucesso false se nao
	 * @param mim - numero de movimentos minimos.
	 * @param move - numero de movimentos ja realizados
	 */	
	public void actionMoverDisco(boolean sucesso, int min, int move);
}