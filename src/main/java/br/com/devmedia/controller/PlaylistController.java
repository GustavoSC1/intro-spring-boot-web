package br.com.devmedia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devmedia.domain.Playlist;
import br.com.devmedia.service.PlaylistService;

@Controller
@RequestMapping(value="/playlists")
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;
	
	@GetMapping("/listar")
	public ModelAndView listar(ModelMap model) {
		model.addAttribute("playlists", playlistService.recuperar());
		return new ModelAndView("/playlist/list", model);
	}
	
	@GetMapping("/cadastro")
	public String preSalvar(@ModelAttribute("playlist") Playlist playlist) {
		return "/playlist/add";
	}
	
	@PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attr) {
        System.out.println("POST");
		if (result.hasErrors()) {
            return "/playlist/add";
        }
		
		if (playlist.getId() != 0) {
			playlistService.atualizar(playlist);
	        attr.addFlashAttribute("mensagem", "Playlist atualizada com sucesso.");
	        return "redirect:/playlists/listar";
		}

        playlistService.salvar(playlist);
        attr.addFlashAttribute("mensagem", "Playlist criada com sucesso.");
        return "redirect:/playlists/listar";
    }
	
	@GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
        Playlist playlist = playlistService.recuperarPorId(id);
        model.addAttribute("playlist", playlist);
        return new ModelAndView("/playlist/add", model);
    }
	
	@GetMapping("/{id}/remover")
	public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
		playlistService.excluir(id);
		attr.addFlashAttribute("mensagem", "Playlist excluida com sucesso.");
		return "redirect:/playlists/listar";
	}
	
}
