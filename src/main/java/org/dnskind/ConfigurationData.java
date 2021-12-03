package org.dnskind;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigurationData {
        @XmlElement(name = "configLocation")
        protected String configLocation;
        @XmlElement(name = "includeTestSourceDirectory")
        protected String includeTestSourceDirectory;

        public String getConfigLocation() {
            return configLocation;
        }

        public void setConfigLocation(String configLocation) {
            this.configLocation = configLocation;
        }

        public String getIncludeTestSourceDirectory() {
            return includeTestSourceDirectory;
        }

        public void setIncludeTestSourceDirectory(String includeTestSourceDirectory) {
            this.includeTestSourceDirectory = includeTestSourceDirectory;
        }
}
