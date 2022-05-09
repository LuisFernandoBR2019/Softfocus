package br.com.luis.softfocus.controllerweb;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.luis.softfocus.model.ComunicacaoPerda;

@Controller
public class ComunicacaoPerdaControllerWeb {

	@RequestMapping("/")
	public String init(Model model) {

		model.addAttribute("lista", new ArrayList<ComunicacaoPerda>());
		model.addAttribute("title_page", "Comunicação Perda");
		model.addAttribute("comunicacaoPerda", new ComunicacaoPerda());
		return "index";
	}
}
