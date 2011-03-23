package br.pelom.android.hanoigrossi.utils;

import android.content.Context;
import android.os.Vibrator;
import br.pelom.android.hanoigrossi.hanoi.HanoiControleFase;

/**
 * @author pelom
 */
public class Utils {
	public static boolean emitirSom = false;

	/**
	 * 
	 * @param disco
	 * @return
	 */
	public static int moviMinimo(int disco) {
		return (int) Math.pow(2, disco) - 1;
	}

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static HanoiControleFase getInstaciaControleHanoi(Context ctx) {
		return HanoiControleFase.getInstacia(ctx);
	}

	/**
	 * 
	 * @param ctx
	 * @param emitirSom
	 * @return
	 */
	public static AudioPlay getInstaciaControleAudio(Context ctx) {
		return AudioPlay.getInstacia(ctx, emitirSom);
	}

	/**
	 * 
	 * @param ctx
	 */
	public static void msgVibracall(Context ctx, long vb) {
		if(!emitirSom) return;
		Vibrator v = (Vibrator) ctx.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(vb);
	}
}
