package grupo.teste01.poc;

import java.io.Serializable;
import java.util.TimerTask;

import javax.enterprise.context.ApplicationScoped;

import br.gov.frameworkdemoiselle.util.Beans;

@ApplicationScoped
class TarefasDiarias extends TimerTask implements Serializable {

	private static final long serialVersionUID = 1L;

	public void run() {
		System.out.println("TarefasDiarais.run()");
//		String fileName = "/media/025C9CA35874DA84/Pessoal/workspace/git-rest01/NTN-B_2015-20150609-1615.xls";
		String fileName = "/var/lib/openshift/5575900a500446b971000169/app-root/data/NTN-B_2015.xls";
		GetFileFromInternetPoc01.getFileFromInternet(fileName);
		try {
			Beans.getReference(ExcelPoc01.class).createBookmarkLastLineNTNB(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}