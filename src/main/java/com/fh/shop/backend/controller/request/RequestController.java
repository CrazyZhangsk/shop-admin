package com.fh.shop.backend.controller.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class RequestController {

    @RequestMapping("toClientList")
    public String toAreaList() {
        return "client/memberList";
    }
}
