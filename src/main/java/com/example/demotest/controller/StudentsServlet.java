package com.example.demotest.controller;

import com.example.demotest.HelloServlet;
import com.example.demotest.dao.IManagerDAO;
import com.example.demotest.dao.StudentsDAO;
import com.example.demotest.model.Students;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "StudentsServlet", urlPatterns = "/Students")
public class StudentsServlet extends HelloServlet {

    private IManagerDAO iManagerDAO;

    public void init() {
        iManagerDAO = new StudentsDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                case "delete":
                    deleteStudents(request, response);
                    break;
                case "update":
                    showUpdateForm(request, response);
                    break;

                default:
                    listStudents(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        try {
            List<Students> studentsList = null;
            studentsList = iManagerDAO.selectAll();
            req.setAttribute("studentsList", studentsList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("view/home.jsp");
            dispatcher.forward(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCreateForm(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/add_students.jsp");
        dispatcher.forward(req,resp);
    }
    private void createStudents(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String address = request.getParameter("address");
        String phoneNumber =request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String classroom = request.getParameter("classroom");
        Students students = new Students(name,dateOfBirth,address,phoneNumber,email,classroom);
        iManagerDAO.create(students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/home.jsp");
        request.setAttribute("message", "New product was created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = (Students) iManagerDAO.select(id);
        RequestDispatcher dispatcher;
        if(students == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("students", students);
            dispatcher = request.getRequestDispatcher("view/update.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStudents(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //        int id = Integer.parseInt(request.getParameter("id"));
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String address = request.getParameter("address");
        String phoneNumber = new String(request.getParameter("phoneNumber").getBytes("iso-8859-1"), "utf-8");
        String email = request.getParameter("email");
        String classroom = request.getParameter("classroom");
        Students students = new Students(name,dateOfBirth,address,phoneNumber,email,classroom);
        iManagerDAO.update(students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/home.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudents(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        iManagerDAO.delete(id);
        List<Students> studentsList = null;
        try {
            studentsList = iManagerDAO.selectAll();

            req.setAttribute("studentsList", studentsList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("view/home.jsp");
            dispatcher.forward(req,resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createStudents(req, resp);
                    break;
                case "delete":
                    deleteStudents(req, resp);
                    break;
                case "update":
                    updateStudents(req, resp);
                    break;

                default:
                    listStudents(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
