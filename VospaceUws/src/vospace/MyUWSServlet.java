package vospace;

import uws.UWSException;
import uws.job.JobList;
import uws.job.JobThread;
import uws.job.UWSJob;
import uws.service.UWSServlet;

public class MyUWSServlet extends UWSServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void initUWS() throws UWSException{
		addJobList(new JobList("PullFromVoSpace"));
	}

	@Override
	public JobThread createJobThread(UWSJob job) throws UWSException{
		if (job.getJobList().getName().equals("PullFromVoSpace"))
			return new PullFromVoSpace(job);
		else
			throw new UWSException("Impossible to create a job inside the jobs list \"" + job.getJobList().getName() + "\" !");
	}


}
