package my.exam.catalog.apiserver.libapiserver.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.AuthorDTO;
import my.exam.catalog.apiserver.libapiserver.mapper.BookMapper;
import my.exam.catalog.apiserver.libapiserver.service.AuthorService;
import my.exam.catalog.apiserver.libapiserver.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/author", consumes = MediaType.ALL_VALUE)
@RequiredArgsConstructor

public class AuthorController {

    @Autowired
    private CatalogService catalogService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookMapper bookMapper;

    @PostMapping()
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO dto){
        Optional<AuthorDTO> optionalDto = authorService.create(dto);
        return optionalDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id){
        return authorService.read(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
        Optional<List<AuthorDTO>> optionalAuthors = authorService.findAll();
        return optionalAuthors.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
