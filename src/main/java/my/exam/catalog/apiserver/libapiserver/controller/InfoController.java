package my.exam.catalog.apiserver.libapiserver.controller;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/api", consumes = MediaType.ALL_VALUE)
public class InfoController {

    @GetMapping(value = "/info")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/info.jsp");
        modelAndView.addObject("title", "Welcome to IT-Library!");
        return modelAndView;
    }
}