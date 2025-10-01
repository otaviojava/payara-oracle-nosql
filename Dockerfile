FROM payara/micro:6.2025.9-jdk21
COPY target/payara-oracle-nosql-0.1-SNAPSHOT.war $DEPLOY_DIR
