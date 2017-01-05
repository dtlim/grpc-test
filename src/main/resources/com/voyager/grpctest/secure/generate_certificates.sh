#!/bin/bash

DIR="src/main/resources/com/voyager/grpctest/secure"
country="PH"
state="Metro Manila"
city="Quezon City"
organization="Voyager Innovations"
organization_unit="Core"
common_name="localhost"
email="someone@yahoo.com"

read -p "Password: " password
read -p "Common name (default: localhost): " common_name

if [ -z "$common_name" ]; then
	common_name="localhost"
fi

# remove previous files
rm -vf server.*

# Private Key:
openssl genrsa -des3 -passout pass:'$password' -out server.key 1024

# Private Key in PKCS8 format (for Netty):
openssl pkcs8 -topk8 -nocrypt -in server.key -out server.pem -passin pass:'$password'

# Certificate Signing Request:
openssl req -new -key server.key -out server.csr -passin pass:'$password' -subj "/CN=$common_name"

# Certificate:
openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt -passin pass:'$password'
openssl x509 -in server.crt -out server.crt.pem -outform PEM -passin pass:'$password'
