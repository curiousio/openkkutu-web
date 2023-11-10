package com.glowat.openkkutuweb.help;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelpController {
  private final KKuTuSetting kkuTuSetting;
  private final HelpService helpService;

  @GetMapping("/help")
  public String help(Model model) {
    model.addAttribute("koInjeongThemes", kKuTuSetting.getKoInjeongThemes());
    model.addAttribute("levels", helpService.getLevels());
    return "help";
  }

}
