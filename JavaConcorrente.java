/*Escreva um programa em Java que realize o cálculo das somas dos
valores de uma matriz 4x4 de números inteiros e imprima o resultado
destas somas na tela. Faça com que o cálculo do somatório de cada
linha seja realizado emparalelo, porthreads;*/

import java.util.Scanner;
import java.security.SecureRandom;

class JavaConcorrente{
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		final int linha = 4;
		final int coluna = 4;
		int[][] matriz = new int[linha][coluna];
		
		// inserção dos elementos randomicos na matriz
		for(int l=0 ; l < linha ; l++) {
			for(int c=0 ; c < coluna ; c++) {
				matriz[l][c] = new SecureRandom().nextInt(100);
			}
		}
		
		// percorrer os elementos da matriz
		for(int l=0 ; l < linha ; l++) {
			for(int c=0 ; c < coluna ; c++) {
				System.out.printf("%d - ", matriz[l][c]);
			}
			System.out.println("\n");
		}
		
		int somaMatriz = 0;
		
		// soma dos elementos da matriz
		for(int l=0 ; l < linha ; l++) {
			for(int c=0 ; c < coluna ; c++) {
				somaMatriz += matriz[l][c];
			}
		}
		
		System.out.println(somaMatriz);
	}
}
