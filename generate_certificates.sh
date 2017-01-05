#!/bin/sh

DIR="src/main/resources/com/voyager/grpctest/secure"
read -p "Password: " password
country="PH"
state="Metro Manila"
city="Quezon City"
organization="Voyager Innovations"
organization_unit="Core"
common_name="localhost"
email="someone@yahoo.com"


# remove previous files
rm -vf server.*

# Private Key:
##{echo $password; $password} | openssl genrsa -des3 -out server.key 1024
openssl genrsa -des3 -out server.key 1024
expect "Enter pass phrase for server.key:"
send $password
expect "Verifying - Enter pass phrase for server.key:"
send $password


# Private Key in PKCS8 format (for Netty):
echo $password | openssl pkcs8 -topk8 -nocrypt -in server.key -out server.pem

# Certificate Signing Request:
{echo $password; $country; $state; $city; $organization; $organization_unit; $common_name; $email; $password; ""} | openssl req -new -key server.key -out server.csr

# Certificate:
echo $password | openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt