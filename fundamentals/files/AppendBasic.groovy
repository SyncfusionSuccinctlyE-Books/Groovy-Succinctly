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
 * Appends text to a file.
 *
 * Also demonstrates `import static` as an approach to neater code
 */

//Source file: AppendBasic.groovy
import static java.nio.file.Paths.get as getFile

def list = ['cat', 'dog', 'rabbit']

getFile('pets.tmp').withWriterAppend {writer ->
    list.each { item ->
        writer.write "$item\n"
    }
}

def list2 = ['fish', 'turtle']
def file = getFile('pets.tmp')
list2.each { pet ->
    file << "$pet\n"
}
