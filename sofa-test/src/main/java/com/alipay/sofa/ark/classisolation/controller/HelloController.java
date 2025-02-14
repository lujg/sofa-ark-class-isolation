/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.sofa.ark.classisolation.controller;

import com.alipay.sofa.demo.hessian3.Hessian3Service;
import com.alipay.sofa.demo.hessian4.Hessian4Service;
import com.alipay.sofa.demo.pojo.SamplePoJo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author qilong.zql
 */
@RestController
public class HelloController {

    @RequestMapping("/hello-hessian3")
    public String hessian3() throws IOException {
      Hessian3Service hessian3Service = new Hessian3Service();
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
      Hessian4Service hessian4Service = new Hessian4Service();
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