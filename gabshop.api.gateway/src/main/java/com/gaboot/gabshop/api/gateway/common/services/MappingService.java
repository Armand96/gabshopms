package com.gaboot.gabshop.api.gateway.common.services;

import com.gaboot.gabshop.api.gateway.common.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingService<T> {

    public void mapResponseSuccess(ResponseDto<T> resp, T data, String msg){
        mapResponseMsg(resp, msg);
        resp.setDatum(data);
    }

    public void mapResponseSuccess(ResponseDto<T> resp, List<T> data, String msg){
        mapResponseMsg(resp, msg);
        resp.setData(data);
    }

    public void mapResponseSuccess(ResponseDto<T> resp, List<T> data, String msg, int lastPage, int totalData) {
        mapResponseMsg(resp, msg);
        resp.setData(data);
        resp.setLastPage(lastPage);
        resp.setTotalData(totalData);
    }

    void mapResponseMsg(ResponseDto<T> resp, String msg) {
        if(msg.isEmpty()) resp.setMessage(msg);
        resp.setMessage("OK");
        resp.setSuccess(true);
    }

    public void mapResponseMsgFail(ResponseDto<T> resp) {
        resp.setMessage("Error");
        resp.setSuccess(false);
    }


}
