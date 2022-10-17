package mp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		// Recibimos los parametros
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputLine;
		ArrayList<Integer> resultados = new ArrayList<Integer>();

		
			inputLine = reader.readLine();
			while (inputLine != null) {
				StringTokenizer tk = new StringTokenizer(inputLine);
				int n = Integer.parseInt(tk.nextToken());
				double l = Double.parseDouble(tk.nextToken());
				double w = Double.parseDouble(tk.nextToken())/2;

				ArrayList<Aspersor> aspersores = new ArrayList<>();

				// Recibimos y guardamos los datos de todos los aspersores
				for (int i = 0; i < n; i++) {
					tk = new StringTokenizer(reader.readLine());
					double posicion = Double.parseDouble(tk.nextToken());
					double radio = Double.parseDouble(tk.nextToken());
					Aspersor aspersor = new Aspersor(posicion, radio, l, w);
					if (aspersor.esValido()) {
						aspersores.add(aspersor);
					}
				}
				
				// Ejecutamos el algoritmo de watering grass
				int result = wateringGrass(aspersores, l, w);

				resultados.add(result);
				inputLine = reader.readLine();
			}
			;
		

		// Imprimimos la respuesta
		for (Integer result : resultados) {
			System.out.println(result);
		}
	}

	private static int wateringGrass(ArrayList<Aspersor> aspersores, double l, double w) {
		if(aspersores.size() == 0) {
			return -1;
		}
		
		int result = -1;
		
		// Ordenamos el array de aspersores
		aspersores.sort((s1, s2) -> Double.compare(s1.getAreaEfectivaIzquierda(), s2.getAreaEfectivaIzquierda()));
		
		// Recorremos el array y agregamos los aspersores de forma golosa
		int i = 1;
		Aspersor candidato = aspersores.get(0);
		double limiteIzquierdoActual = 0;
		int contadorAspersoresUsados = 0;
		if(candidato.getAreaEfectivaIzquierda() > 0) {
			return -1;
		}
		
		while(i < aspersores.size() && limiteIzquierdoActual < l) {
			Aspersor aspersorActual = aspersores.get(i);
			if(aspersorActual.areaEfectivaIzquierda <= limiteIzquierdoActual) {	//Vemos si es un aspersor que se puede agregar
				if(candidato.areaEfectivaDerecha < aspersorActual.areaEfectivaDerecha) {
					candidato = aspersorActual;
				}
			} else {
				//Agregamos el aspersor candidato 				
				limiteIzquierdoActual = candidato.areaEfectivaDerecha;
				contadorAspersoresUsados++;
				
				//Vemos si se puede agregar el aspersor ahora que acutalizamos el limite
				if(aspersorActual.areaEfectivaIzquierda <= limiteIzquierdoActual) {	
					if(candidato.areaEfectivaDerecha < aspersorActual.areaEfectivaDerecha) {
						candidato = aspersorActual;
					}
				} else {
					//Como el array está ordenado, si el aspersor actual tiene límite izquierdo mayor 
					//	que el limiteIzquierdoActual, entoncés ninguno de los aspersores siguientes podran agregarse 
					break;
				}
			}
			i++;
		}
		
		if(limiteIzquierdoActual >= l) {
			result = contadorAspersoresUsados;
		} else if(candidato.getAreaEfectivaDerecha() >= l) {
			result = ++contadorAspersoresUsados;
		}

		// Devolvemos resultados
		return result;
	}
	

	public static class Aspersor {
		private double areaEfectivaIzquierda;
		private double areaEfectivaDerecha;

		public Aspersor(double posicion, double radio, double largo, double altura) {
			areaEfectivaIzquierda = calculoAreaEfectivaIzquierda(posicion, radio, largo, altura);
			areaEfectivaDerecha = calculoAreaEfectivaDerecha(posicion, radio, largo, altura);
		}

		public boolean esValido() {
			return areaEfectivaIzquierda < areaEfectivaDerecha;
		}

		private double calculoAreaEfectivaDerecha(double posicion, double radio, double largo, double altura) {
			double result = 0;
			if (radio > 0 && radio > altura) {
				double n = posicion + Math.sqrt(Math.pow(radio, 2) - Math.pow(altura, 2));

				result = (largo > n) ? n : largo;
			}
			return result;
		}

		private double calculoAreaEfectivaIzquierda(double posicion, double radio, double largo, double altura) {
			double result = 0;
			if (radio > 0 && radio > altura) {
				double n = posicion - Math.sqrt(Math.pow(radio, 2) - Math.pow(altura, 2));
				if (result < n) {
					result = n;
				}
			}
			return result;
		}

		public double getAreaEfectivaIzquierda() {
			return areaEfectivaIzquierda;
		}

		public double getAreaEfectivaDerecha() {
			return areaEfectivaDerecha;
		}
	}
}
