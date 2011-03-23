package br.pelom.android.hanoigrossi.activity;
/**
 * 
 */


import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.utils.AudioPlay;
import br.pelom.android.hanoigrossi.utils.Utils;
import br.pelom.android.utils.Dialogo;

/**
 * @author pelom
 */
public class HanoigrossiSplash extends Activity implements Runnable {

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		final Window win = getWindow();
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.splash);

		final Handler handler = new Handler();
		//aqui é definido o delay do handler em milisegundos
		handler.postDelayed(this, 2000);
	}

	@Override
	public void run() {

		Dialogo.criarDialogConfirmacao(HanoigrossiSplash.this, 
				"Ativar Audio", "Deseja ativar o som?", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Utils.emitirSom = true;
				iniciar();
			}
		}, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Utils.emitirSom = false;
				iniciar();
			}
		}, "Sim", "Não").show();
	}

	/**
	 * 
	 */
	private void iniciar() {
		startActivity(new Intent(this, HanoigrossiMenu.class));

		finish();
	}
}