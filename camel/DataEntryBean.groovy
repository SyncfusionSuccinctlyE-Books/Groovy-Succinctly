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

@Grab('org.apache.camel:camel-bindy:2.16.0')
import org.apache.camel.dataformat.bindy.annotation.CsvRecord
import org.apache.camel.dataformat.bindy.annotation.DataField

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement
import java.time.LocalDateTime

/**
 * A basic bean for handling incoming CSV data.
 * Provides a mapping to XML.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CsvRecord(separator = ',', skipFirstLine = false)
class DataEntryBean {
    static random = new Random()

    @XmlAttribute
    @DataField(pos = 1, required = true, trim = true)
    final String timestamp

    @XmlAttribute
    @DataField(pos = 2, required = true, trim = true)
    final Integer value

    DataEntryBean() {
        timestamp = LocalDateTime.now().toString()
        value = random.nextInt(1000)
    }

    String toString() {
        "$timestamp,$value".toString()
    }
}
