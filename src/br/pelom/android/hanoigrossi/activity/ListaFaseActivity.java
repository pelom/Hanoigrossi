/**
 * 
 */
package br.pelom.android.hanoigrossi.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.listadapter.FaseListaAdapter;
import br.pelom.android.hanoigrossi.model.Fase;
import br.pelom.android.hanoigrossi.utils.Utils;
import br.pelom.android.utils.LogManager;

/**
 * @author pelom
 */
public class ListaFaseActivity extends Activity {

	private FaseListaAdapter adapter;

	static {
		LogManager.debug = true;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		final Window win = getWindow();
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.lista_fase);

		List<Fase> lista = Utils.getInstaciaControleHanoi(this).getFases();
		adapter = new FaseListaAdapter(lista, this);
		ListView listView = (ListView) findViewById(R.id.fases);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long arg3) {

				final Fase fase = (Fase) adapter.getItem(adapter.convertIndex(pos));

				if(fase.isAberta()) {
					Utils.getInstaciaControleHanoi(ListaFaseActivity.this).setFaseAtual(fase);

					startActivity(new Intent(ListaFaseActivity.this, HanoigrossiActivity.class));

					Utils.getInstaciaControleAudio(ListaFaseActivity.this).stopTrilha();

				} else {
					Toast.makeText(ListaFaseActivity.this, 
							"Essa Fase Encontrasse Bloaqueada!", Toast.LENGTH_SHORT).show();

				}
			}
		});
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
				Utils.getInstaciaControleAudio(ListaFaseActivity.this).playTrilha();
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		LogManager.i("hanoigrossi", "onDestroy()");

		Utils.getInstaciaControleAudio(this).descarregarAudio();
	}
}