package com.fh.shop.backend.biz.client;

import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.client.ClientPO;

import java.util.Map;

public interface IClientService {

    ServerResponse findClientList(Map requestMap);

    ClientPO findClientById(Integer id);

    ServerResponse updatePassword(Map requestMap);

    ServerResponse updateClientName(Map requestMap);

    ServerResponse updateClientEmail(Map requestMap);

    ServerResponse updateClientArea(Map requestMap);
}
