#!/bin/bash
set -e
mkdir -p out
javac -d out $(find src -name "*.java")
echo "Compiled. Run: java -cp out edu.ccrm.cli.Main"
