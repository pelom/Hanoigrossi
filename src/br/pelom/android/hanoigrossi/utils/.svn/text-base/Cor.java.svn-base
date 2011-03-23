package br.pelom.android.hanoigrossi.utils;

import java.util.HashMap;

import android.graphics.Paint;

/**
 * @author pelom
 */
public class Cor {

	public static final Cor MARROM_MAIS_ESCURO = new Cor(102, 51, 0, 255);
	public static final Cor MARROM_ESCURO = new Cor(153,102, 0, 255);
	public static final Cor MARROM_CLARO = new Cor(204,153,51, 255);
	public static final Cor MARROM_COLOR4 = new Cor(255, 204, 0, 255);

	public static final Cor AZUL_MAIS_ESCURO = new Cor(16, 78, 139, 255);
	public static final Cor AZUL_ESCURO = new Cor(24,116, 205, 255);
	public static final Cor AZUL_CLARO = new Cor(30,144, 255, 255);
	public static final Cor AZUL_COLOR4 = new Cor(170, 187, 204, 255);

	public static final Cor ROSO_MAIS_ESCURO = new Cor(71, 60, 139, 255);
	public static final Cor ROSO_ESCURO = new Cor(105, 89, 205, 255);
	public static final Cor ROSO_CLARO = new Cor(122,103, 238, 255);
	public static final Cor ROSO_COLOR4 = new Cor(131, 111, 255, 255);

	public static final Cor VERDE_MAIS_ESCURO = new Cor(84, 139, 84, 255);
	public static final Cor VERDE_ESCURO = new Cor(124, 205, 124, 255);
	public static final Cor VERDE_CLARO = new Cor(144,238, 144, 255);
	public static final Cor VERDE_COLOR4 = new Cor(154, 255, 154, 255);

	public static final Cor VERMELHO_MAIS_ESCURO = new Cor(139, 37, 0, 255);
	public static final Cor VERMELHO_ESCURO = new Cor(205, 55, 0, 255);
	public static final Cor VERMELHO_CLARO = new Cor(238, 64, 0, 255);
	public static final Cor VERMELHO_COLOR4 = new Cor(255, 69, 0, 255);

	public static final Cor BRANCO_MAIS_ESCURO = new Cor(139, 121, 94, 255);
	public static final Cor BRANCO_ESCURO = new Cor(205, 179, 139, 255);
	public static final Cor BRANCO_CLARO = new Cor(238, 207, 161, 255);
	public static final Cor BRANCO_COLOR4 = new Cor(255, 222, 173, 255);

	public static final Cor ROSA_MAIS_ESCURO = new Cor(139, 105, 105, 255);
	public static final Cor ROSA_ESCURO = new Cor(205, 155, 155, 255);
	public static final Cor ROSA_CLARO = new Cor(238, 180, 180, 255);
	public static final Cor ROSA_COLOR4 = new Cor(255, 193, 193, 255);

	public static final Cor COLOR5 = new Cor(255, 255, 200, 255);
	public static final Cor COLOR6 = new Cor(230,230,230, 255);
	public static final Cor LARANJA = new Cor(255, 127, 36, 255);

	public static final HashMap<Integer, Cor[]> MAP_CORES = new HashMap<Integer, Cor[]>();

	static {
		MAP_CORES.put(1, new Cor[]{MARROM_MAIS_ESCURO, MARROM_ESCURO, MARROM_CLARO, MARROM_COLOR4 });
		MAP_CORES.put(2, new Cor[]{AZUL_MAIS_ESCURO, AZUL_ESCURO, AZUL_CLARO, AZUL_COLOR4 });
		MAP_CORES.put(3, new Cor[]{VERDE_MAIS_ESCURO, VERDE_ESCURO, VERDE_CLARO, VERDE_COLOR4 });
		MAP_CORES.put(4, new Cor[]{BRANCO_MAIS_ESCURO, BRANCO_ESCURO, BRANCO_CLARO, BRANCO_COLOR4 });
		MAP_CORES.put(5, new Cor[]{ROSA_MAIS_ESCURO, ROSA_ESCURO, ROSA_CLARO, ROSA_COLOR4 });
		MAP_CORES.put(6, new Cor[]{ROSO_MAIS_ESCURO, ROSO_ESCURO, ROSO_CLARO, ROSO_COLOR4 });
		MAP_CORES.put(7, new Cor[]{VERMELHO_MAIS_ESCURO, VERMELHO_ESCURO, VERMELHO_CLARO, VERMELHO_COLOR4 });
		MAP_CORES.put(8, new Cor[]{MARROM_MAIS_ESCURO, MARROM_ESCURO, MARROM_CLARO, MARROM_COLOR4 });
		MAP_CORES.put(9, new Cor[]{AZUL_MAIS_ESCURO, AZUL_ESCURO, AZUL_CLARO, AZUL_COLOR4 });
		MAP_CORES.put(10, new Cor[]{VERDE_MAIS_ESCURO, VERDE_ESCURO, VERDE_CLARO, VERDE_COLOR4 });
		MAP_CORES.put(11, new Cor[]{BRANCO_MAIS_ESCURO, BRANCO_ESCURO, BRANCO_CLARO, BRANCO_COLOR4 });
		MAP_CORES.put(12, new Cor[]{ROSA_MAIS_ESCURO, ROSA_ESCURO, ROSA_CLARO, ROSA_COLOR4 });
	}

	/** Vermelho **/
	public int r = 0;
	/** Verde **/
	public int g = 0;
	/** Azul **/
	public int b = 0;
	/** Alfa **/
	public int a = 255;

	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 */
	public Cor(int r, int g, int b, int a) {
		super();

		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	/**
	 * Configurar cor
	 * 
	 * @param paint
	 */
	public void setCor(Paint paint) {
		paint.setARGB(a, r, g, b);
	}
}