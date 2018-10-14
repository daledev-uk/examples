# NEO4J Data Importer

Project for running configuration files with extension *.gcrmd.json on the classpath which will import data into the host environment.

Using [Liquibase](https://www.liquibase.org/) as an inspiration, goal is to have a means to track what data changes have been run so that things are only run once if required and in order