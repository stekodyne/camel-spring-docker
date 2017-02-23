#!/bin/bash

set -e

openssl s_client -connect 10.255.242.20:143 -starttls imap > imap.cer

cat imap.cer | awk '/-----BEGIN CERTIFICATE-----/{f=1} /-----END CERTIFICATE-----/{f=0;print} f' > imap.cert

echo "yes" | $JAVA_HOME/bin/keytool -import -alias soa1 -file imap.cert -keystore $JAVA_HOME/jre/lib/security/jssecacerts -storepass changeme

rm imap.*
rm install.sh
