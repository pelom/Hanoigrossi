package br.pelom.android.hanoigrossi.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.utils.Utils;
import br.pelom.android.utils.Dialogo;
import br.pelom.android.utils.LogManager;

/**
 * @author pelom
 */
public class HanoigrossiMenu extends HanoigrossiBase {

	/**
	 * 
	 */
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.menu);

		//registrar ouvintes dos botoes.
		final Button startGameButton = (Button) findViewById(R.id.StartGame);
		startGameButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				criarAnimacaoClique(v, HanoigrossiActivity.class);
			}
		});

		final Button helpButton = (Button)findViewById(R.id.Help);
		helpButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				criarAnimacaoClique(v, HanoigrossiAjuda.class);
			}
		});

		final Button optionsButton = (Button)findViewById(R.id.Options);
		optionsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				criarAnimacaoClique(v);
			}
		});

		final Button creditsButton = (Button) findViewById(R.id.Credits);
		creditsButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				criarAnimacaoClique(v, HanoigrossiCreditos.class);
			}
		});
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();

		criarAnimacao(findViewById(R.id.StartGame));
		criarAnimacao(findViewById(R.id.Help));
		criarAnimacao(findViewById(R.id.Options));
		criarAnimacao(findViewById(R.id.Credits));
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();

		LogManager.i("hanoigrossi", "onDestroy()");

		Utils.getInstaciaControleAudio(this).descarregarAudio();
	}

	/**
	 * Animacao ao clicar no botao
	 * @param view - botao
	 * @param clazz - Activity a ser inicializada
	 */
	private void criarAnimacaoClique(View view, final Class<? extends Activity> clazz) {
		Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		shake.setAnimationListener(new AnimationListener() {
			@Override public void onAnimationStart(Animation anim) {
				Utils.getInstaciaControleAudio(HanoigrossiMenu.this).playBonus();
			}
			@Override public void onAnimationRepeat(Animation anim) {
			}
			@Override public void onAnimationEnd(Animation anim) {
				startActivity(new Intent(HanoigrossiMenu.this, clazz));
			}
		});

		view.startAnimation(shake);
	}

	/**
	 * Animacao ao clicar no botao
	 * @param view - botao
	 */
	private void criarAnimacaoClique(View view) {
		final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);

		shake.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
				Utils.getInstaciaControleAudio(HanoigrossiMenu.this).playBonus();
			}
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			@Override
			public void onAnimationEnd(Animation arg0) {
				Dialogo.criarDialogConfirmacao(HanoigrossiMenu.this, 
						"Ativar Audio", "Deseja ativar o som?", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Utils.emitirSom = true;

						Utils.getInstaciaControleAudio(HanoigrossiMenu.this).carregarAudio();
					}
				}, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Utils.getInstaciaControleAudio(HanoigrossiMenu.this).descarregarAudio();
						Utils.emitirSom = false;

					}
				}, getString(R.string.sim), getString(R.string.nao)).show();

			}
		});

		view.startAnimation(shake);
	}

	/**
	 * @param view
	 */
	private void criarAnimacao(View view) {
		Animation a = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f);

		a.setDuration(500);
		a.setStartOffset(300);
		//a.setRepeatMode(Animation.RESTART);
		//a.setRepeatCount(1);
		a.setInterpolator(AnimationUtils.loadInterpolator(this,
				android.R.anim.accelerate_interpolator));

		view.startAnimation(a);
	}
}