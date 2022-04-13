package uz.learn.learningcentre.controller.base;

import lombok.RequiredArgsConstructor;
import uz.learn.learningcentre.service.base.BaseService;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> implements BaseController {

    protected final S service;

}
