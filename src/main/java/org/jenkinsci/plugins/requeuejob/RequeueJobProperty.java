package org.jenkinsci.plugins.requeuejob;

import java.io.IOException;
import org.kohsuke.stapler.DataBoundConstructor;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import hudson.model.Queue;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Computer;
import hudson.model.Job;

public class RequeueJobProperty extends JobProperty<AbstractProject<?, ?>> {

	public final boolean requeueJob;

	@DataBoundConstructor
	public RequeueJobProperty(boolean requeueJob){
		this.requeueJob = requeueJob;
	}

    public AbstractProject<?, ?> getProject() {
        return (AbstractProject<?, ?>) owner;
    }

    @Override
    public boolean perform(AbstractBuild<?,?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
    	if(this.requeueJob && build.getResult() == Result.FAILURE)
    	{
    		// Job either aborted or failed
    		Computer comp = Computer.currentComputer();
    		if(comp.isOffline())
    		{
    			// If the current computer is offline
    			Queue.getInstance().schedule(build.getProject());
    			return true;
    		}

    		// Job is assumed to have failed normally
    		return true;
    	}
        return true;
    }

    @Extension
    public static class DescriptorImpl extends JobPropertyDescriptor {

    	@Override
        public boolean isApplicable(Class<? extends Job> jobType){
            return AbstractProject.class.isAssignableFrom(jobType);
        }

        @Override
        public String getDisplayName() {
        	return "Requeue Job if slave dies.";
        }
    }
}
