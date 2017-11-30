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

class AppTest extends Specification {
    @Unroll("Input file: #file")
    def "Testing loadNumberListFromFile"() {
        expect:
        App.loadNumberListFromFile("src/test/resources/$file") == result

        where: "The input file has an associated result"
        file                || result
        'sample-data-1.txt' || [ 5, 6, 2, 9, 0, 1 ]
        'sample-data-2.txt' || [ 5, 6, 2, 9, 0, 1 ]
        'sample-data-3.txt' || [ ]
        'sample-data-4.txt' || [ 5, 6, 2, 9, 0, 1 ]
        'sample-data-5.txt' || [ -5, 6, 2, -9.3, 0.2, 1 ]
    }

    def "Ensure that a FileNotFoundException is thrown for absent files"() {
        when: "I try to load a file that doesn't exist"
        App.loadNumberListFromFile('')
        then: "the correct exception should be thrown"
        thrown(FileNotFoundException)
    }
}
