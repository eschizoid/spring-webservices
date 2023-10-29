## spring-webservices

## Generate JAXB classes from XSD

```bash
./gradlew clean build genJaxb -x test
```

## Generate ssl bundles

```bash
openssl req \
  -x509 \
  -out localhost.crt \
  -keyout localhost.key \
  -newkey rsa:2048 \
  -nodes -sha256 \
  -subj '/CN=localhost' \
  -extensions EXT -config <( \
   printf "[dn]\nCN=localhost\n[req]\ndistinguished_name = dn\n[EXT]\nsubjectAltName=DNS:localhost\nkeyUsage=digitalSignature\nextendedKeyUsage=serverAuth")

openssl pkcs12 \
  -export \
  -in src/main/resources/localhost.crt \
  -inkey src/main/resources/localhost.key \
  -name 'secure-server' \
  -out src/main/resources/keystore.p12
```

## Run application

```bash
./gradlew booRun
```

## Sample curl command

```bash
curl <<-EOF --insecure -fsSL -H "content-type: text/xml" -d @- https://localhost:8443/ws \
  > target/response.xml && xmllint --format target/response.xml

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                                  xmlns:gs="http://spring.io/spring-webservices">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:getCountryRequest>
         <gs:name>Spain</gs:name>
      </gs:getCountryRequest>
   </soapenv:Body>
</soapenv:Envelope>

EOF
```