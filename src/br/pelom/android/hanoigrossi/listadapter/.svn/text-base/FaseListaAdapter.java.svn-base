/**
 * 
 */
package br.pelom.android.hanoigrossi.listadapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.pelom.android.hanoigrossi.R;
import br.pelom.android.hanoigrossi.model.Fase;
import br.pelom.android.hanoigrossi.utils.Utils;

/**
 * @author pelom
 */
public class FaseListaAdapter extends BaseAdapter {
	/** Lista de fase **/
	private List<Fase> lista = null;

	/** Contexto **/
	private Context ctx = null;

	/** Numero de labels da lista **/
	private static final int NUM_LABELS = 3;

	/**
	 * Construtor da classe.
	 * 
	 * @param lista
	 * @param ctx
	 */
	public FaseListaAdapter(List<Fase> lista, 
			Context ctx) {
		super();

		this.ctx = ctx;
		this.lista = lista;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return lista.size() + NUM_LABELS;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return lista.get(index);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	/* (non-Javadoc)
	 * @see android.widget.BaseAdapter#isEnabled(int)
	 */
	@Override public boolean isEnabled(int position) {
		if(position == 0 || position == 6 || position == 12) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final LayoutInflater inflater = (LayoutInflater) 
		ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if(position == 0) {
			return criarViewLabel(inflater, ctx.getString(R.string.iniciante));

		} else if(position == 6) {
			return criarViewLabel(inflater, ctx.getString(R.string.intermediario));

		} else if( position == 12) {
			return criarViewLabel(inflater, ctx.getString(R.string.avancado));

		}

		Fase fase = lista.get(convertIndex(position));
		final int minMov = Utils.moviMinimo(fase.getDisco());

		View view = inflater.inflate(R.layout.lista_item_fase, null);

		TextView textDisco =  (TextView) view.findViewById(R.id.TextViewListNumDisco);
		textDisco.setText(fase.getDisco() + " " + ctx.getString(R.string.discos));

		TextView textMov =  (TextView) view.findViewById(R.id.TextViewListMovimentos);
		textMov.setText(fase.getNumeroMov() + " " + ctx.getString(R.string.de) +  " " + minMov);
		TextView textTempo =  (TextView) view.findViewById(R.id.TextViewListTempo);
		textTempo.setText(fase.getTempo());

		TextView textNome =  (TextView) view.findViewById(R.id.TextViewListNivel);
		textNome.setText(ctx.getString(R.string.nivel) + ":"+ fase.getNivel());

		ImageView imageView =  (ImageView) view.findViewById(R.id.ImageViewa);
		if(!fase.isAberta()) {
			imageView.setImageResource(R.drawable.ic_lock_lock);

		} else {
			if(fase.getNumeroMov() == Utils.moviMinimo(fase.getDisco())) {
				imageView.setImageResource(R.drawable.btn_star_big_on);

			} else {
				imageView.setImageResource(R.drawable.btn_star_big_off_disable);	

			}
		}

		return view;
	}

	/**
	 * 
	 * @param stg
	 */
	private View criarViewLabel(LayoutInflater inflater, String stg) {
		View viewInflater = inflater.inflate(R.layout.list_label_fase, null);
		TextView label =  (TextView) viewInflater.findViewById(R.id.labelstrylelist);
		label.setText(stg);

		return viewInflater;
	}

	/**
	 * 
	 * @param pos
	 * @return
	 */
	public int convertIndex(int pos) {
		if(pos > 0 && pos < 6) {
			return pos-1;

		} else if(pos > 6 && pos < 12) {
			return pos-2;

		} else {
			return pos-3;

		}
	}
}