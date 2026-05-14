#!/bin/bash

# Dừng script nếu có lỗi
set -e

echo "Cleaning and compiling project..."
mvn clean compile

echo "Running application..."
mvn exec:java -Dexec.mainClass="org.example.Main"