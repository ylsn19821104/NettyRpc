package com.nettyrpc.client.proxy;

import com.nettyrpc.client.RPCFuture;

/**
 * Created by hongxp on 2016/3/16.
 */
public interface IAsyncObjectProxy {
    public RPCFuture call(String funcName, Object... args);
}