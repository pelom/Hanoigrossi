package br.pelom.android.hanoigrossi.activity;


import java.util.List;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.pelom.android.hanoigrossi.HanoiGrossiView;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.hanoi.Hanoigrossi;
import br.pelom.android.hanoigrossi.hanoi.IHanoigrossiBoard;
import br.pelom.android.hanoigrossi.hanoi.IHanoigrossiGameView;
import br.pelom.android.hanoigrossi.listadapter.FaseListaAdapter;
import br.pelom.android.hanoigrossi.model.Fase;
import br.pelom.android.hanoigrossi.repositorio.Banco;
import br.pelom.android.hanoigrossi.repositorio.FaseRepositorio;
import br.pelom.android.hanoigrossi.utils.Rotate3dAnimation;
import br.pelom.android.hanoigrossi.utils.Utils;
import br.pelom.android.utils.Dialogo;
import br.pelom.android.utils.LogManager;

/**
 * Classe responsavel por centralizar todas as partes do jogo
 * @author pelom
 */
public class HanoigrossiActivity extends HanoigrossiBase implements IHanoigrossiGameView {
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

	/** Adapter de fases **/
	private FaseListaAdapter adapter;

	/** View principal **/
	private ViewGroup mContainer;

	/** View do game **/
	private ViewGroup gameHanoigrossi;

	/** Lista de fases **/
	private ListView listView;

	static {
		LogManager.debug = true;

	}

	/**
	 * 
	 */
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		//recuperar a lista de fases.
		final List<Fase> lista = Utils.getInstaciaControleHanoi(this).getFases();
		adapter = new FaseListaAdapter(lista, this);

		//recuperar lista.
		listView = (ListView) findViewById(R.id.fases);
		//configurar adapter
		listView.setAdapter(adapter);
		//registrar ouvinte de selecao
		listView.setOnItemClickListener(new FaseSelecionada());

		//recuperar Layout principal
		this.mContainer = (ViewGroup) findViewById(R.id.container);
		//recuperar layout do game
		this.gameHanoigrossi = (ViewGroup) findViewById(R.id.GameHanoigrossi);

		//recuperar componente hanoigrossi view
		this.hanoigrossiview =  (HanoiGrossiView) findViewById(R.id.HanoiView);

		//criar jogo
		this.hanoigrossi = new Hanoigrossi(this, hanoigrossiview);
		//registrar ouvinte dos eventos do jogo
		this.hanoigrossi.setGame(this);

		//recuperar componentes do menu de informacoes
		this.textViewDiscos = (TextView) findViewById(R.id.TextViewDiscos);
		this.textViewNivel =  (TextView) findViewById(R.id.TextViewNivel);
		this.textViewMovi =  (TextView) findViewById(R.id.TextViewMovi);
		this.cromometro = (Chronometer) findViewById(R.id.chronometer);

		// criar animacao na lista.
		criarAnimacao(listView);

		// Since we are caching large views, we want to keep their cache
		// between each animation
		mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
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

		voltar();
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();

		LogManager.i("hanoigrossi", "OnStart()");

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				adapter.notifyDataSetChanged();

				Utils.getInstaciaControleAudio(HanoigrossiActivity.this).playTrilha();
			}
		}, 500);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();

		LogManager.i("hanoigrossi", "onPause()");

		Utils.getInstaciaControleAudio(this).stopTrilha();
	}
	
	/***********************************************************************
	 * 		Metodos e classe para o tratamento da rotacao 3D e aninacoes
	 ***********************************************************************/

	/**
	 * Setup a new 3D rotation on the container view.
	 *
	 * @param position the item that was clicked to show a picture, or -1 to show the list
	 * @param start the start angle at which the rotation must begin
	 * @param end the end angle of the rotation
	 */
	private void applyRotation(int position, float start, float end) {
		// Find the center of the container
		final float centerX = mContainer.getWidth() / 2.0f;
		final float centerY = mContainer.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Rotate3dAnimation rotation =
			new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);

		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(position));
		//iniciar animacao
		mContainer.startAnimation(rotation);
	}

	/**
	 * This class listens for the end of the first half of the animation.
	 * It then posts a new action that effectively swaps the views when the container
	 * is rotated 90 degrees and thus invisible.
	 */
	private final class DisplayNextView implements Animation.AnimationListener {
		private final int mPosition;

		/**
		 * Contrutor da classe.
		 * @param position - posicao a item selecionado
		 */
		private DisplayNextView(int position) {
			mPosition = position;
		}

		public void onAnimationStart(Animation animation) {
		}
		public void onAnimationRepeat(Animation animation) {
		}
		public void onAnimationEnd(Animation animation) {
			//alguma item foi selecionado?
			if(mPosition > -1) {
				//recuperar fase selecionada.
				final Fase fase = (Fase) adapter.getItem(adapter.convertIndex(mPosition));
				//configurar fase selecionada.
				Utils.getInstaciaControleHanoi(HanoigrossiActivity.this).setFaseAtual(fase);	

				//iniciar jogo.
				hanoigrossi.iniciar();
			}
			//termina rotacao.
			mContainer.post(new SwapViews(mPosition));
		}
	}

	/**
	 * This class is responsible for swapping the views and start the second
	 * half of the animation.
	 */
	private final class SwapViews implements Runnable {
		private final int mPosition;

		/**
		 * Construtor da classe.
		 * @param position
		 */
		public SwapViews(int position) {
			mPosition = position;
		}

		/**
		 * 
		 */
		public void run() {
			final float centerX = mContainer.getWidth() / 2.0f;
			final float centerY = mContainer.getHeight() / 2.0f;

			Rotate3dAnimation rotation = null;

			//item selecionado?
			if (mPosition > -1) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

				//esconder lista.
				listView.setVisibility(View.GONE);
				// mostrar game
				gameHanoigrossi.setVisibility(View.VISIBLE);
				gameHanoigrossi.requestFocus();

				//criar rotacao.
				rotation = new Rotate3dAnimation(90, 360, centerX, centerY, 310.0f, false);

			} else {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

				//escoder game
				gameHanoigrossi.setVisibility(View.GONE);
				//mostrar lista.
				listView.setVisibility(View.VISIBLE);
				listView.requestFocus();

				//criar rotacao.
				rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
			}

			rotation.setDuration(500);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());

			mContainer.startAnimation(rotation);
		}
	}

	/**
	 * Criar aninamacao na lista.
	 * @param listaView
	 */
	private void criarAnimacao(ListView listaView) { 
		AnimationSet set = new AnimationSet(true);

		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(50);
		set.addAnimation(animation);

		animation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, -1.0f,Animation.RELATIVE_TO_SELF, 0.0f
		);
		animation.setDuration(100);
		set.addAnimation(animation);

		LayoutAnimationController controller =
			new LayoutAnimationController(set, 0.5f);

		listaView.setLayoutAnimation(controller);
	}

	/***************************************************************************************/

	/**
	 * Voltar
	 */
	private void voltar() {
		// o game esta escondido?
		if(gameHanoigrossi.getVisibility() == View.GONE) {
			//voltar ao MENU
			finish();

		} else if(listView.getVisibility() == View.GONE) {
			//rotacionar
			applyRotation(-1, 360, 90);
			
			//a lista mudou.
			adapter.notifyDataSetChanged();

			Utils.getInstaciaControleAudio(this).playTrilha();

			// criar animacao na lista.
			criarAnimacao(listView);
		}
	}

	/****************************************************************
	 *	Tratar eventos do teclado 
	 *******************************************************/

	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//o botao voltar foi pressionado?
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			voltar();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	/**
	 * Classe responsavel por tratar o evento de selecao da lista de fase.
	 * @author pelom
	 */
	private class FaseSelecionada implements OnItemClickListener {

		@Override
		public void onItemClick(final AdapterView<?> adapterView, final View view, final int pos, final long arg3) {
			final Fase fase = (Fase) adapter.getItem(adapter.convertIndex(pos));

			//a fase está disponivel?
			if(fase.isAberta()) {

				Utils.getInstaciaControleAudio(HanoigrossiActivity.this).stopTrilha();

				//aplicar rotacao.
				applyRotation(pos, 0, 90);

			} else {
				Toast.makeText(HanoigrossiActivity.this, 
						"Essa Fase Encontrasse Bloaqueada!", Toast.LENGTH_SHORT).show();

			}
		}
	}
}