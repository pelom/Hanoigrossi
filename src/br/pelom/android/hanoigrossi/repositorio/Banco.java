/**
 * 
 */
package br.pelom.android.hanoigrossi.repositorio;

import android.content.Context;
import br.pelom.android.utils.sqlite.SQLiteHelper;

/**
 * @author pelom
 */
public class Banco {
	/** Nome do banco **/
	public static final String NOME_BANCO = "hanoigrossi";

	/** Versao do banco **/
	public static final int VERSAO_BANCO = 1;

	/** Classe utilitaria para a manipulacao do banco **/
	private static SQLiteHelper dbHelper = null;

	/** Identificador padrao para todas as tabelas **/
	public static final String CL_ID_PADRAO = "_id";

	/** Variaveis da tabela de silenciador  de chamada **/
	public static final String TB_FASE 					= "tbfase";
	public static final String CL_FASE_DISCOS 			= "discos";
	public static final String CL_FASE_MOVIMENTOS 		= "movimento";
	public static final String CL_FASE_ABERTA 			= "aberta";
	public static final String CL_FASE_NUM_MOVIMENTOS 	= "num_movimentos";
	public static final String CL_FASE_TEMPO 			= "tempo";

	/** Colunas da tabela **/
	public static final String[] CLS_FASE = {
		CL_ID_PADRAO, CL_FASE_DISCOS, CL_FASE_MOVIMENTOS, 
		CL_FASE_ABERTA, CL_FASE_NUM_MOVIMENTOS, CL_FASE_TEMPO};

	/** Scripts de criacao do banco **/
	public static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"CREATE TABLE " + TB_FASE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + CL_FASE_DISCOS + " INTEGER, " + CL_FASE_MOVIMENTOS + " INTEGER, " + CL_FASE_ABERTA + " INTEGER, " + CL_FASE_NUM_MOVIMENTOS + " INTEGER, " + CL_FASE_TEMPO + " TEXT);",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('1',  '3', '0', '1', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('2',  '4', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('3',  '5', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('4',  '6', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('5',  '7', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('6',  '8', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('7',  '9', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('8',  '10', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('9',  '11', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('10',  '12', '0', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('11',  '3', '1', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('12',  '4', '1', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('13',  '5', '1', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('14',  '6', '1', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('15',  '7', '1', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('16',  '8', '1', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('17',  '9', '1', '0', '0', '00:00');",
		"INSERT INTO " + TB_FASE + " ('_id',  'discos',  'movimento',  'aberta',  'num_movimentos',  'tempo') values ('18',  '10', '1', '0', '0', '00:00');",
	};

	/** Scripts de drop das tabelas **/
	public static final String[] SCRIPT_DATABASE_DROP = new String[] {
		"DROP TABLE IF EXISTS " + TB_FASE
	};

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static SQLiteHelper getSQLiteHelper(Context ctx) {
		if(dbHelper == null) {
			// Classe utilitaria para abrir, criar, e atualizar o banco de dados
			dbHelper =  new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO,
					SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DROP);
		}

		return dbHelper;
	}
}
