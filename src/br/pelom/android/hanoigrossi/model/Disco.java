package br.pelom.android.hanoigrossi.model;

import br.pelom.android.hanoigrossi.utils.Cor;

/**
 * @author pelom
 */
public class Disco {
	/** Numero do disco **/
	private int num;
	/** Tamanho do disco **/
	private int discWidth;
	
	private Cor corMaisEscuro;
	private Cor corEscura;
	private Cor corClara;
	private Cor corLuz;
	
	private boolean selecionado;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param num
	 */
	public Disco(int num) {
		this.num = num;
		
		final Cor[] cores = Cor.MAP_CORES.get(num) ;
		this.corMaisEscuro = cores[0];
		this.corEscura = cores[1];
		this.corClara = cores[2];
		this.corLuz = cores[3];
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Disco [num=" + num + ", discWidth=" + discWidth + "]";
	}


	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}


	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}


	/**
	 * @return the discWidth
	 */
	public int getDiscWidth() {
		return discWidth;
	}


	/**
	 * @param discWidth the discWidth to set
	 */
	public void setDiscWidth(int discWidth) {
		this.discWidth = discWidth;
	}


	/**
	 * @return the corMaisEscuro
	 */
	public Cor getCorMaisEscuro() {
		return corMaisEscuro;
	}


	/**
	 * @param corMaisEscuro the corMaisEscuro to set
	 */
	public void setCorMaisEscuro(Cor corMaisEscuro) {
		this.corMaisEscuro = corMaisEscuro;
	}


	/**
	 * @return the corEscura
	 */
	public Cor getCorEscura() {
		return corEscura;
	}


	/**
	 * @param corEscura the corEscura to set
	 */
	public void setCorEscura(Cor corEscura) {
		this.corEscura = corEscura;
	}


	/**
	 * @return the corClara
	 */
	public Cor getCorClara() {
		return corClara;
	}


	/**
	 * @param corClara the corClara to set
	 */
	public void setCorClara(Cor corClara) {
		this.corClara = corClara;
	}

	/**
	 * @return the corLuz
	 */
	public Cor getCorLuz() {
		return corLuz;
	}


	/**
	 * @param corLuz the corLuz to set
	 */
	public void setCorLuz(Cor corLuz) {
		this.corLuz = corLuz;
	}

	/**
	 * @return the selecionado
	 */
	public boolean isSelecionado() {
		return selecionado;
	}


	/**
	 * @param selecionado the selecionado to set
	 */
	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
}