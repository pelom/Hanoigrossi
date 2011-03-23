/**
 * 
 */
package br.pelom.android.hanoigrossi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import br.pelom.android.hanoigrossi.hanoi.Hanoigrossi;
import br.pelom.android.hanoigrossi.hanoi.IHanoigrossiBoard;
import br.pelom.android.hanoigrossi.model.Disco;
import br.pelom.android.hanoigrossi.utils.Cor;

/**
 * @author pelom
 */
public class HanoiGrossiView extends View {
	/** Tamanho do disco **/
	static final int DISCHEIGHT = 15;

	/** Controle de desenho **/
	private float pegSpace;
	private float x;
	private float y;
	private Disco dragged;

	/** Manipulador de movimentacao de discos **/
	private IHanoigrossiBoard board = null;

	/** Disco a sofrer a acao **/
	private Disco sourceDisc;
	/** Haste de origem **/
	private int sourcePeg;
	/** Haste de destino **/
	private int targetPeg;

	/**
	 * 
	 * @param context
	 */
	public HanoiGrossiView(Context context) {
		this(context,  null);
	}

	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public HanoiGrossiView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}
	/**
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public HanoiGrossiView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_fase));
		//setBackgroundColor(Color.BLACK);
		setFocusable(true);
	}

	/**
	 * 
	 * @param haste
	 */
	public void anicacaoDiscoHaste(final int haste) {
		
		final Runnable runSelect = new Runnable() {
			
			@Override
			public void run() {
				board.selecionarDisco(haste, true);
				invalidate();
			}
		};
		final Runnable runDesSelect = new Runnable() {
			
			@Override
			public void run() {
				board.selecionarDisco(haste, false);
				invalidate();
			}
		};
		
		Handler handler = new Handler();
		handler.post(runSelect);
		handler.postDelayed(runDesSelect, 150);
		handler.postDelayed(runSelect, 300);
		handler.postDelayed(runDesSelect, 450);
	}
	
	/* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float x = event.getX();
		float y = event.getY();

		switch (action) { 
		case (MotionEvent.ACTION_DOWN):
			//recuperar haste da acao
			sourcePeg = pixelToPeg(x, y);

		if (board.isStartPeg(sourcePeg)) {
			//recuperar disco do top da haste
			sourceDisc = board.getTopDisc(sourcePeg);

			if(sourceDisc.getNum() > 0) {
				board.discoSilencionado(sourceDisc);
				
				redesenharView(sourceDisc, x, y); 
			}
		}

		break; 

		case (MotionEvent.ACTION_UP):
			targetPeg = pixelToPeg(x, y);

		//o disco selecionado pode se movido?
		if (sourceDisc != null && board.moveDisc(sourceDisc, sourcePeg, targetPeg)) {

		}

		//resenhar tela
		redesenharView(null, 0, 0);

		sourceDisc = null;
		break; 

		case (MotionEvent.ACTION_MOVE):
			redesenharView(sourceDisc, x, y);

		break;
		}

		return true;
	}

	/**
	 * Conversao dos eventos de mouse (seleciona a haste)
	 * 
	 * @param x
	 * @param y
	 * 
	 * @return
	 */
	private int pixelToPeg(float x, float y) {
		double peg = (double) x / pegSpace;

		if (peg < 2) {
			return Hanoigrossi.PEG1;

		} else if (peg < 4) {
			return Hanoigrossi.PEG2;

		} else {
			return Hanoigrossi.PEG3;
		}

	}

	/**********************************************************************************/

	public void redesenharView(Disco dragged, float x, float y) {
		this.x = x;
		this.y = y;
		this.dragged = dragged;

		invalidate();
	}

	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		//simetria entre as hastes
		pegSpace = getWidth() / 6;
		//centro da primeira haste
		onDrawHaste(canvas, pegSpace);
		//centro da segunda haste
		onDrawHaste(canvas, 3 * pegSpace);
		//centro da terceira haste
		onDrawHaste(canvas, 5 * pegSpace);
		
		//varrer as hastes.
		for (int p = 0; p <= 2; p++) {
			//recperar todos os discos da haste
			Disco[] disc = board.getDisco(p);
			
			//for de discos
			for (int d = 0; d < disc.length; d++) {
				//recuperar disco.
				final Disco dc = disc[d];
				int width = dc.getDiscWidth();
				//calcular posicoes X e Y
				float xTem = (2 * p +1) * pegSpace - width/2;
				float yTem = getHeight()-(d + 1) * DISCHEIGHT;
				
				//desenhar disco
				onDrawDisco(canvas, xTem, yTem, dc);
			}
		}

		//desenha disco arrastado
		if (dragged != null) {
			//desenhar disco
			onDrawDisco(canvas, x, y, dragged);
		}
	}

	/**
	 * 
	 * @param canvas
	 */
	protected void onDrawHaste(Canvas canvas, float posX) {
		float x = posX;
		x -= 5;                         // Coordenada da haste 
		//altura do painel 
		int h = getHeight(); 
		//topo da haste e 80% da tela
		float ha = (float) (getHeight() - (getHeight() * 0.8));		

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		Cor.MARROM_MAIS_ESCURO.setCor(paint);
		paint.setAntiAlias(true);

		//ponta da haste
		Path path = new Path();
		path.moveTo(0, -7);
		path.lineTo(5, 0);
		path.lineTo(-5, 0);
		path.close();
		path.offset(posX+1, ha);
		canvas.drawPath(path, paint);

		Cor.MARROM_ESCURO.setCor(paint);
		canvas.drawRect(x,   ha, x+2, ha +(h-ha), paint);//2 pixel
		Cor.MARROM_CLARO.setCor(paint);
		canvas.drawRect(x+2, ha, x+6, ha + (h-ha), paint);//4 pixels
		Cor.MARROM_ESCURO.setCor(paint);
		canvas.drawRect(x+6, ha, x+8, ha + (h-ha), paint);//2 pixels
		Cor.MARROM_MAIS_ESCURO.setCor(paint);
		canvas.drawRect(x+8, ha, x+12, ha + (h-ha), paint);//4 pixels
	}

	/**
	 * 
	 * @param canvas
	 */
	protected void onDrawDisco(Canvas canvas, float x, float y, Disco disco) {
		final int width = disco.getDiscWidth();
		//acerta x
		x = fixX(x, width);
		//acerta y
		y = fixY(y, DISCHEIGHT);               

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		if(disco.isSelecionado()) {
			Cor.LARANJA.setCor(paint);
		} else {
			disco.getCorClara().setCor(paint);		
		}
	
		canvas.drawLine(x+4, y, x+width-4, y, paint);
		canvas.drawLine(x+2, y+1, x+width-2, y+1, paint);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(x, y+7, x+width, (y+7) + 1, paint);

		if(disco.isSelecionado()) {
			Cor.LARANJA.setCor(paint);
		} else {
			disco.getCorLuz().setCor(paint);	
		}
		
	
		canvas.drawLine(x+1, y+2, x+width-1, y+2, paint);
		canvas.drawRect(x, y+5, x+width, (y+5) + 1, paint);

		Cor.COLOR5.setCor(paint);
		canvas.drawLine(x+1, y+3, x+width-1, y+3, paint);
		canvas.drawLine(x, y+4, x+width, y+4, paint);

		if(disco.isSelecionado()) {
			Cor.LARANJA.setCor(paint);
		} else {
			disco.getCorEscura().setCor(paint);
		}
		
		canvas.drawRect(x, y+9, x+width, (y+9) + 1, paint);
		canvas.drawLine(x+1, y+11, x+width-1, y+11, paint);

		if(disco.isSelecionado()) {
			Cor.LARANJA.setCor(paint);
		} else {
			disco.getCorMaisEscuro().setCor(paint);
		}
		
		canvas.drawLine(x+1, y+12, x+width-1, y+12, paint);
		canvas.drawLine(x+2, y+13, x+width-2, y+13, paint);
		canvas.drawLine(x+4, y+14, x+width-4, y+14, paint);
	}

	/** 
	 * Acerta a coodenada x do disco arrastado 
	 * 
	 * @param x
	 * @param width
	 * @return
	 */
	private float fixX(float x, int width) { 
		if (x < 0) x = 0;
		if (x > getWidth() - width) x = getWidth() - width;
		return x;
	}

	// Acerta a coordenada y do disco arrastado
	private float fixY(float y, int height) {  
		if (y < height) y = 0;
		if (y > getHeight() - height) y = getHeight() - height;
		return y;
	}

	/**
	 * @return the board
	 */
	public IHanoigrossiBoard getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(IHanoigrossiBoard board) {
		this.board = board;
		this.invalidate();
	}

	
	/**
	 * 
	 * @param xPos
	 * @param yPos
	 * @return
	 *
	public boolean isSelecionado(float xPos, float yPos) {
		Rect rectangulo = new Rect((int) x,(int) y,(int) x + width,(int) y + DISCHEIGHT);
		return rectangulo.contains((int)xPos, (int) yPos);
	}*/
}