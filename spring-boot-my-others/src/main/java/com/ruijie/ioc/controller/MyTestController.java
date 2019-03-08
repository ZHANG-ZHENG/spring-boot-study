package com.ruijie.ioc.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ruijie.ioc.bean.MyTest;
import com.ruijie.ioc.bean.Test;
import com.ruijie.ioc.service.TestService;





@RestController
@RequestMapping("/myTest")
public class MyTestController extends BaseController<MyTest>{





}
