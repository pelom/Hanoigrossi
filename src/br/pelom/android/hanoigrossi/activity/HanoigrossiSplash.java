package br.pelom.android.hanoigrossi.activity;
/**
 * 
 */


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.utils.Utils;
import br.pelom.android.utils.Dialogo;

/**
 * @author pelom
 */
public class HanoigrossiSplash extends HanoigrossiBase implements Runnable {

	/** Imagem logo marca **/
	private ImageView logomarca;

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.splash);

		//logo marcar
		this.logomarca = (ImageView) findViewById(R.id.logomarca);
		this.logomarca.setImageResource(R.drawable.pelommedrado);

		final Handler handler = new Handler();

		handler.postDelayed(new SplashGdesigner(), 4000);
		handler.postDelayed(new SplashHanoigrossi(), 8000);
		handler.postDelayed(this, 12000);
	}

	/**
	 * @author pelom
	 */
	private class SplashGdesigner implements Runnable {
		@Override
		public void run() {
			logomarca.setImageResource(R.drawable.g_designer);
		}
	}

	/**
	 * @author pelom
	 */
	private class SplashHanoigrossi implements Runnable {
		@Override
		public void run() {
			logomarca.setImageResource(R.drawable.splash);
		}
	}

	/**
	 * 
	 */
	@Override public void run() {
		Dialogo.criarDialogConfirmacao(HanoigrossiSplash.this, 
				"Ativar Audio", "Deseja ativar o som?", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Utils.emitirSom = true;

				Utils.getInstaciaControleAudio(HanoigrossiSplash.this).carregarAudio();

				iniciar();

			}
		}, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				Utils.emitirSom = false;

				iniciar();

			}
		}, getString(R.string.sim), getString(R.string.nao)).show();
	}

	/**
	 * 
	 */
	private void iniciar() {
		startActivity(new Intent(this, HanoigrossiMenu.class));

		finish();
	}
}