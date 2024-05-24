# Bootstrap Artists App

mvn -U io.quarkus:quarkus-maven-plugin:create \
 -DprojectGroupId=com.jovisco.quarkus.orm \
 -DprojectArtifactId=artists \
 -DpackageName="com.jovisco.quarkus.jdbc" \
 -Dextensions="jdbc-mysql, quarkus-agroal"

# Bootstrap Customers App

mvn -U io.quarkus:quarkus-maven-plugin:create \
 -DprojectGroupId=com.jovisco.quarkus.orm \
 -DprojectArtifactId=customers \
 -DpackageName="com.jovisco.quarkus.jpa" \
 -Dextensions="jdbc-mariadb, hibernate-orm"

# Bootstrap Store App

mvn -U io.quarkus:quarkus-maven-plugin:create \
 -DprojectGroupId=com.jovisco.quarkus.orm \
 -DprojectArtifactId=store \
 -DpackageName="com.jovisco.quarkus.panache" \
 -Dextensions="jdbc-postgresql, hibernate-orm-panache"
