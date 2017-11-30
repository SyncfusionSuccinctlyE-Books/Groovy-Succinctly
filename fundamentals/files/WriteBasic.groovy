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
 * Write a text file using the `text` property and a Writer
 * Also write a temporary file
 */

//Source file: WriteBasic.groovy
import java.nio.file.Files
import java.nio.file.Paths

def haiku = '''\
groovy makes it easy
less boilerplate to create mess
solve the problem quick
'''

Paths.get('haiku1.tmp').text = haiku

Paths.get('haiku2.tmp').withWriter { writer ->
    writer.write(haiku)
}

def tempFile = Files.createTempFile('haiku', '.tmp')
tempFile.write(haiku)
println "Wrote to temp file: $tempFile:"
