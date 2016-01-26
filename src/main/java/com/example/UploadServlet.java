package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
@MultipartConfig(location="/tmp/upload", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*50)
@WebServlet(urlPatterns={"/upload"}, name="upload")
public class UploadServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        
        int i=0;
        for(Part part: req.getParts())
        {
            out.printf("Got part: name=%s, size=%d%n",part.getName(), part.getSize());
            part.write(String.format("part-%02d.dat",i++));
        }
    }
}
