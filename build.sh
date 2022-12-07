#!/bin/bash

mvn clean install $skip && native-image -classpath target/mycommand-1.0-SNAPSHOT-shaded.jar org.example.App ;mv org.example.app ~/bin/color
