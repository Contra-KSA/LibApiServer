package my.exam.catalog.apiserver.libapiserver.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.AuthorDTO;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.entity.BookEntity;
import my.exam.catalog.apiserver.libapiserver.mapper.BookMapper;
import my.exam.catalog.apiserver.libapiserver.service.AuthorService;
import my.exam.catalog.apiserver.libapiserver.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.ALL_VALUE)
@RequiredArgsConstructor
public class CatalogApiController {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> getById( @PathVariable Long id){
        Optional<BookDTO> dto = catalogService.read(id);
        return dto.map(bookEntity -> ResponseEntity.ok(dto.get()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/info")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/info.jsp");
        modelAndView.addObject("title", "Welcome to IT-Library!");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDto)throws IOException {
        Optional<BookDTO> dto = Optional.ofNullable(catalogService.create(bookDto));
        return dto.map(bookEntity -> ResponseEntity.ok(dto.get()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll(){
        Optional<List<BookDTO>> dtos = Optional.ofNullable(catalogService.getAll());
        return dtos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchByTitleContaining(
            @RequestParam(value = "title", required = false ) String title,
            @RequestParam(value = "year", required = false ) Integer year
    ){
        Optional<List<BookDTO>> dtosByTitle = catalogService.findByTitleContaining(title);
        Optional<List<BookDTO>> dtos = catalogService.findByYear(year);
        if(dtosByTitle.isPresent()){
            List<BookDTO> dtoList= dtosByTitle.get();
            dtoList.addAll(dtos.get());
            return ResponseEntity.ok(dtoList);
        }else{
            return dtos.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        }
    }

    @PostMapping("/author")
        public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO dto){
        Optional<AuthorDTO> optionalDto = authorService.create(dto);
        return optionalDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id){
        return authorService.read(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

}