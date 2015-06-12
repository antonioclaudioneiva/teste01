package grupo.teste01.poc;

import grupo.teste01.business.BookmarkBC;
import grupo.teste01.entity.Bookmark;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityManager;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import br.gov.frameworkdemoiselle.context.RequestContext;
import br.gov.frameworkdemoiselle.util.Beans;

public class ExcelPoc01 implements Serializable {

	private static final long serialVersionUID = 1L;

	// public static void main(String[] args) throws IOException, BiffException
	// {
	// createBookmarkLastLineNTNB("/media/025C9CA35874DA84/Pessoal/workspace/git-rest01/NTN-B_2015-20150609-1050.xls");
	// }

	public void createBookmarkLastLineNTNB(String fileName) throws IOException, BiffException {
//		System.out.println("Nome do arquivo=" + fileName);
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		Sheet sheet = workbook.getSheet("NTN-B 150535");
		int linhas = sheet.getRows();
		int colunas = sheet.getColumns();
		System.out.println("L=" + linhas+"|C="+colunas);
		for (int i = sheet.getRows() - 1; i < linhas; i++) {
			Cell dia = sheet.getCell(0, i);
			Cell taxaCompraManha = sheet.getCell(1, i);
			Cell taxaVendaManha = sheet.getCell(2, i);
			Cell puCompraManha = sheet.getCell(3, i);
			Cell puVendaManha = sheet.getCell(4, i);
			Cell puBaseManha = sheet.getCell(5, i);
			String dsDia = dia.getContents();
			String dsTaxaCompraManha = taxaCompraManha.getContents();
			String dsTaxaVendaManha = taxaVendaManha.getContents();
			String dsPuCompraManha = puCompraManha.getContents();
			String dsPuVendaManha = puVendaManha.getContents();
			String dsPuBaseManha = puBaseManha.getContents();
			String dsRow = "dsDia=" + dsDia + "dsTaxaCompraManha=" + dsTaxaCompraManha + "dsTaxaVendaManha=" + dsTaxaVendaManha + "dsPuCompraManha="
					+ dsPuCompraManha + "dsPuVendaManha=" + dsPuVendaManha + "dsPuBaseManha=" + dsPuBaseManha;
			Bookmark bookmark = new Bookmark((new Date()).toString(), dsRow);

			RequestContext requestContext = ContextPOC.getRequestContext();
			requestContext.activate();

			Beans.getReference(EntityManager.class).getTransaction().begin();
			Beans.getReference(BookmarkBC.class).insert(bookmark);
			Beans.getReference(EntityManager.class).getTransaction().commit();

			requestContext.deactivate();

		}
		workbook.close();
	}

}
