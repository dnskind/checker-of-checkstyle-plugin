# checker-of-checkstyle-plugin

Check if the maven-checkstyle-plugin applied as build plugin.

Output information happy case:
-Project name
-Number of plugins
-Plugin name
-Plugin version
-Plugin configuration[configLocation]
-Plugin configuration[includeTestSourceDirectory]
or
maven-checkstyle-plugin not found


<plugin>
    <groupId>org.dnskind</groupId>
    <artifactId>checker-of-checkstyle-plugin</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <executions>
        <execution>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
