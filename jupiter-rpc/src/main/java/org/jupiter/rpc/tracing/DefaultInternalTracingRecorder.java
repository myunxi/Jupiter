/*
 * Copyright (c) 2015 The Jupiter Project
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jupiter.rpc.tracing;

import org.jupiter.common.util.StringBuilderHelper;
import org.jupiter.common.util.internal.logging.InternalLogger;
import org.jupiter.common.util.internal.logging.InternalLoggerFactory;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * jupiter
 * org.jupiter.rpc.tracing
 *
 * @author jiachun.fjc
 */
public class DefaultInternalTracingRecorder extends TracingRecorder {

    private static final InternalLogger logger = InternalLoggerFactory.getInstance(DefaultInternalTracingRecorder.class);

    @Override
    public void recording(Role role, Object... args) {
        if (logger.isInfoEnabled()) {
            if (role == Role.CONSUMER) {
                String traceInfo = StringBuilderHelper.get()
                        .append("[Consumer] - ")
                        .append(args[0])
                        .append(", invokeId: ")
                        .append(args[1])
                        .append(", callInfo: ")
                        .append(args[2])
                        .append('#')
                        .append(args[3])
                        .append(", on ")
                        .append(args[4]).toString();

                logger.info(traceInfo);
            } else if (role == Role.PROVIDER) {
                String traceInfo = StringBuilderHelper.get()
                        .append("[Provider] - ")
                        .append(args[0])
                        .append(", invokeId: ")
                        .append(args[1])
                        .append(", callInfo: ")
                        .append(args[2])
                        .append(", elapsed: ")
                        .append(NANOSECONDS.toMillis((long) args[3]))
                        .append(" millis, on ")
                        .append(args[4]).toString();

                logger.info(traceInfo);
            }
        }
    }
}
