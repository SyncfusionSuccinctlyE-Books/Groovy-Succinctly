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

class DataSummary {
    final List data

    final Integer count
    final BigDecimal sum, average, max, min

    def DataSummary(List sourceData) {
        if (!sourceData)
            throw new IllegalArgumentException
                    ('The source list cannot be empty')

        data = sourceData.asImmutable()

        count = data.size()
        max = data.max()
        min = data.min()
        sum = data.sum()
        average = sum / count
    }

    String toString() {
        """\
Count: $count
Max: $max
Min: $min
Sum: $sum
Average: $average"""
    }
}
