package com.fh.shop.backend.controller.client;

import com.fh.shop.backend.biz.client.IClientService;
import com.fh.shop.backend.common.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Resource(name = "clientService")
    private IClientService clientService;

    @RequestMapping("findClientList")
    public ServerResponse findClientList(@RequestParam Map requestMap){
        ServerResponse serverResponse = clientService.findClientList(requestMap);
        return serverResponse;
    }

    @RequestMapping("updatePassword")
    public ServerResponse updatePassword(@RequestParam Map requestMap){
        ServerResponse serverResponse = clientService.updatePassword(requestMap);
        return serverResponse;
    }

    @RequestMapping("updateClientName")
    public ServerResponse updateClientName(@RequestParam Map requestMap) {
        return clientService.updateClientName(requestMap);
    }

    @RequestMapping("updateClientEmail")
    public ServerResponse updateClientEmail(@RequestParam Map requestMap) {
        return clientService.updateClientEmail(requestMap);
    }

    @RequestMapping("updateClientArea")
    public ServerResponse updateClientArea(@RequestParam Map requestMap) {
        return clientService.updateClientArea(requestMap);
    }

}
