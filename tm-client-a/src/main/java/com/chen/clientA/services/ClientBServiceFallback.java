package com.chen.clientA.services;

import com.codingapi.txlcn.tc.support.DTXUserControls;
import com.codingapi.txlcn.tracing.TracingContext;
import org.springframework.stereotype.Component;

@Component
public class ClientBServiceFallback implements ClientBService {
    @Override
    public String getClientBSuccess() {
        return null;
    }

    @Override
    public String createClientB(Integer id) {
        DTXUserControls.rollbackGroup(TracingContext.tracing().groupId());
        return null;
    }

    @Override
    public String createClientBThrowError(Integer id) {
        return null;
    }

    @Override
    public String createClientBTransactionError(Integer id) {
        return null;
    }

    @Override
    public String updateClientBTransactionError(Integer id) {
        return null;
    }

}
