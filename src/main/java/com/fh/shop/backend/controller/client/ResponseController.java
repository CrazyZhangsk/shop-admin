package com.fh.shop.backend.controller.client;

import com.fh.shop.backend.biz.client.IClientService;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.client.ClientPO;
import com.fh.shop.utils.TransFormUtils;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/response/client")
public class ResponseController {

    @Resource(name="clientService")
    private IClientService clientService;

    @RequestMapping("updateDetailInfo")
    public ModelAndView updateDetailInfo(Integer id) {
        ModelAndView modelAndView = new ModelAndView("/client/update");
        ClientPO client = clientService.findClientById(id);
        modelAndView.addObject("clientInfo",client);
        return modelAndView;
    }
}
