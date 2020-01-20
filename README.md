Quarkus - Code Your First Supersonic Extension
==============================================

This is a demo application for [DevConf 2020 workshop](https://devconfcz2020a.sched.com/event/YOof/quarkus-code-your-first-supersonic-extension) focused on writing your own Quarkus extension.

Structure
---------

Demo consists of two parts - extension and example application, both of which are separate Maven projects.
Since extension is a dependency of the example, it should be built first.

Repository has multiple branches, each of which corresponds to an addition of certain funcionality.

Goal
----

The aim of this demo is to show an extension that processes and automatically monitors JAX-RS `@GET` resources and intercepts their invocations.
If an invocation exceeds a configurable limit, it logs a warning.
Furthermore the extension reports on its status and configuration when started.

To sum it up, this demo uses the following:

- Jandex index analysis
- Annotation transformation
- Extension configuration
- Bytecode recording
- Quarkus extension testing tooling

Building projects
------------------

Both projects are Maven project and can be built (including tests) by moving into respective sub-directory and running: 

- `mvn clean install`

If you wish to only execute tests, then run:

- `mvn clean verify`

Starting the application
------------------------

In order to start the example app in dev mode, do the following:

- `cd example`
- `mvn quarkus:dev`

This will build the appplication and deploy it to [`http://0.0.0.0:8080`](http://0.0.0.0:8080).
