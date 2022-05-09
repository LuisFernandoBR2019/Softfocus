package br.com.luis.softfocus.jasper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ManipulaArquivos {

	public List<Byte> fileRead(String diretorio) {
		List<Byte> listaByte = new ArrayList<>();

		try {
			File f = new File(diretorio);

			byte[] buf = new byte[8192];

			InputStream is = new FileInputStream(f);

			int c = 0;

			while (is.read(buf, 0, buf.length) > 0) {
				for (int i = 0; i < buf.length; i++) {
					listaByte.add(buf[i]);

				}
			}

			System.out.println("stop");
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaByte;

	}
}
