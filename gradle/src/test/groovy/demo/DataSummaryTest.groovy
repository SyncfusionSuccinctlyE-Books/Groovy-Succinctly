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

package demo

import spock.lang.Specification
import spock.lang.Unroll

class DataSummaryTest extends Specification {
    @Unroll("Number list: #numbers")
    def "Trivial DataSummary Test with a basic data table"() {
        given: "A new DataSummary object"
        def summary = new DataSummary(numbers)

        expect: "That the stats are correct"
        summary.count == count
        summary.max == max
        summary.min == min
        summary.sum == sum
        summary.average == average

        where: "The input data and expected summaries are"
        numbers        || count | min | max | sum | average
        [ 10 ]         || 1     | 10  | 10  | 10  | 10
        [ 10, 12 ]     || 2     | 10  | 12  | 22  | 11
        [ 10, 12, 14 ] || 3     | 10  | 14  | 36  | 12
    }

    def "Ensure that an IllegalArgumentException is thrown with an empty list"() {
        when: "I try to create a new DataSummary with an empty list"
        new DataSummary([ ])
        then: "the correct exception should be thrown"
        thrown(IllegalArgumentException)
    }
}
