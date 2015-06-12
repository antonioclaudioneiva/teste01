package grupo.teste01.poc;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.lifecycle.Startup;

public class SchedulePoc01 {
	
	Timer timer;
	
	@Inject
	TarefasDiarias tarefasDiarias;
	
	public void utilizarTimeService(){
	}
	
	@Startup
	public void iniciar() {
		timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 13);
		calendar.set(Calendar.MINUTE, 15);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();
		timer.schedule(tarefasDiarias, time);
//		timer.schedule(tarefasDiarias, 5000, 20000);
	}

	public void parar() {
		timer.cancel();
	}

}
