package com.fh.shop.backend.mapper.client;

import com.fh.shop.backend.po.client.ClientPO;

public interface IClientMapper {

    ClientPO findClientById(Integer id);

}
