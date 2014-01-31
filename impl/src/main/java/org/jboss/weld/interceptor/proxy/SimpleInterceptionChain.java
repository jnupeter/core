/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.weld.interceptor.proxy;

import java.lang.reflect.Method;
import java.util.List;

import javax.interceptor.InvocationContext;

import org.jboss.weld.interceptor.chain.AbstractInterceptionChain;
import org.jboss.weld.interceptor.spi.metadata.InterceptorClassMetadata;
import org.jboss.weld.interceptor.spi.model.InterceptionType;
import org.jboss.weld.security.SetAccessibleAction;

/**
 * @author <a href="mailto:mariusb@redhat.com">Marius Bogoevici</a>
 */
public class SimpleInterceptionChain extends AbstractInterceptionChain {

    public SimpleInterceptionChain(Object instance, Method method, Object[] args, InterceptionType interceptionType, InterceptionContext ctx) {
        super(instance, method, args, interceptionType, ctx);
    }

    public SimpleInterceptionChain(InterceptorInvocation interceptorInvocation) {
        super(interceptorInvocation);
    }

    public SimpleInterceptionChain(List<InterceptorClassMetadata<?>> interceptorMetadata, InterceptionContext ctx, InterceptionType interceptionType) {
        super(interceptorMetadata, ctx, interceptionType);
    }

    @Override
    protected Object interceptorChainCompleted(InvocationContext ctx) throws Exception {
        Method method = ctx.getMethod();
        if (method != null) {
            SetAccessibleAction.ensureAccessible(method);
            return method.invoke(ctx.getTarget(), ctx.getParameters());
        } else {
            return null;
        }
    }
}
