package vospace;

import java.io.BufferedOutputStream;
import java.io.IOException;

import uws.UWSException;
import uws.job.ErrorType;
import uws.job.JobThread;
import uws.job.Result;
import uws.job.UWSJob;

public class PullFromVoSpace extends JobThread {

	public PullFromVoSpace(UWSJob j) throws NullPointerException {
		super(j);
	}

	@Override
	protected void jobWork() throws UWSException, InterruptedException {
		String nodePath = "Path de la node";
		int count = 0;
		int executionTime = (int)getJob().getAdditionalParameterValue("time");
		
		while(!isInterrupted() && count < executionTime){
			Thread.sleep(1000);
			count++;
		}
		
		if (isInterrupted())
			throw new InterruptedException();
		
		try{
			Result result = createResult("Report");	
			
			BufferedOutputStream output = new BufferedOutputStream(getResultOutput(result));
			output.write((nodePath + "time : " + executionTime).getBytes("ISO-8859-1"));
			output.close();

			publishResult(result);

		}catch(IOException e){
			throw new UWSException(UWSException.INTERNAL_SERVER_ERROR, e, "Impossible to write the result file of the Job " + job.getJobId() + " !", ErrorType.TRANSIENT);
		}
	}


	
	
}
