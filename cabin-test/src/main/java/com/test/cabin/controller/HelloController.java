package com.test.cabin.controller; /**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */

import com.test.cabin.demo.hessian3.Hessian3Service;
import com.test.cabin.demo.hessian4.Hessian4Service;
import com.test.cabin.demo.pojo.SamplePoJo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author qilong.zql
 */
@RestController
public class HelloController {
  @Autowired
  private Hessian3Service hessian3Service;

  @Autowired
  private Hessian4Service hessian4Service;

  @RequestMapping("/hello-hessian3")
  public String hessian3() throws IOException {
    ClassLoader classLoader = SamplePoJo.class.getClassLoader();
    ClassLoader hessianClassLoader = Hessian3Service.class.getClassLoader();
    ClassLoader parentClassLoader = hessianClassLoader.getParent();
    SamplePoJo samplePoJo = new SamplePoJo("Hello, hessian3 with class loader " + classLoader + " " +
        hessianClassLoader + " parent " + parentClassLoader);
    byte[] bytes = hessian3Service.serialize(samplePoJo);
    Object pojo = hessian3Service.deserialize(bytes);
    return pojo.toString();
  }

  @RequestMapping("/hello-hessian4")
  public String hessian4() throws IOException {
    ClassLoader classLoader = SamplePoJo.class.getClassLoader();
    ClassLoader hessianClassLoader = Hessian4Service.class.getClassLoader();
    ClassLoader parentClassLoader = hessianClassLoader.getParent();
    SamplePoJo samplePoJo = new SamplePoJo("Hello, hessian4 with class loader " + classLoader + " " +
        hessianClassLoader + " parent " + parentClassLoader);
    byte[] bytes = hessian4Service.serialize(samplePoJo);
    Object pojo = hessian4Service.deserialize(bytes);
    return pojo.toString();
  }
}