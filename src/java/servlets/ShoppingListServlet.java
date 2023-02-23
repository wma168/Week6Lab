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
           String username= (String)session.getAttribute("username");
           if(username==null || username.equals("")){
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
           else if(request.getParameter("action").equals("logout")){
            session.invalidate();
            HttpSession session2 = request.getSession();
            session2.setAttribute("message","You have logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
           else{
               getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
           }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if(action.equals("register")){
           session.removeAttribute("message");
           String username= request.getParameter("username");
           session.setAttribute("username",username);
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
        
        else if(action.equals("add")){
            String item = request.getParameter("itemToAdd");
            if(item!=null &&!item.equals("")){
            session.removeAttribute("message");
            ArrayList<String> items=(ArrayList<String>)session.getAttribute("items");
            if(items==null)
                items=new ArrayList<>();
            items.add(item);
            session.setAttribute("items",items);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
            else{
                session.setAttribute("message","Please enter a valid item");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }

        }
        else if(action.equals("delete")){
            session.removeAttribute("message");
            ArrayList<String> items=(ArrayList<String>)session.getAttribute("items");
            if(items==null)
                items=new ArrayList<>();
            String item = request.getParameter("foodDelete");
            if(item==null||item.equals("")){
                request.setAttribute("noFoodDelete", "No item(s) to delete.");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
            else{
                items.remove(item);
                session.setAttribute("items",items);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
            
        }
    }


}