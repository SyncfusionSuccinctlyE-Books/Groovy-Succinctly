/*
 *    Copyright 2016 Duncan Dickinson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

@GrabConfig(systemClassLoader = true)
@Grab('ch.qos.logback:logback-classic:1.1.3')
@Grab('org.apache.camel:camel-core:2.16.0')
@Grab('org.apache.camel:camel-stream:2.16.0')
@Grab('org.apache.activemq:activemq-camel:5.13.1')

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.main.Main

/*
 * This is a test queue consumer for the messages produced by
 * ProducerQueue.groovy. The message is just displayed in the
 * console.
 */

new Main() {
    {
        enableHangupSupport()
        addRouteBuilder new RouteBuilder() {
            void configure() {
                from('activemq:DEMO').to('stream:out')
            }
        }
    }

    void afterStart() {
        LOG.info 'Started Camel. Use ctrl + c to terminate the JVM.'
    }

    void beforeStop() {
        LOG.info 'Stopping Camel.'
    }
}.run()
