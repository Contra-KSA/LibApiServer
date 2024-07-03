package my.exam.catalog.apiserver.libapiserver.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import my.exam.catalog.apiserver.libapiserver.dto.UserDTO;
import my.exam.catalog.apiserver.libapiserver.entity.UserEntity;
import my.exam.catalog.apiserver.libapiserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users", consumes = MediaType.ALL_VALUE)
@RequiredArgsConstructor
public class UserController {

    UserService service;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/myname")
    public ResponseEntity<String> getName() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        Optional<UserDTO> optionalUser = service.create(dto);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/get/all")
//    public ResponseEntity<List<User>> getAllUser() {
//        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
//    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll() {
        Optional<List<UserDTO>> optionalUsers = service.findAll();
        return optionalUsers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @GetMapping("/get/{id}")
//    public ResponseEnt ity<User> getUser(@PathVariable("id") Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id).orElseThrow(() ->
//                new UsernameNotFoundException("User with id = " + id + " not found!")));
//    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        Optional<UserDTO> optionalUser = service.findById(id);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
        Optional<UserDTO> optionalUser = service.findById(id);
        if (optionalUser.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto, @PathVariable Long id) {
        Optional<UserDTO> optionalUser = service.update(dto, id);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
