#!/bin/bash

echo "=============================="
echo " Running Maven Tests..."
echo "=============================="

mvn test

if [ $? -ne 0 ]; then
    echo "Tests failed!"
    exit 1
fi

echo ""
echo "=============================="
echo " All Tests Passed!"
echo "=============================="