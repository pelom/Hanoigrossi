package br.pelom.android.hanoigrossi.hanoi;

import java.util.List;

import android.content.Context;
import br.pelom.android.hanoigrossi.model.Fase;
import br.pelom.android.hanoigrossi.repositorio.Banco;
import br.pelom.android.hanoigrossi.repositorio.FaseRepositorio;

/**
 * @author pelom
 */
public class HanoiControleFase implements IHanoiControleFase {
	/** Lista de fases **/
	private List<Fase> fases;
	/** Fase atual **/
	private Fase faseAtual;
	/** Context **/
	private Context ctx = null;
	/** Instancia da classe **/
	private static HanoiControleFase controleFase = null;

	/**
	 * 
	 * @param ctx
	 */
	private HanoiControleFase(Context ctx) {
		this.ctx = ctx;

		final FaseRepositorio rep = 
			new FaseRepositorio(Banco.getSQLiteHelper(ctx), ctx);
		rep.abrir();
		fases = rep.listar();
		rep.fechar();
		
		this.faseAtual = fases.get(0);
	}

	/**
	 * @return the fases
	 */
	public List<Fase> getFases() {
		return fases;
	}

	/**
	 * {@inheritDoc}
	 */
	public Fase getFaseAtual() {
		return faseAtual;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setFaseAtual(Fase faseAtual) {
		this.faseAtual = faseAtual;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public Fase proximaFase() {
		//recuperar o nivel da fase
		int nivel = faseAtual.getNivel();

		if(nivel >= fases.size()) {
			return null;
		}
		
		//recuperar proxima fase
		Fase novaFase =  fases.get(nivel);

		faseAtual = novaFase;
		faseAtual.setAberta(true);
		
		final FaseRepositorio rep = 
			new FaseRepositorio(Banco.getSQLiteHelper(ctx), ctx);
		rep.abrir();
		rep.atualizar(faseAtual);
		rep.fechar();

		
		return novaFase;
	}

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static HanoiControleFase getInstacia(Context ctx) {
		if(controleFase == null) {
			controleFase = new HanoiControleFase(ctx);
		}
		return controleFase;
	}
}