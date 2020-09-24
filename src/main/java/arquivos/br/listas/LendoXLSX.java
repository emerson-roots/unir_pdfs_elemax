/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos.br.listas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Br
 * 
 *         necess�rio bibliotecas inclusas no projeto
 * 
 */
public class LendoXLSX {

//	/**
//	 * @param args the command line arguments
//	 */
//	public static void main(String[] args) {
//
//		// leituraLinhasExcel();
//		//leituraColunasExcel("D:\\pasta\\planilha.xlsm");
//
//	}

	public static List<List<String>> leituraColunasExcel(String pDiretorioArquivoElemax) {

		// variavel que dara o retorno ao metodo
		List<List<String>> dados = new ArrayList<>();
		FileInputStream fisPlanilha = null;
		String diretorioPlanilhaElemax = pDiretorioArquivoElemax;

		try {
			File file = new File(diretorioPlanilhaElemax);
			fisPlanilha = new FileInputStream(file);

			// cria um workbook = planilha toda com todas as abas
			XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

			/**
			 * getSheetAt - retorna uma planilha de acordo com o index da aba getSheet -
			 * retorna uma planilha de acordo com o nome na aba
			 */
			XSSFSheet sheet = workbook.getSheet("Cadastro de Clientes");

			// retorna todas as linhas da planilha 0 (aba 1)
			Iterator<Row> rowIterator = sheet.iterator();

			long qtdLinhas = sheet.getLastRowNum();

			System.out.println("==== Qtd de linhas preenchidas na planilha Cadastro Clientes Excel: " + qtdLinhas);

			List<String> nomeCliente = new ArrayList<String>();
			List<String> cpfOuCnpj = new ArrayList<String>();
			List<String> adm = new ArrayList<String>();

			// varre todas as linhas da planilha 0
			while (rowIterator.hasNext()) {

				// recebe cada linha da planilha
				Row row = rowIterator.next();

				// pegamos todas as celulas desta linha
				Iterator<Cell> cellIterator = row.iterator();

				// varremos todas as celulas da linha atual
				while (cellIterator.hasNext()) {

					// criamos uma celula
					Cell cell = cellIterator.next();

					// percorre as colunas
					switch (cell.getColumnIndex()) {
					case 1:// Cell.CELL_TYPE_STRING:
						nomeCliente.add(cell.toString());
						break;

					case 2:// Cell.CELL_TYPE_NUMERIC:
						cpfOuCnpj.add(cell.toString());
						break;
					case 3:// Cell.CELL_TYPE_FORMULA:
						adm.add(cell.toString());
						break;
					}

				}
			}

			// recebe 3 das 4 listas criadas acima
			dados = Arrays.asList(nomeCliente, cpfOuCnpj, adm);

		} catch (FileNotFoundException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				fisPlanilha.close();
			} catch (IOException ex) {
				Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return dados;

	}

	public static void leituraLinhasExcel() {

		FileInputStream fisPlanilha = null;

		try {
			File file = new File("D:\\teste.xlsx");
			fisPlanilha = new FileInputStream(file);

			// cria um workbook = planilha toda com todas as abas
			XSSFWorkbook workbook = new XSSFWorkbook(fisPlanilha);

			/**
			 * getSheetAt - retorna uma planilha de acordo com o index da aba getSheet -
			 * retorna uma planilha de acordo com o nome na aba
			 */
			XSSFSheet sheet = workbook.getSheet("Cadastro de Clientes");

			// retorna todas as linhas da planilha 0 (aba 1)
			Iterator<Row> rowIterator = sheet.iterator();

			// varre todas as linhas da planilha 0
			while (rowIterator.hasNext()) {

				// recebe cada linha da planilha
				Row row = rowIterator.next();

				// pegamos todas as celulas desta linha
				Iterator<Cell> cellIterator = row.iterator();

				// varremos todas as celulas da linha atual
				while (cellIterator.hasNext()) {

					// criamos uma celula
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:
						System.out.println("TIPO STRING: " + cell.getStringCellValue());
						break;

					case Cell.CELL_TYPE_NUMERIC:
						System.out.println("TIPO NUMERICO: " + cell.getNumericCellValue());
						break;

					case Cell.CELL_TYPE_FORMULA:
						System.out.println("TIPO FORMULA: " + cell.getCellFormula());
					}

				}
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				fisPlanilha.close();
			} catch (IOException ex) {
				Logger.getLogger(LendoXLSX.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
