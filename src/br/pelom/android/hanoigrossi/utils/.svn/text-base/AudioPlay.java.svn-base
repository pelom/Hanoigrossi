/**
 * 
 */
package br.pelom.android.hanoigrossi.utils;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import br.pelom.android.hanoigrossi.R;

/**
 * @author pelom
 */
public class AudioPlay {
	public static final int SOUND_CLICK = 1;  
	public static final int SOUND_FLIP = 2; 
	public static final int SOUND_VICTORY = 3; 
	public static final int SOUND_BONUS = 4; 
	public static final int SOUND_GAMEOVER = 5; 
	public static final int SOUND_FIBRA = 6; 
	private static AudioPlay audioPlay;

	private SoundPool soundPool = null;  
	private HashMap<Integer, Integer> soundPoolMap = null;  
	private Context ctx = null;
	private int idStream;
	private boolean emitirSom;

	/**
	 * Construtor da classe.
	 * 
	 * @param ctx
	 */
	private AudioPlay(Context ctx, boolean emitirSom) {
		super();

		this.emitirSom = emitirSom;
		this.ctx = ctx;
	}

	/**
	 * 
	 */
	public void carregarAudio() {
		if(!emitirSom) return;
		soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();  
		soundPoolMap.put(SOUND_FIBRA, soundPool.load(ctx, R.raw.fibra, 1));
		soundPoolMap.put(SOUND_CLICK, soundPool.load(ctx, R.raw.click, 1));
		soundPoolMap.put(SOUND_FLIP, soundPool.load(ctx, R.raw.flippiece, 1));  
		soundPoolMap.put(SOUND_VICTORY, soundPool.load(ctx, R.raw.victory, 1));
		soundPoolMap.put(SOUND_BONUS, soundPool.load(ctx, R.raw.gnometris, 1));
		soundPoolMap.put(SOUND_GAMEOVER, soundPool.load(ctx, R.raw.gameover, 1));
	}  

	/**
	 * 
	 */
	public void descarregarAudio() {
		audioPlay = null;

		if(!emitirSom) return;
		soundPool.unload(soundPoolMap.get(SOUND_FIBRA));
		soundPool.unload(soundPoolMap.get(SOUND_CLICK));
		soundPool.unload(soundPoolMap.get(SOUND_FLIP));
		soundPool.unload(soundPoolMap.get(SOUND_VICTORY));
		soundPool.unload(soundPoolMap.get(SOUND_BONUS));
		soundPool.unload(soundPoolMap.get(SOUND_GAMEOVER));
	}

	/**
	 * 
	 * @param sound
	 */
	private void playSound(int sound, boolean repetir) {
		if(!emitirSom) return;
		/* Updated: The next 4 lines calculate the current volume in a scale of 0.0 to 1.0 */  
		AudioManager mgr = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);    
		float volume =  mgr.getStreamVolume(AudioManager.STREAM_MUSIC);  
		volume = volume / mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

		/* Play the sound with the correct volume */  
		idStream = soundPool.play(soundPoolMap.get(sound), 
				volume, volume, 1, (repetir) ? -1 : 0, 1f);
	}

	/**
	 * 
	 */
	public void playMoverDisco() {  
		playSound(SOUND_CLICK, false);  
	} 

	/**
	 * 
	 */
	public void playMoverDiscoFalha() {  
		playSound(SOUND_FLIP, false);  
	} 

	/**
	 * 
	 */
	public void playConclusaoFase() {  
		playSound(SOUND_VICTORY, false);  
	} 

	/**
	 * 
	 */
	public void playGameOver() {  
		playSound(SOUND_GAMEOVER, false);  
	} 

	/**
	 * 
	 */
	public void playBonus() {  
		playSound(SOUND_BONUS, false);  
	}

	/**
	 * 
	 */
	public void playTrilha() {  
		playSound(SOUND_FIBRA, true);  
	}

	/**
	 * 
	 */
	public void stopTrilha() {
		if(!emitirSom) return;
		soundPool.stop(idStream);
	}

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public static AudioPlay getInstacia(Context ctx, boolean emitirSom) {
		if(audioPlay == null) {
			audioPlay = new AudioPlay(ctx, emitirSom);
		}
		return audioPlay;
	} 
}