mvn clean deploy -P release -Dgpg.passphrase=P2ssw0rd -Dmaven.test.skip=true

mvn clean deploy -P snapshot -Dgpg.passphrase=P2ssw0rd -Dmaven.test.skip=true

mvn clean deploy -P nexus-release -Dmaven.test.skip=true

mvn clean deploy -P nexus-snapshot -Dmaven.test.skip=true