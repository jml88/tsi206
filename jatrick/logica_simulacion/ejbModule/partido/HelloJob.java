package partido;

//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.jobs.ee.ejb.EJB3InvokerJob;
//
//public class HelloJob extends EJB3InvokerJob {
//
//	public HelloJob() {
//	}
//
//	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//		JobDataMap data = jobExecutionContext.getMergedJobDataMap();
//
//		//Indico el jndi del EJB y el método a invocar.
//		data.put(EJB_JNDI_NAME_KEY, "java:global/jatrick_ear/logica_simulacion/LogicaSimulacion!partido.LogicaSimulacionI");
//		data.put(EJB_METHOD_KEY, "simular");
//
//		//Cargo argumento que se le pasará por parámetro a la operación EJB_METHOD_KEY.
//		Object[] argumentos = new Object[1];
////		argumentos[0] = data.get("datosConfiguracion");
////		data.put(EJB_ARGS_KEY, argumentos);
//		Class[] argumentTypes = new Class[1];
//		argumentTypes[0] =Integer.class;
////		data.put(EJB_ARG_TYPES_KEY, argumentTypes);
//
//		super.execute(jobExecutionContext);
//	}
//}
