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
@Grab('org.xerial:sqlite-jdbc:3.8.11.2')

@Grab('org.apache.camel:camel-core:2.16.0')
@Grab('org.apache.camel:camel-bindy:2.16.0')
@Grab('org.apache.camel:camel-jdbc:2.16.0')
@Grab('org.apache.activemq:activemq-camel:5.13.1')
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat as CSV
import org.apache.camel.main.Main
@Grab('org.apache.commons:commons-dbcp2:2.1.1')
import org.apache.commons.dbcp2.BasicDataSource

import groovy.sql.Sql

/*
 * This is a queue consumer for the messages produced by
 * ProducerQueue.groovy. Each message is read into a DataEntryBean
 * then inserted into an SQLite database
 *
 * Unlike in ConsumerQueueDatabase.groovy, the message body is set using setBody,
 * rather than a bean, to create the SQL command.
 *
 * See: http://camel.apache.org/simple.html
 */

new Main() {
    {
        enableHangupSupport()

        def ds = new BasicDataSource()
        ds.driverClassName = 'org.sqlite.JDBC'
        ds.url = 'jdbc:sqlite:logger_data.db'
        registry.put('demoDb', ds)

        Sql.withInstance(ds.url) { sql ->
            sql.execute '''
            CREATE TABLE IF NOT EXISTS readings (
                timestamp NOT NULL,
                value NOT NULL)'''
        }

        addRouteBuilder new RouteBuilder() {
            void configure() {
                from('activemq:DEMO')
                    .unmarshal(new CSV(DataEntryBean))
                    .setBody(simple('INSERT INTO readings (timestamp, value) VALUES ("${body.timestamp}", ${body.value})'))
                    .to('log:camelApp?level=INFO')
                    .to('jdbc:demoDb')
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
