#!/bin/bash

while [[ ( ! -f "logfile.txt" ) || ( $(/bin/grep "java.lang.ArithmeticException: / by zero" "logfile.txt" | wc -l) -eq 0 ) ]];  do sleep 5; done; killtree.sh -d -f "$SS_TC_ROOT/rc_parent.pid"