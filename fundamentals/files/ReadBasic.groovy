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

/*
 * Read a text file using the `text` property and a Reader
 */

//Source file: ReadBasic.groovy
import java.nio.file.Paths

def file = Paths.get('demo.txt')

println 'Reading a file with using the .text attribute:'
print file.text

println 'Reading a file via a Reader:'
file.withReader { reader ->
    print reader.text
}
