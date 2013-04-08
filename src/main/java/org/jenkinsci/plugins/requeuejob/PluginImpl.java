package org.jenkinsci.plugins.requeuejob;

import hudson.Extension;
import hudson.Plugin;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.model.Hudson;

import java.util.logging.Logger;


@Extension
public class PluginImpl extends Plugin implements Describable<PluginImpl> {
	private final static Logger LOG = Logger.getLogger(PluginImpl.class.getName());

    public Descriptor<PluginImpl> getDescriptor() {
        return (DescriptorImpl)Hudson.getInstance().getDescriptorOrDie(getClass());
    }

    public static PluginImpl get() {
        return Hudson.getInstance().getPlugin(PluginImpl.class);
    }

    @Extension
    public static final class DescriptorImpl extends Descriptor<PluginImpl> {
        @Override
        public String getDisplayName() {
            return "RequeueJob PluginImpl";
        }
    }
}
