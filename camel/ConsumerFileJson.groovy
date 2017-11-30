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
@Grab('org.apache.camel:camel-bindy:2.16.0')
@Grab('org.apache.camel:camel-xstream:2.16.0')
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat as CSV
import org.apache.camel.main.Main

/*
 * Reads CSV files from the data_in directory into a DataEntryBean
 * and converts the record to a pretty-printed JSON file in data_out
 */

new Main() {
    {
        enableHangupSupport()
        addRouteBuilder new RouteBuilder() {
            void configure() {
                from('file://data_in/')
                    .unmarshal(new CSV(DataEntryBean))
                    .marshal().json(true)
                    .to('file://data_out/?fileName=${file:onlyname.noext}.json')
                    .to('log:camelApp?level=INFO')
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
