## Installing sbt on Linux

https://www.scala-sbt.org/1.0/docs/Installing-sbt-on-Linux.html

### Red Hat Enterprise Linux and other RPM-based distributions

RPM package is officially supported by sbt.

Red Hat Enterprise Linux and other RPM-based distributions use the RPM format. Run the following from the terminal to install sbt (You’ll need superuser privileges to do so, hence the sudo).

```bash

curl https://bintray.com/sbt/rpm/rpm | sudo tee /etc/yum.repos.d/bintray-sbt-rpm.repo
sudo yum install sbt

```

sbt binaries are published to Bintray, and conveniently Bintray provides an RPM repository. You just have to add the repository to the places your package manager will check.

On Fedora, sbt 0.13.1 available on official repos. If you want to install sbt 1.1.6 or above, you may need to uninstall sbt 0.13 (if it’s installed) and indicate that you want to install the newest version of sbt (i.e. sbt 1.1.6 or above) using bintray-sbt-rpm.repo then.

```shell

[elastic4s_sample]$ sbt
sbt:elastic4s-sample> 
sbt:elastic4s-sample> help
sbt:elastic4s-sample> tasks

This is a list of tasks defined for the current project.
It does not list the scopes the tasks are defined in; use the 'inspect' command for that.
Tasks produce values.  Use the 'show' command to run the task and print the resulting value.

  bgRun                   Start an application's default main class as a background job
  bgRunMain               Start a provided main class as a background job
  clean                   Deletes files produced by the build, such as generated sources, compiled classes, and task caches.
  compile                 Compiles sources.
  console                 Starts the Scala interpreter with the project classes on the classpath.
  consoleProject          Starts the Scala interpreter with the sbt and the build definition on the classpath and useful imports.
  consoleQuick            Starts the Scala interpreter with the project dependencies on the classpath.
  copyResources           Copies resources to the output directory.
  coursierWhatDependsOn   Prints dependencies and transitive dependencies as an inverted tree for a specific module (dependees as children)
  doc                     Generates API documentation.
  fgRun                   Start an application's default main class as a foreground job
  fgRunMain               Start a provided main class as a foreground job
  package                 Produces the main artifact, such as a binary jar.  This is typically an alias for the task that actually does the packaging.
  packageBin              Produces a main artifact, such as a binary jar.
  packageDoc              Produces a documentation artifact, such as a jar containing API documentation.
  packageSrc              Produces a source artifact, such as a jar containing sources and resources.
  publish                 Publishes artifacts to a repository.
  publishLocal            Publishes artifacts to the local Ivy repository.
  publishM2               Publishes artifacts to the local Maven repository.
  run                     Runs a main class, passing along arguments provided on the command line.
  runMain                 Runs the main class selected by the first argument, passing the remaining arguments to the main method.
  test                    Executes all tests.
  testOnly                Executes the tests provided as arguments or all tests if no arguments are provided.
  testQuick               Executes the tests that either failed before, were not run or whose transitive dependencies changed, among those provided as arguments.
  update                  Resolves and optionally retrieves dependencies, producing a report.

More tasks may be viewed by increasing verbosity.  See 'help tasks'
```