package br.com.luis.softfocus.jasper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.luis.softfocus.model.ComunicacaoPerda;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioJasperServicos {

	@Autowired
	private ManipulaArquivos manipularArquivos;

	private String Base_Path = "";

	private String createDirectoryRelatorios() {
		File backupFilePath = new File(System.getProperty("user.home") + File.separator + "documents" + File.separator
				+ "ProAgro" + File.separator + "Relatórios");

		if (!backupFilePath.exists()) {
			File dir = backupFilePath;
			dir.mkdirs();
		}

		return backupFilePath.getAbsolutePath();
	}

	public String generateAndGetPdfFilePath(List<ComunicacaoPerda> listaComunicacaoPerda) {

		try {
			Base_Path = createDirectoryRelatorios();

			File file = ResourceUtils.getFile("classpath:static/relatorios/relatorio.jrxml");

			List<ComunicacaoPerda> comunicacaoPerdaLista = listaComunicacaoPerda;

			if (comunicacaoPerdaLista == null || comunicacaoPerdaLista.size() == 0) {
				return "Não possui Comunicacao de Perda";
			}

			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(comunicacaoPerdaLista);

			Map<String, Object> parameters = new HashMap<String, Object>();

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			String filePath = generateFilePath();

			JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);

			return filePath;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}

	}

	private String generateFilePath() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");

		Date date = new Date(System.currentTimeMillis());

		String filename = "\\" + formatter.format(date) + ".pdf";

		return Base_Path + filename;
	}
}
