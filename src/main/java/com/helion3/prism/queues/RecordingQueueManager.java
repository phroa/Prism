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
package com.helion3.prism.queues;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.api.data.DataContainer;

import com.helion3.prism.Prism;

public class RecordingQueueManager extends Thread {

    @Override
    public void run() {

        while (true) {

            List<DataContainer> eventsSaveBatch = new ArrayList<DataContainer>();

            // Assume we're iterating everything in the queue
            while (!RecordingQueue.getQueue().isEmpty()) {

                // Poll the next event, append to list
                DataContainer event = RecordingQueue.getQueue().poll();
                if (event != null) {
                    eventsSaveBatch.add(event);
                }
            }

            if (eventsSaveBatch.size() > 0) {
                try {
                    Prism.getStorageAdapter().records().write(eventsSaveBatch);
                } catch (Exception e) {
                    // @todo handle failures
                    e.printStackTrace();
                }
            }

            // Delay next execution
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}