/**
 * This file is part of Prism, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015 Helion3 http://helion3.com/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.helion3.prism.api.flags;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.helion3.prism.api.query.Query;
import com.helion3.prism.api.query.QuerySession;
import org.spongepowered.api.command.CommandSource;

import javax.annotation.Nullable;
import java.util.Optional;


public class FlagClean extends SimpleFlagHandler {
    /**
     * Flag which allows illegal blocks/item entities to be drained from an applier region.
     */
    public FlagClean() {
        super(ImmutableList.of("clean"));
    }

    @Override
    public boolean acceptsSource(@Nullable CommandSource source) {
        return true;
    }

    @Override
    public boolean acceptsValue(String value) {
        return true;
    }

    @Override
    public Optional<ListenableFuture<?>> process(QuerySession session, String parameter, Optional<String> value, Query query) {
        session.addFlag(Flag.CLEAN);
        return Optional.empty();
    }
}