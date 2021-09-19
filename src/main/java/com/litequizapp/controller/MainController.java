package com.litequizapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

  @GetMapping(value = {"", "/", "/index"})
  public String getMainIndex(){
    return "index/index-main";
  }

  @GetMapping(value = {"/admin", "/admin/", "/admin/index"})
  public String getAdminIndex(){
    return "index/index-admin";
  }

}
