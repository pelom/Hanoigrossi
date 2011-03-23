package br.pelom.android.hanoigrossi.hanoi;

import android.content.Context;
import android.os.Handler;
import br.pelom.android.hanoigrossi.HanoiGrossiView;
import br.pelom.android.hanoigrossi.model.Fase;
import br.pelom.android.hanoigrossi.utils.AudioPlay;
import br.pelom.android.hanoigrossi.utils.Utils;


/**
 * @author pelom
 */
public class Hanoigrossi implements IHanoiAcao {
	/** Primeira haste **/
	public static final int PEG1 = 0;

	/** Segunda haste **/
	public static final int PEG2 = 1;

	/** Terceira haste **/
	public static final int PEG3 = 2;

	/** Numero minimo de discos **/
	public static final int MINDISCS = 3;

	/** Numero maximo de discos **/
	public static final int MAXDISCS = 12;

	/** Classe controladora da regra de movimentacao dos discos **/
	private IHanoigrossiBoard board;

	/** Classe ouvinte dos estados do jogo **/
	private IHanoigrossiGameView game;

	/** Classe controladora das fases do jogo **/
	private IHanoiControleFase controleFase;

	/** Classe rederizadora da GUI **/
	private HanoiGrossiView view;

	/** Classe responsavel por reproduzir os sons **/
	private AudioPlay audio = null;

	/** Context **/
	private Context ctx = null;

	/**
	 * Construtor da classe.
	 * @param ctx
	 * @param view
	 */
	public Hanoigrossi(Context ctx, HanoiGrossiView view ) {
		super();

		this.ctx = ctx;
		this.view = view;
		this.board = new HanoigrossiBoard(MINDISCS, PEG1, MINDISCS);
		this.board.setHanoiAcao(this);
		this.controleFase = Utils.getInstaciaControleHanoi(ctx);
		this.audio = Utils.getInstaciaControleAudio(ctx);
	}

	/**
	 * Novo jogo
	 */
	public void iniciar() {
		this.board = 
			new HanoigrossiBoard(controleFase.getFaseAtual().getDisco(), PEG1, MINDISCS);
		this.board.setHanoiAcao(this);

		if(game != null) {
			game.actionNovaFase(board, controleFase.getFaseAtual());
		}
	}

	/**
	 * @return the board
	 */
	public IHanoigrossiBoard getBoard() {
		return board;
	}

	/**
	 * @return the game
	 */
	public IHanoigrossiGameView getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(IHanoigrossiGameView game) {
		this.game = game;
	}

	/*****************************************************
	 * Metodos implementados da interface IHanoiAcao
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override public void actionMoverDisco(final boolean sucesso, final int min, final int move) {
		//criar handler para nao segurar a linha de execucao.
		Handler handler = new Handler();
		handler.post(new Runnable() {
			@Override
			public void run() {
				//movimento ocorreu com sucesso?
				if(sucesso) {
					audio.playMoverDisco();

				} else {
					audio.playMoverDiscoFalha();

				}

				//o momento ocorre com sucesso e o passo foi correto?
				int haste;
				if(sucesso && (haste = board.verificarPassoCorreto()) != -1) {
					audio.playBonus();
					
					Utils.msgVibracall(ctx, 100);

					//desenhar animacao.
					view.anicacaoDiscoHaste(haste);
				}

				if(game != null) {
					game.actionMoverDisco(sucesso, min, move);
				}

				//a fase requer o minimo de movimentos?
				if(controleFase.getFaseAtual().isMovimentos()) {
					//movimentos utrapasso o movimento minimo?
					if(min < move) {
						audio.playGameOver();

						if(game != null) {
							game.actionGameOver(min, move);
						}
					}
				}

				//o objetivo foi concluido
				if(board.isbjetivoConcluido()) {
					audio.playConclusaoFase();

					final Fase fase = controleFase.getFaseAtual();
					controleFase.proximaFase();

					if(game != null) {
						game.actionObjetivoConcluido(min == move, fase);
					}
				}
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionDiscoSelecionado() {
		Utils.msgVibracall(ctx, 50);
	}
}