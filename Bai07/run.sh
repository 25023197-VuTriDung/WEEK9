#!/bin/bash

echo "=============================="
echo " Building Maven Project..."
echo "=============================="

mvn clean compile

if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

echo ""
echo "=============================="
echo " Running Program..."
echo "=============================="

java -cp target/classes com.practice.BadCode

echo ""
echo "=============================="
echo " Program Finished"
echo "=============================="