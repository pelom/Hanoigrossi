package br.pelom.android.hanoigrossi.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.utils.AudioPlay;
import br.pelom.android.hanoigrossi.utils.Utils;
import br.pelom.android.utils.Dialogo;

public class HanoigrossiMenu extends Activity {

	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		final Window win = getWindow();
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.menu);

		Button StartGameButton = (Button) findViewById(R.id.StartGame);
		StartGameButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//carregar recursos
				AudioPlay audioPlay = Utils.getInstaciaControleAudio(HanoigrossiMenu.this);
				audioPlay.carregarAudio();
				
				startActivity(new Intent(HanoigrossiMenu.this, ListaFaseActivity.class));
			}
		});

		Button HelpButton = (Button)findViewById(R.id.Help);
		HelpButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(HanoigrossiMenu.this, HanoigrossiAjuda.class));
			}
		});

		Button OptionsButton = (Button)findViewById(R.id.Options);
		OptionsButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Dialogo.criarDialogConfirmacao(HanoigrossiMenu.this, 
						"Ativar Audio", "Deseja ativar o som?", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Utils.emitirSom = true;
						
					}
				}, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Utils.emitirSom = false;
						
					}
				}, getString(R.string.sim), getString(R.string.nao)).show();
			}
		});

		Button CreditsButton = (Button)findViewById(R.id.Credits);
		CreditsButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(HanoigrossiMenu.this, HanoigrossiCreditos.class));
			}
		});
	}
}