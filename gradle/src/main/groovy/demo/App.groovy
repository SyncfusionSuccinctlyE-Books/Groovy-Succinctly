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

import java.nio.file.Files
import java.nio.file.Paths

class App {
    static final cli =
            new CliBuilder(usage: 'App [options]')

    static {
        cli.with {
            f longOpt: 'file',
                    args: 1,
                    argName: 'inputFile',
                    'The input file with one number per line'
            h longOpt: 'help',
                    'Displays the usage information'
        }
    }

    static loadNumberListFromFile(String fileName) {
        def path = Paths.get(fileName)

        if (!Files.isRegularFile(path))
            throw new FileNotFoundException
                    ("Path does not exist: $fileName")

        path.readLines().findAll {
            it.isNumber()
        }.collect {
            it.toBigDecimal()
        }
    }

    static void main(args) {

        def displayHelpAndExit = {
            cli.usage()
            System.exit 0
        }

        def displayErrorAndExit = { message, errorNumber = -1 ->
            System.err.println "Error: $message"
            System.exit errorNumber
        }

        def options = cli.parse args

        if (options.h)
            displayHelpAndExit()

        if (!options.f)
            displayErrorAndExit 'No input file provided', -2

        def inputData = null
        try {
            inputData = loadNumberListFromFile options.f
        } catch (any) {
            displayErrorAndExit "${any.message}", -3
        }

        if (!inputData)
            displayErrorAndExit "No numeric data in ${options.f}", -4

        println "${new DataSummary(inputData)}"

        System.exit 0
    }
}
