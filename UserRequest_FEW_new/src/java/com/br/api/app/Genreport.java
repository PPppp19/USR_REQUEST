/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app;

import com.br.api.app.connect.ConnectDB2;


//import com.br.api.app.DB2.Conn_DB2;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Monthon
 */
public class Genreport {

    private JasperReport jasperReport;
    private JasperDesign jasperDesign;
    private Map param;
    Connection conn;
    // private String path;

    public Genreport() {

    }

    public void setreport(String name, String path, Map par) throws JRException, Exception {
        conn = ConnectDB2.ConnectionDB();

        jasperDesign = JRXmlLoader.load(path + "" + name);

        jasperReport = JasperCompileManager.compileReport(jasperDesign);//(JasperReport)JRLoader.loadObjectFromFile(path+""+name);

        //JRLoder.//
        param = par;

        //OutputStream outStream = response.getOutputStream();
    }

    public void getreport1(OutputStream outStream) throws JRException {
        JasperPrint jasp = JasperFillManager.fillReport(jasperReport, param, conn);
        JasperExportManager.exportReportToPdfStream(jasp, outStream);

    }

   

   

   
}
