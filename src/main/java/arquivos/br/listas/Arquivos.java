package arquivos.br.listas;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Arquivos {

//	public static void main(String[] args) {
//
//		String diretorio = selecionarDiretorio("Selecionar pasta", "Escolha uma pasta", true);
//
//		try {
//			// lista arquivos gerais
//			// listarArquivosOuPastas();
//
//			// lista arquivos de acordo com a extensao passada
//			// listaArquivosFiltradosPelaExtensao(diretorio,".pdf");
//
//			// lista arquivo de acordo com o trexo do nome descrito e a extensao
//			listaArquivosFiltradosPeloNomeComExtensao("digite aqui parte do nome ou nome inteiro do arquivo que busca",
//					diretorio, ".pdf");
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "VOCE NAO SELECIONOU UM DIRETORIO", "DIRETORIO INCORRETO",
//					JOptionPane.ERROR_MESSAGE);
//		}
//
//	}

	public static File[] listaArquivosFiltradosPelaExtensao(String pDiretorio, final String pExtensaoArquivo) {

		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {

				if (!file.exists()) {

					JOptionPane.showMessageDialog(null, "Arquivo não encontrado!!!", "LISTAR ARQUIVOS FILTRADOS",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}

				return file.getName().endsWith(pExtensaoArquivo); // .pdf ~ .txt ~ .zip ~ etc
			}
		};

		File dir = new File(pDiretorio);
		File[] arquivos = dir.listFiles(filter);

		for (int i = 0; i < arquivos.length; i++) {
			System.out.println(arquivos[i].getName());
		}

		return arquivos;

	}

	public static File[] listaArquivosFiltradosPeloNomeComExtensao(String pParteDoTextoContigoNoNomeDoArquivo,
			String pDiretorio, String pExtensaoArquivo) {

		// ==========================================
		// CUIDADO - respeitar case sensitive no parametro
		// pParteDoTextoContigoNoNomeDoArquivo
		// ==========================================
		FilenameFilter filtroNomeArquivo = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {

				if (!dir.exists()) {

					JOptionPane.showMessageDialog(null, "Arquivo não encontrado!!!", "LISTAR ARQUIVOS FILTRADOS",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}

				// retorna arquivos encontrados por parte do nome + extensao
				return name.contains(pParteDoTextoContigoNoNomeDoArquivo)
						&& name.endsWith(pExtensaoArquivo.toLowerCase());
			}
		};

		// arquivos filtrados por parte do nome + extensao
		File dir = new File(pDiretorio);
		File[] arquivos = dir.listFiles(filtroNomeArquivo);

		for (int i = 0; i < arquivos.length; i++) {
			System.out.println(arquivos[i].getName());
		}

		return arquivos;

	}

	public static List<String> listarArquivosOuPastas(String pDiretorioCaixaDialogo) {

		List<String> lista = new ArrayList<String>();
		String[] nomes;

		File diretorio = new File(pDiretorioCaixaDialogo);
		nomes = diretorio.list(); // lista os arquivos
		lista = Arrays.asList(nomes); // passo de Array para List
		Collections.sort(lista); // ordeno a lista

		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).toString());
		}

		return lista;
	}

	public static String selecionarDiretorio(String pTextoBotaoAceitar, String pTituloCaixaDialogo,
			boolean pDiretoriosOuArquivos) {

		String diretorio = "";
		// abre caixa dialogo escolha diretorio - filtrando apenas pastas
		final JFileChooser janelaEscolherDiretorio = new JFileChooser();
		janelaEscolherDiretorio.setApproveButtonText(pTextoBotaoAceitar);
		janelaEscolherDiretorio.setDialogTitle(pTituloCaixaDialogo);
		
		if (pDiretoriosOuArquivos) {
			janelaEscolherDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		} else {
			janelaEscolherDiretorio.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		
		int i = janelaEscolherDiretorio.showOpenDialog(null);

		// verifica se o caminho existe e se é um diretório
		if (i == 0) {
			
			
			if (janelaEscolherDiretorio.getSelectedFile().exists()) {
				return janelaEscolherDiretorio.getSelectedFile().getAbsolutePath();
			}else {
				JOptionPane.showInternalMessageDialog(null, "O caminho ou arquivo selecionado não existe.","MENSAGEM",JOptionPane.ERROR_MESSAGE);
			}
		}

		return diretorio;
	}

}
