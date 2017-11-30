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
 * Reads an XML file from a URL and parses out information
 *
 * Note: See http://jsoup.org/ if you want to parse HTML pages
 */

//Source file: ReadXmlUrl.groovy

//Grab the XML document listing all IANA media types
def slurper = new XmlSlurper()

def url = 'http://www.iana.org/assignments/media-types/media-types.xml'.toURL()
def page = slurper.parseText(url.text)
println "${page.title.text()} last updated ${page.updated.text()}"
