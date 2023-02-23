package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xbali
 */
public class ShoppingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String username = request.getParameter("name");
        
        if(action != null){
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);    
        }else if(username != null){
            response.sendRedirect("ShoppingList");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);     
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("name");
        String add = request.getParameter("itemadd");
        String list = request.getParameter("itemthing");
        ArrayList<String> itemlist;
        HttpSession session = request.getSession();
        
        if(session.getAttribute("items")== null){
            itemlist = new ArrayList<>();
        }else{
            itemlist = (ArrayList<String>)session.getAttribute("items");
        }
        
            if(action.equals("register")){
             if(username == null || username.equals("")){
                request.setAttribute("message", "Enter a name");
               getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);  
            }else{
                session.setAttribute("name", username);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request,response);
            }}
            if(action.equals("logout")){ itemlist.clear();
             session.invalidate();
             getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response); }
            
            if(action.equals("add")){itemlist.add(add);
            session.setAttribute("items",itemlist);
             getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request,response);}
            
            if(action.equals("delete")){itemlist.remove(list);
            session.setAttribute("items",itemlist);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request,response);
            }    
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request,response);
    }

   
}
