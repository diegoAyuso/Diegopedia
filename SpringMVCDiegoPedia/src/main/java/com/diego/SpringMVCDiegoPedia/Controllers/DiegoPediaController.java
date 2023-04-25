package com.diego.SpringMVCDiegoPedia.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiegoPediaController {

	@GetMapping("/diegopedia")
	public String goToDiegoPedia(Model model) {
		return "diegopedia";
	}
}