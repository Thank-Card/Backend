package com.thankscard.hello.service;

import com.thankscard.hello.exception.HelloException;
import com.thankscard.hello.exception.HelloStatus;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == null) {
            throw new HelloException(HelloStatus.HELLO_FLAG_NULL_ERROR);
        }
        if (flag == 1) {
            throw new HelloException(HelloStatus.HELLO_TEST_ERROR);
        }
    }
}
