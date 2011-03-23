package br.pelom.android.hanoigrossi.activity;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.TextView;
import br.pelom.android.hanoigrossi.HanoiGrossiView;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.hanoi.Hanoigrossi;
import br.pelom.android.hanoigrossi.hanoi.IHanoigrossiBoard;
import br.pelom.android.hanoigrossi.hanoi.IHanoigrossiGameView;
import br.pelom.android.hanoigrossi.model.Fase;
import br.pelom.android.hanoigrossi.repositorio.Banco;
import br.pelom.android.hanoigrossi.repositorio.FaseRepositorio;
import br.pelom.android.hanoigrossi.utils.Utils;
import br.pelom.android.utils.Dialogo;

/**
 * @author pelom
 */
public class HanoigrossiActivity extends Activity implements IHanoigrossiGameView {
	/** Cronometro do jogo **/
	private Chronometer cromometro = null;
	/** Text do menu da fase **/
	private TextView textViewDiscos = null;
	private TextView textViewNivel = null;
	private TextView textViewMovi = null;

	/** Controlado da logico do jogo **/
	private Hanoigrossi hanoigrossi = null;
	/** Controlado de redenrizacao do jogo **/
	private HanoiGrossiView hanoigrossiview = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		final Window win = getWindow();
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		this.hanoigrossiview =  (HanoiGrossiView) findViewById(R.id.HanoiView);

		this.hanoigrossi = new Hanoigrossi(this, hanoigrossiview);
		this.hanoigrossi.setGame(this);

		this.textViewDiscos = (TextView) findViewById(R.id.TextViewDiscos);
		this.textViewNivel =  (TextView) findViewById(R.id.TextViewNivel);
		this.textViewMovi =  (TextView) findViewById(R.id.TextViewMovi);
		this.cromometro = (Chronometer) findViewById(R.id.chronometer);

		this.hanoigrossi.iniciar();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void actionNovaFase(IHanoigrossiBoard board, Fase fase) {
		//iniciar contagem.
		this.cromometro.stop();
		this.cromometro.setBase(SystemClock.elapsedRealtime());

		//configurar o novo estado dos objetos.
		this.hanoigrossiview.setBoard(board);
		//numero de discos
		this.textViewDiscos.setText(getString(R.string.discos) + ": " + fase.getDisco());
		//numero do nivel.
		this.textViewNivel.setText(getString(R.string.nivel)+": " + fase.getNivel());

		//numero de movimento.
		this.textViewMovi.setText(getString(R.string.movimentos) + ": " + hanoigrossi.getBoard().getMoveCount() + 
				" " + getString(R.string.de) +  " " + hanoigrossi.getBoard().getMinMoves());

		//iniciar contagem.
		this.cromometro.start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void actionMoverDisco(boolean sucesso, int min, int move) {
		//numero de movimento.
		this.textViewMovi.setText(getString(R.string.movimentos) + ": " + move + 
				" " + getString(R.string.de) +  " " + min);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void actionObjetivoConcluido(boolean min, Fase fase) {

		this.cromometro.stop();
		fase.setTempo(this.cromometro.getText().toString());
		fase.setNumeroMov(hanoigrossi.getBoard().getMoveCount());

		final FaseRepositorio rep = 
			new FaseRepositorio(Banco.getSQLiteHelper(this), this);
		rep.abrir();
		rep.atualizar(fase);
		rep.fechar();

		finish();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override public void actionGameOver(int min, int move) {
		Dialogo.criarDialogInformativo(HanoigrossiActivity.this, 
				getString(R.string.gameover_titulo), 
				getString(R.string.gameover_msg), true).show();

		hanoigrossi.iniciar();
	}
}