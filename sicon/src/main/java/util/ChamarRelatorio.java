package util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ChamarRelatorio  implements Work {
	
	
	private String caminhoRelatorio;
	private String nomeRelatorio;
	private HashMap param;
	
	
	public ChamarRelatorio(String caminho, HashMap parametros, String nome){
		this.caminhoRelatorio = caminho;
		this.nomeRelatorio = nome;
		this.param = parametros;
		
		System.out.println("no chamar relatorio");
	}
	
	
	
	
	@Override
	public void execute(Connection connection) throws SQLException {

		try {
			
			HashMap parametros = param;

			FacesContext facesContext = FacesContext.getCurrentInstance();

			facesContext.responseComplete();
			
			ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

			JasperPrint jasperPrint = JasperFillManager
					.fillReport(scontext.getRealPath("/relatorios/" + caminhoRelatorio), parametros, connection);
			OutputStream saida = new FileOutputStream("clientes.pdf") {
				
				@Override
				public void write(int b) throws IOException {
					// TODO Auto-generated method stub
					
				}
			};

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

			byte[] bytes = baos.toByteArray();
			
			if (bytes != null && bytes.length > 0) {

				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

				response.setContentType("application/pdf");

				response.setHeader("Content-disposition", "attachment; filename=\"" + nomeRelatorio + ".pdf\"");

				response.setContentLength(bytes.length);

				ServletOutputStream outputStream = response.getOutputStream();

				outputStream.write(bytes, 0, bytes.length);

				outputStream.flush();

				outputStream.close();

				baos.close();
				System.out.println("terminou");
			}

		} catch (Exception e) {

			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);

		}

	}
	

	
	
	
	
}

