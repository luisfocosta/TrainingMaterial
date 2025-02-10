/*
Copyright 2015 Will Provost.
All rights reserved by Capstone Courseware, LLC.
*/

package cc.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
Generic cache implementation that functions as an invocation handler
and so can be interposed as a dynamic proxy.

@author Will Provost
*/
public class Cache<T>
    extends HashMap<Object,Object>
    implements InvocationHandler
{
    private T delegate;
    private String methodName;
    private int keyIndex;

    /**
    Factory method creates a dynamic proxy with a caching handler.
    */
    // We supply T.class, so we are sure of the return type
    @SuppressWarnings("unchecked")
    public static <T> T createProxy
        (Class<T> tClass, T delegate, String methodName, int keyIndex)
    {
        return (T) Proxy.newProxyInstance
            (delegate.getClass ().getClassLoader (),
                new Class<?>[] { tClass },
                new Cache<T> (delegate, methodName, keyIndex));
    }

    /**
    Builds a cache on a given delegate object. When calling the
    designated method, return values will be cached, with a
    designated method argument as the key; then subsequent calls
    with the same key argument will be handled by the cache, instead
    of delegating. Calls to all other methods will be directly delegated.
    */
    public Cache (T delegate, String methodName, int keyIndex)
    {
        this.delegate = delegate;
        this.methodName = methodName;
        this.keyIndex = keyIndex;
    }

    /**
    If the method name is our designated cacheable method,
    check the map first, and if we have the value already, then
    just return that. Otherwise, call the delegate and add the result
    to the cache before returning it.
    For all other methods, just invoke on the delegate.
    */
    public Object invoke (Object proxy, Method method, Object[] args)
        throws Throwable
    {
        if (method.getName ().equals (methodName))
        {
            if (!containsKey (args[keyIndex]))
                put (args[keyIndex], method.invoke (delegate, args));

            return get (args[keyIndex]);
        }

        return method.invoke (delegate, args);
    }
}

