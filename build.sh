#!/bin/bash
echo 'installing & starting postfix'
apt install postfix
cp /usr/share/postfix/main.cf.debian /etc/postfix/main.cf
service postfix start

echo 'installing & starting mongo'
mkdir /data
mkdir /data/db
apt install mongo
mongod &

echo 'setting config repo location'
export CONFIG_REPO_LOCATION=$(pwd)/configrepo

echo 'installing maven'
apt install maven

echo 'installing jdk'
apt-get install default-jdk

for directoryName in */
do
    mvn clean install -f $directoryName/pom.xml
done

microservices = ("registry" "configuration" "gateway" "publisher" "news-reader" "espn" "scheduler" "communicator")
for ms in microservices
do
    java -jar $directoryName/target/
