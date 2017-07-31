package vospace;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import uws.UWSException;
import uws.job.ErrorType;
import uws.job.JobThread;
import uws.job.Result;
import uws.job.UWSJob;
import uws.service.actions.GetJobParam;

public class PullFromVoSpace extends JobThread {

	public PullFromVoSpace(UWSJob j) throws NullPointerException {
		super(j);
	}

	@Override
	protected void jobWork() throws UWSException, InterruptedException {
		VoCore vo = new VoCore();
		String target = (String)getJob().getAdditionalParameterValue("target");
		String direction = (String)getJob().getAdditionalParameterValue("direction");
		String securityMethod = (String)getJob().getAdditionalParameterValue("securitymethod");
		String keepBytes = (String)getJob().getAdditionalParameterValue("KeepBytes");
		String jobtype = (String)getJob().getAdditionalParameterValue(getName());
		vo.setTarget(target);
		vo.setDirection(direction);
		vo.setSecurity(securityMethod);
		vo.setKeepbytes(keepBytes);
		
		Mydb vodb = new Mydb();
		System.out.println(vodb.getNode());
		System.out.println("Job : "+jobtype);
		System.out.println("Target : "+vo.getTarget());
		System.out.println("Direction : "+vo.getDirection());
		System.out.println("Securité : "+vo.getSecurity());
		System.out.println("KeepBytes : "+vo.getKeepbytes());
		System.out.println("Done for job #"+getJob().getJobId());
			
		
		if (isInterrupted())
			throw new InterruptedException();
		
		
		/*try{
			Result result = createResult("Report");	
			
			BufferedOutputStream output = new BufferedOutputStream(getResultOutput(result));
			output.write(("node : " + nodePath + ", time : " + executionTime).getBytes("ISO-8859-1"));
			output.close();

			publishResult(result);

		}catch(IOException e){
			throw new UWSException(UWSException.INTERNAL_SERVER_ERROR, e, "Impossible to write the result file of the Job " + job.getJobId() + " !", ErrorType.TRANSIENT);
		}*/
	}


	
	
}
