package org.dnskind;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Build;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@Mojo(name = "check", defaultPhase = LifecyclePhase.COMPILE)
public class StylerCheckerMojo
    extends AbstractMojo
{
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute()
        throws MojoExecutionException
    {
        Boolean artifactFound = false;
        System.out.println("Project name: "+project.getName());
        List<Plugin> buildPlugins = project.getBuildPlugins();

        long numBuildPlugins = buildPlugins.stream().count();
        System.out.println("Number of plugins: "+numBuildPlugins);

        for(Plugin buildPlugin:buildPlugins) {
            if (buildPlugin.getArtifactId().matches("maven-checkstyle-plugin")) {
                artifactFound = true;
                System.out.println("Plugin name: "+buildPlugin.getArtifactId());
                System.out.println("Plugin version: "+buildPlugin.getVersion());
                System.out.println("Plugin configuration[configLocation]: "+getConfigData(buildPlugin.getConfiguration()).getConfigLocation());
                System.out.println("Plugin configuration[includeTestSourceDirectory]: "+getConfigData(buildPlugin.getConfiguration()).getIncludeTestSourceDirectory());
                continue;
            }
        }

        if (!artifactFound) System.out.println("maven-checkstyle-plugin not found");

    }

    private ConfigurationData getConfigData(Object configuration) {
        ConfigurationData confData = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ConfigurationData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            confData = (ConfigurationData) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(configuration.toString().getBytes(StandardCharsets.UTF_8)));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return confData;
    }
}
