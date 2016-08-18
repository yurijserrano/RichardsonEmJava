package org.mack.an2.ativ1;

/**
 * @author Yuri Serrano
 *
 */

public abstract class Richardson {
	/**
	 * x0 ponto onde a derivada deve ser estimada
	 * h  passo
	 * n  número de linhas do método
	 */
	double x0, h;
	int n;
	/**
	 * Cria objeto para calcular derivada pela extrapolação de Richardson
	 * @param x0  ponto onde a derivada deve ser calculada
	 * @param h   tamanho do passo (intervalo) da extrapolação
	 * @param n   número de linhas na extrapolação
	 */
	Richardson(double x0, double h, int n) {
		this.x0=x0;
		this.h=h;
		this.n=n;	// if n < 1 throw error?!
	}
	/**
	 * Função cuja derivada será estimada pela extrapolação de Richardson
	 * @param x argumento double
	 * @return retorna double
	 */
	abstract double f(double x);
	/**
	 * Calcula x elevado a i
	 * @param x double
	 * @param i potência inteira
	 * @return x elevado a i
	 */
	private static double expo(double x, int i) {
		double res = 1.0;
		while (i>0) {
			res *= x;
			i--;
		}
		return res;
	}
	/**
	 * Calcula o Método de Extrapolação de Richardson
	 * @return String com as diferentes linhas da extrapolação
	 */
	public String toString() {
		double N[] = new double[(n+1)*n/2];
		String s="Exercício 2\n";
		double hh=h;
		int indice=0;
		for (int i=0; i<n; i++) {
			N[indice]=(f(x0+hh)-f(x0-hh))/(2*hh);
			s += "N1(" + hh + ")=" + N[indice++];
			for (int j=1; j<i+1; j++) {
				s += "\t";
//				System.out.println("N["+i+"]["+(j-1)+"]="+N[i][j-1]);
//				System.out.println("expo="+(expo(4.0,j)-1));
		// expo(4.0,j) e nao expo(4.0,j-1) porque j já está deslocado de 1
				N[indice]=N[indice-1]+(N[indice-1]-N[indice-i-1])/(expo(4.0,j)-1.0);
				s += "N" + (j+1) + "(" + (expo(2.0,j)*hh) + ")=" + N[indice++];
			}
			s += "\n";
			hh /= 2.0;	// para a próxima iteração (linha)
		}
		return s;
	}
}
