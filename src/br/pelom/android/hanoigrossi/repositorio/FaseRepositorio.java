package br.pelom.android.hanoigrossi.repositorio;

import android.content.Context;
import android.database.Cursor;
import br.pelom.android.hanoigrossi.model.Fase;
import br.pelom.android.utils.sqlite.AbstractRepositorio;
import br.pelom.android.utils.sqlite.SQLiteHelper;

/**
 * @author pelom
 */
public class FaseRepositorio  extends AbstractRepositorio<Fase, Integer> {

	public FaseRepositorio(SQLiteHelper helper, Context ctx) {
		super(helper, ctx);
	}

	/**
	 * Obtero nome da classe ID.
	 * 
	 * @return nome da coluna identificadora.
	 */
	@Override
	public String getNomeColunaId() {
		return Banco.CL_ID_PADRAO;
	}

	/**
	 * Obter o nome de todas as colunas da babela.
	 * 
	 * @return nome das colunas.
	 */
	@Override
	public String[] getNomeColunas() {
		return Banco.CLS_FASE;
	}

	/**
	 * Obter o nome da tabela.
	 * 
	 * @return nome da tabela.
	 */
	@Override
	public String getNomeTabela() {
		return Banco.TB_FASE;
	}

	@Override
	protected Fase popularComValores(Cursor cursor) {
		Fase fase = new Fase(getNivel(cursor), getDisco(cursor), isMovimentos(cursor));
		fase.setAberta(isAberta(cursor));
		fase.setNumeroMov(getNumMovimentos(cursor));
		fase.setTempo(getTempo(cursor));
		
		return fase;
	}

	private String getTempo(Cursor cursor) {
		// TODO Auto-generated method stub
		return cursor.getString(
				cursor.getColumnIndex(Banco.CL_FASE_TEMPO));
	}

	private int getNumMovimentos(Cursor cursor) {
		return cursor.getInt(
				cursor.getColumnIndex(Banco.CL_FASE_NUM_MOVIMENTOS));
	}

	private boolean isAberta(Cursor cursor) {
		return (cursor.getInt(
				cursor.getColumnIndex(Banco.CL_FASE_ABERTA)) == 0) ? false : true;
	}

	private int getDisco(Cursor cursor) {
		return cursor.getInt(
				cursor.getColumnIndex(Banco.CL_FASE_DISCOS));
	}

	private boolean isMovimentos(Cursor cursor) {
		return (cursor.getInt(
				cursor.getColumnIndex(Banco.CL_FASE_MOVIMENTOS)) == 0) ? false : true;
	}

	private int getNivel(Cursor cursor) {
		return cursor.getInt(
				cursor.getColumnIndex(Banco.CL_ID_PADRAO));
	}
}