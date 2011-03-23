/**
 * 
 */
package br.pelom.android.hanoigrossi.model;

import android.content.ContentValues;
import br.pelom.android.hanoigrossi.repositorio.Banco;
import br.pelom.android.utils.sqlite.IPersiste;

/**
 * @author pelom
 */
public class Fase implements IPersiste{

	/** SerialVersionUID */
	private static final long serialVersionUID = -3310568053828344340L;

	/** Nivel da fase **/
	private int nivel;

	/** Numero de disco **/
	private int disco;

	/** conclusao da fase pelo movimento**/
	private boolean movimentos = false;

	/** Marca se a fase esta aberta **/
	private boolean aberta = false;

	/** Numero de movimentos  **/
	private int numeroMov;

	/** Tempo levado para concluir a fase **/
	private String tempo;

	/**
	 * Construtor da classe.
	 * 
	 * @param nivel
	 * @param disco
	 */
	public Fase(int nivel, int disco, boolean movimentos) {
		this(nivel, disco, movimentos, false);


	}

	/**
	 * 
	 * @param nivel
	 * @param disco
	 * @param movimentos
	 * @param aberta
	 */
	public Fase(int nivel, int disco, boolean movimentos, boolean aberta) {
		super();

		this.nivel = nivel;
		this.disco = disco;
		this.movimentos = movimentos;
		this.aberta = aberta;
	}

	/**
	 * @return the nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the disco
	 */
	public int getDisco() {
		return disco;
	}

	/**
	 * @param disco the disco to set
	 */
	public void setDisco(int disco) {
		this.disco = disco;
	}

	/**
	 * @return the movimentos
	 */
	public boolean isMovimentos() {
		return movimentos;
	}

	/**
	 * @param movimentos the movimentos to set
	 */
	public void setMovimentos(boolean movimentos) {
		this.movimentos = movimentos;
	}

	/**
	 * @return the aberta
	 */
	public boolean isAberta() {
		return aberta;
	}

	/**
	 * @param aberta the aberta to set
	 */
	public void setAberta(boolean aberta) {
		this.aberta = aberta;
	}

	/**
	 * @return the numeroMov
	 */
	public int getNumeroMov() {
		return numeroMov;
	}

	/**
	 * @param numeroMov the numeroMov to set
	 */
	public void setNumeroMov(int numeroMov) {
		this.numeroMov = numeroMov;
	}

	/**
	 * @return the tempo
	 */
	public String getTempo() {
		return tempo;
	}

	/**
	 * @param tempo the tempo to set
	 */
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fase [nivel=" + nivel + ", disco=" + disco + ", movimentos="
				+ movimentos + ", aberta=" + aberta + ", numeroMov="
				+ numeroMov + ", tempo=" + tempo + "]";
	}

	@Override
	public Integer getID() {
		return nivel;
	}

	@Override
	public ContentValues itemToContentValues() {
		final ContentValues values = new ContentValues();
		values.put(Banco.CL_ID_PADRAO, nivel);
		values.put(Banco.CL_FASE_DISCOS, disco);
		values.put(Banco.CL_FASE_ABERTA, ( (aberta) ? 1 : 0) );
		values.put(Banco.CL_FASE_MOVIMENTOS, ( (movimentos) ? 1 : 0) );
		values.put(Banco.CL_FASE_NUM_MOVIMENTOS, numeroMov);
		values.put(Banco.CL_FASE_TEMPO, tempo);

		return values;
	}
}