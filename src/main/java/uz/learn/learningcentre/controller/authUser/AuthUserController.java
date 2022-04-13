package uz.learn.learningcentre.controller.authUser;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import uz.learn.learningcentre.controller.base.AbstractController;
import uz.learn.learningcentre.controller.base.GenericController;
import uz.learn.learningcentre.controller.base.GenericCrudController;
import uz.learn.learningcentre.criteria.authUser.AuthUserCriteria;
import uz.learn.learningcentre.dto.auth.*;
import uz.learn.learningcentre.response.DataDto;
import uz.learn.learningcentre.response.ResponseEntity;
import uz.learn.learningcentre.service.AuthService;
import uz.learn.learningcentre.service.authUser.AuthUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping(value = "/auth")
public class AuthUserController extends AbstractController<AuthUserService>
        implements GenericCrudController<AuthUserCreateDto, AuthUserUpdateDto>,
        GenericController<AuthUserDto, AuthUserCriteria> {

    private final AuthService authService;

    public AuthUserController(AuthUserService service, AuthService authService) {
        super(service);
        this.authService = authService;
    }


    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> getToken(@RequestBody LoginDto dto) {
        return authService.getToken(dto);
    }

    @SneakyThrows
    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<DataDto<SessionDto>> getToken(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(new DataDto<>(authService.getRefreshToken(request, response).getData().getData()));
    }


    @Override
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody AuthUserCreateDto authUserCreateDto) {
        return service.create(authUserCreateDto);
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody AuthUserUpdateDto authUserUpdateDto) {
        return service.update(authUserUpdateDto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<DataDto<AuthUserDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<DataDto<List<AuthUserDto>>> getAll(AuthUserCriteria authUserCriteria) {
        return service.getAll(authUserCriteria);
    }
}
