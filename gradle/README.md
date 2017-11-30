# A basic Gradle-Groovy project

The basis of the structure was created using:

    gradle init --type groovy-library

In order to build the system:

- Linux & OS X: `./gradlew clean build`
- Windows: `gradlew.bat clean build`

Now check out:

- `build/distributions`: A handy archive to send to your friends
- `build/scripts` and `build/libs`:
- `build/reports`: Any build reports (mainly Spock test results)

The `build/distributions` contains a `zip` and `tar` file, each holding a copy
of your compiled application. Extract one of these archives to a handy location then
open your terminal and change to the base directory for the extracted files. From here
you can give the app a spin:

- Linux & OS X users:
    - To view the application's usage info: `bin/gradle-demo -h`
    - To get a summary of a data file: `bin/gradle-demo -f <input file>`
- Windows users:
     - To view the application's usage info: `bin\gradle-demo.bat -h`
     - To get a summary of a data file: `bin\gradle-demo.bat -f <input file>`

Remember that the input file is a text file with a series of numbers - one one each line.
