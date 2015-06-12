package grupo.teste01.poc;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class GetFileFromInternetPoc01 {

	public static void main(String args[]) throws IOException {
		getFileFromInternet("/media/025C9CA35874DA84/Pessoal/workspace/git-rest01/NTN-B_2015-GotFromInternet.xls");
	}

	public static void getFileFromInternet(String fileName) {
		OutputStream out = null;
		URLConnection conn = null;
		InputStream in = null;

		try {
			URL url = new URL(
					"http://www.tesouro.fazenda.gov.br/documents/10180/137713/NTN-B_2015.xls");
			out = new BufferedOutputStream(new FileOutputStream(fileName));
			conn = url.openConnection();
			in = conn.getInputStream();
			byte[] buffer = new byte[1024];

			int numRead;
			long numWritten = 0;

			while ((numRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, numRead);
				numWritten += numRead;
			}

			System.out.println(fileName + "\t" + numWritten);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
			}
		}
	}

	public static void getFileFromInternet1(String fileName)
			throws MalformedURLException, IOException, FileNotFoundException {
		URL website = new URL(
				"http://www.tesouro.fazenda.gov.br/documents/10180/137713/NTN-B_2015.xls");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
	}

}
