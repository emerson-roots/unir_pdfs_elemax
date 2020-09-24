package arquivos.br.listas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class MergePDF {

//	public static void main(String[] args) {
//	    try {
//	    	//lista de pdfs
//	        List<InputStream> pdfs = new ArrayList<InputStream>();
//	        
//	        //adiciona pdfs de um diretorio a lista
//	        pdfs.add(new FileInputStream("D:/java/Eclipse/PROJETOS ECLIPSE/itext/pdf1.pdf"));
//	        pdfs.add(new FileInputStream("D:/java/Eclipse/PROJETOS ECLIPSE/itext/pdf2.pdf"));
//	        pdfs.add(new FileInputStream("D:/java/Eclipse/PROJETOS ECLIPSE/itext/pdf3.pdf"));
//	        pdfs.add(new FileInputStream("D:/java/Eclipse/PROJETOS ECLIPSE/itext/pdf4.pdf"));
//	        pdfs.add(new FileInputStream("D:/java/Eclipse/PROJETOS ECLIPSE/itext/pdf5.pdf"));
//	        pdfs.add(new FileInputStream("D:/java/Eclipse/PROJETOS ECLIPSE/itext/pdf6.pdf"));
//	        pdfs.add(new FileInputStream("D:/java/Eclipse/PROJETOS ECLIPSE/itext/pdf7.pdf"));
//	        
//	        //seta um nome ao arquivo que sera unificado
//	        String nomeArquivoUnificado = "D:/java/Eclipse/PROJETOS ECLIPSE/itext/DEU CERTO.pdf";
//	        
//	        //efetua a uniao dos pdfs de acordo com o nome e insere paginação no arquivo final
//	        concatPDFs(pdfs, nomeArquivoUnificado, true);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//
//	}

	public static void pdfs(String pDiretorioArquivosParaUnificar, String pDiretorioSalvarArquivosUnificados,
			String pDiretorioPlanilhaElemax) {

		String diretorio = pDiretorioArquivosParaUnificar;
		String diretorioSalvarArquivos = pDiretorioSalvarArquivosUnificados + "/";

		List<List<String>> dadosExcel = LendoXLSX.leituraColunasExcel(pDiretorioPlanilhaElemax);
		List<String> nomesClientes = dadosExcel.get(0);
		List<String> cpfCnpj = dadosExcel.get(1);
		List<String> adms = dadosExcel.get(2);

		for (int i = 1; i < cpfCnpj.size(); i++) {

			// formata strings para trabalhar com nome do arquivo
			cpfCnpj.set(i, cpfCnpj.get(i).replace("/", "").replace("-", "").replace(".", ""));
			nomesClientes.set(i, nomesClientes.get(i).replace("/", ""));
			adms.set(i, adms.get(i).replace("/", ""));

			// armazena a lista de arquivos encontrados de acordo com o CNPJ
			File[] arquivosEncontrados = Arquivos.listaArquivosFiltradosPeloNomeComExtensao(cpfCnpj.get(i), diretorio,
					".pdf");

			List<InputStream> pdfs = new ArrayList<InputStream>();

			try {

				// adiciona arquivos encontrados ao pdf
				for (int j = 0; j < arquivosEncontrados.length; j++) {
					pdfs.add(new FileInputStream(diretorio + "/" + arquivosEncontrados[j].getName()));
				}

				// somente une PDF se a quantidade de arquivos for maior que 0 para unificar
				if (arquivosEncontrados.length > 0) {

					String parteNomeDoArquivo = diretorioSalvarArquivos + adms.get(i) + "_" + nomesClientes.get(i) + "_"
							+ cpfCnpj.get(i);

					concatPDFs(pdfs, parteNomeDoArquivo, false);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void concatPDFs(List<InputStream> streamOfPDFFiles, String pDiretorioComNomeArquivo,
			boolean paginate) {

		OutputStream outputStream = null;
		Document document = new Document();

		try {

			List<InputStream> pdfs = streamOfPDFFiles;
			List<PdfReader> readers = new ArrayList<PdfReader>();
			int totalPages = 0;
			Iterator<InputStream> iteratorPDFs = pdfs.iterator();

			// Create Readers for the pdfs.
			while (iteratorPDFs.hasNext()) {
				InputStream pdf = iteratorPDFs.next();
				PdfReader pdfReader = new PdfReader(pdf);
				readers.add(pdfReader);
				totalPages += pdfReader.getNumberOfPages();
			}

			// gera o arquivo PDF que armazenara todos os arquivos unificados
			outputStream = new FileOutputStream(pDiretorioComNomeArquivo + "_pages_______" + totalPages + ".pdf");

			// Create a writer for the outputstream
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);

			document.open();

			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data

			PdfImportedPage page;
			int currentPageNumber = 0;
			int pageOfCurrentReaderPDF = 0;
			Iterator<PdfReader> iteratorPDFReader = readers.iterator();

			// Loop through the PDF files and add to the output.
			while (iteratorPDFReader.hasNext()) {
				PdfReader pdfReader = iteratorPDFReader.next();

				// Create a new page in the target for each source page.
				while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
					document.newPage();
					pageOfCurrentReaderPDF++;
					currentPageNumber++;

					page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
					cb.addTemplate(page, 0, 0);

					// Code for pagination.
					if (paginate) {
						cb.beginText();
						cb.setFontAndSize(bf, 9);
						cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages,
								520, 5, 0);
						cb.endText();
					}
				}
				pageOfCurrentReaderPDF = 0;
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document.isOpen())
				document.close();
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}
