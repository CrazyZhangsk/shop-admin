package com.fh.shop.backend.biz.client;

import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.mapper.client.IClientMapper;
import com.fh.shop.backend.po.client.ClientPO;
import com.fh.shop.utils.HttpClientUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("clientService")
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientMapper clientMapper;

   @Value("${client.find}")
   private String client_find;

   @Value("${client.updatePassword}")
   private String client_updatePassword;

    @Value("${client.updateClientName}")
    private String client_updateClientName;

    @Override
    public ServerResponse findClientList(Map params) {
        String result = HttpClientUtils.sentPost(client_find, null, params);
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(result, ServerResponse.class);
        return serverResponse;
    }

    @Override
    public ClientPO findClientById(Integer id) {
        ClientPO clientPO = clientMapper.findClientById(id);
        return clientPO;
    }

    @Override
    public ServerResponse updatePassword(Map params) {
        String result = HttpClientUtils.sentPost(client_updatePassword, null, params);
        Gson gson = new Gson();
        ServerResponse serverResponse = gson.fromJson(result, ServerResponse.class);
        return serverResponse;
    }

    @Override
    public ServerResponse updateClientName(Map requestMap) {
        String result = HttpClientUtils.sentPost(client_updateClientName, null, requestMap);
        Gson gson = new Gson();
        return gson.fromJson(result, ServerResponse.class);
    }

    @Value("${client.updateEmail}")
    private String client_updateEmail;
    @Override
    public ServerResponse updateClientEmail(Map requestMap) {
        String result = HttpClientUtils.sentPost(client_updateEmail, null, requestMap);
        Gson gson = new Gson();
        return gson.fromJson(result, ServerResponse.class);
    }

    @Value("${client.updateArea}")
    private String client_updateArea;
    @Override
    public ServerResponse updateClientArea(Map requestMap) {
        String result = HttpClientUtils.sentPost(client_updateArea, null, requestMap);
        Gson gson = new Gson();
        return gson.fromJson(result, ServerResponse.class);
    }
}
