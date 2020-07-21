package arquivos.br.listas;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;

import org.junit.Ignore;
import org.junit.Test;

public class ArquivosTest {

	@Test
	@Ignore
	public void test() {
//		Arquivos arquivos = new Arquivos();
//		List<String> lista = arquivos.preenchelista();
//		
//		for (int i = 0; i < lista.size(); i++) {
//			
//			System.out.println(lista.get(i).toString());
//			
//		}

	}

	@Test
	public void teste2() {
		
		Arquivos arquivos = new Arquivos();
		
		//abre caixa dialogo escolha diretorio - filtrando apenas pastas
		final JFileChooser janelaEscolherDiretorio = new JFileChooser();
		janelaEscolherDiretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		janelaEscolherDiretorio.showOpenDialog(null);
		
		String diretorio = janelaEscolherDiretorio.getSelectedFile().getAbsolutePath();
		
		
		FileFilter filter = new FileFilter() {
		    public boolean accept(File file) {
		        return file.getName().endsWith(".pdf");
		    }
		};

		File dir = new File(diretorio);
		File[] files = dir.listFiles(filter);
		
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].getName());
		}

	}

}
