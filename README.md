RequeueJob
==========

A Jenkins plugin to requeue any jobs that fail due to the remote slave going down.

Usage
=====

To use this plugin, you just need to install it, then click the check box in the project properties to requeue jobs.  If the server/slave that the job is running on fails for any reason, the job will be added to the queue again to ensure that it is run.
