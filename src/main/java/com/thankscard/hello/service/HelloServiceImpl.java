package com.thankscard.hello.service;

import com.thankscard.global.api.code.ErrorStatus;
import com.thankscard.global.api.exception.HelloException;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == null) {
            throw new HelloException(ErrorStatus.HELLO_FLAG_NULL_ERROR);
        }
        if (flag == 1) {
            throw new HelloException(ErrorStatus.HELLO_TEST_ERROR);
        }
    }
}
