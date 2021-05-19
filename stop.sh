#!/bin/bash
echo "Kill process...."

while read line; do
pname=$(echo $line | awk '{print $6}')
pid=$(echo $line | awk '{print $1}')

if [[ $pname == tire* ]] || [[ $pname == config* ]] || [[ $pname == Chatting* ]] ;
then
echo "kill -9 " $pid
kill -9 $pid
fi

done< <(ps -a)
