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
 * A small demo of reading from a URL
 */

//Source file: ReadUrl.groovy

//The basic (easy) approach
print 'http://www.example.com/'.toURL().text

//Using a reader (handy for larger pages)
'http://www.example.com/'.toURL().withReader { reader ->
    print reader.text
}
