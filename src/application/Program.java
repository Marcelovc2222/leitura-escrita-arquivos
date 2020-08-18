package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Produto;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Produto> lista = new ArrayList<Produto>();

		System.out.print("Digite o caminha: ");
		String diretorio = sc.nextLine();

		File arquivo = new File(diretorio);
		String caminho = arquivo.getParent();

		boolean criadoSubPasta = new File(caminho + "\\out").mkdir();

		System.out.println(criadoSubPasta);

		String criandoArquivo = caminho + "\\out\\sumario.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(diretorio))) {

			String itemCsv = br.readLine();

			while (itemCsv != null) {

				String[] campo = itemCsv.split(",");
				String nome = campo[0];
				Double preco = Double.parseDouble(campo[1]);
				Integer quantidade = Integer.parseInt(campo[2]);
				Produto produto = new Produto(nome, preco, quantidade);
				lista.add(produto);
				itemCsv = br.readLine();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(criandoArquivo))) {
				for (Produto produto : lista) {
					bw.write(String.valueOf(produto)); //possivol falha no valueOf
					bw.newLine();
				}
				System.out.println(criandoArquivo + " Criado com sucesso");

			} catch (IOException e) {
				System.out.println("Erro na escrita do arquivo: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo: " + e.getMessage());
		}

		sc.close();

	}

}
